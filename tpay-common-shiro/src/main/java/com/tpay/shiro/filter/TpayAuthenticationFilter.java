package com.tpay.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.tpay.cache.redis.JedisClient;
import com.tpay.common.utils.PropertiesFileUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.shiro.constants.ShiroConstants;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-30 10:15
 **/
public class TpayAuthenticationFilter extends AuthenticationFilter {

    private static Logger logger = LoggerFactory.getLogger(TpayAuthenticationFilter.class);


    @Autowired
    private JedisClient jedisClient;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();

        // 判断请求类型
        String tPayType = PropertiesFileUtil.getInstance("tpay-upms-client").get("tpay.upms.type");

        session.setAttribute(ShiroConstants.UPMS_TYPE, tPayType);
        if (ShiroConstants.CLIENT.equals(tPayType)) {
            return validateClient(request, response);
        }
        if (ShiroConstants.SERVER.equals(tPayType)) {
            return subject.isAuthenticated();
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        StringBuffer sso_server_url = new StringBuffer(PropertiesFileUtil.getInstance("tpay-upms-client").get("tpay.upms.sso.server.url"));

        // server需要登录
        String tPayType = PropertiesFileUtil.getInstance("tpay-upms-client").get("tpay.upms.type");
        if (ShiroConstants.SERVER.equals(tPayType)) {
            WebUtils.toHttp(response).sendRedirect(sso_server_url.append("/sso/login").toString());
            return false;
        }
        sso_server_url.append("/sso/index").append("?").append("appid").append("=").append(PropertiesFileUtil.getInstance("tpay-upms-client").get("tpay.upms.appID"));
        // 回跳地址
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        StringBuffer backUrl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            backUrl.append("?").append(queryString);
        }
        sso_server_url.append("&").append("backurl").append("=").append(URLEncoder.encode(backUrl.toString(), "utf-8"));
        WebUtils.toHttp(response).sendRedirect(sso_server_url.toString());
        return false;

    }


    /**
     * 认证中心登录成功带回code
     * @param request
     */
    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) session.getTimeout() / 1000;
        // 判断局部会话是否登录
        String cacheClientSession = (String)jedisClient.get(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_ID,session.getId()));
        if (StringUtils.isNotBlank(cacheClientSession)) {
            // 更新code有效期
            jedisClient.set(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_ID,sessionId),cacheClientSession, timeOut);
            jedisClient.expire(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,cacheClientSession),timeOut);
            // 移除url中的code参数
            if (null != request.getParameter("code")) {
                String backUrl = WebUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                try {
                    httpServletResponse.sendRedirect(backUrl.toString());
                } catch (IOException e) {
                    logger.error("局部会话已登录，移除code参数跳转出错：", e);
                }
            } else {
                return true;
            }
        }
        // 判断是否有认证中心code
        String code = request.getParameter("upms_code");

        if (StringUtils.isNotBlank(code)) {
            // HttpPost去校验code
            try {
                StringBuffer sso_server_url = new StringBuffer(PropertiesFileUtil.getInstance("tpay-upms-client").get("tpay.upms.sso.server.url"));
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(sso_server_url.toString() + "/sso/code");
                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("code", code));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                    if (1 == result.getIntValue("code") && result.getString("data").equals(code)) {
                        // code校验正确，创建局部会话
                        jedisClient.set(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_ID,sessionId), code, timeOut);

                        // 保存code对应的局部会话sessionId，方便退出操作
                        jedisClient.sAdd(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,code),sessionId,timeOut);
                        logger.debug("当前code="+code+"，对应的注册系统个数："+jedisClient.sCard(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,code))+"个");
                        // 移除url中的token参数
                        String backUrl = WebUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                        // 返回请求资源
                        try {
                            // client无密认证
                            String username = request.getParameter("upms_username");
                            subject.login(new UsernamePasswordToken(username, ""));
                            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                            httpServletResponse.sendRedirect(backUrl.toString());
                            return true;
                        } catch (IOException e) {
                            logger.error("已拿到code，移除code参数跳转出错：", e);
                        }
                    } else {
                        logger.warn(result.getString("data"));
                    }
                }
            } catch (IOException e) {
                logger.error("验证token失败：", e);
            }
        }
        return false;
    }

    private String getCacheKey(String type,Object key) {
        StringBuilder builder = new StringBuilder(type);
        builder.append("_");
        builder.append(key);
        return builder.toString();
    }
}
