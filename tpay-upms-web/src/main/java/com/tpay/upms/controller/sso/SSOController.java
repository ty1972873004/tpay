package com.tpay.upms.controller.sso;

import com.tpay.base.controller.BaseController;
import com.tpay.cache.redis.JedisClient;
import com.tpay.common.constants.WebConstants;
import com.tpay.common.utils.UUIDUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.shiro.constants.ShiroConstants;
import com.tpay.shiro.enums.OnlineStatusEnum;
import com.tpay.shiro.session.TpaySessionDao;
import com.tpay.user.model.SysUser;
import com.tpay.user.service.SysUserService;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tuyong
 * @version 1.0
 * @desc  单点登录Controller
 * @create 2018-03-30 17:24
 **/
@Controller
@RequestMapping("/sso")
public class SSOController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(SSOController.class);

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TpaySessionDao tpaySessionDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception {
        String appid = request.getParameter("appid");
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(appid)) {
            throw new RuntimeException("无效访问！");
        }
        return "redirect:/sso/login?backurl=" + URLEncoder.encode(backurl, "utf-8");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String serverSessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳
        String code = (String)jedisClient.get(getShiroCacheKey(ShiroConstants.TPAY_UPMS_SERVER_SESSION_ID,serverSessionId));
        // code校验值
        if (StringUtils.isNotBlank(code)) {
            // 回跳
            String backUrl = request.getParameter("backurl");
            String username = (String) subject.getPrincipal();
            if (StringUtils.isBlank(backUrl)) {
                backUrl = "/";
            } else {
                if (backUrl.contains("?")) {
                    backUrl += "&upms_code=" + code + "&upms_username=" + username;
                } else {
                    backUrl += "?upms_code=" + code + "&upms_username=" + username;
                }
            }
            logger.debug("认证中心帐号通过，带code回跳：{}", backUrl);
            return "redirect:" + backUrl;

        }
        return "/sso/login.jsp";
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = (String)jedisClient.get(getShiroCacheKey(ShiroConstants.TPAY_UPMS_SERVER_SESSION_ID,sessionId));
        // code校验值
        if (StringUtils.isBlank(hasCode)) {
            // 使用shiro认证
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                if (BooleanUtils.toBoolean(rememberMe)) {
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    usernamePasswordToken.setRememberMe(false);
                }
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                return setModelMap(modelMap,WebConstants.ACCOUNT_NOT_EXIST);
            } catch (IncorrectCredentialsException e) {
                return setModelMap(modelMap,WebConstants.PASSWORD_ERROR);
            } catch (LockedAccountException e) {
                return setModelMap(modelMap,WebConstants.ACCOUNT_IS_LOCKED);
            }

            SysUser sysUser = sysUserService.selectUserByUsername(username);
            sysUser.setOldLoginIp(sysUser.getLoginIp());
            sysUser.setOldLoginTime(sysUser.getLoginTime());
            sysUser.setLoginIp(WebUtil.getHost(request));
            sysUser.setLoginNum(sysUser.getLoginNum()+1);
            sysUser.setLoginTime(new Timestamp(System.currentTimeMillis()));
            sysUserService.insertOrUpdate(sysUser);
            // 更新session状态
            tpaySessionDao.updateStatus(sessionId, OnlineStatusEnum.ON_LINE);

            // 全局会话sessionId列表，供会话管理
            jedisClient.lPush(ShiroConstants.TPAY_UPMS_SERVER_SESSION_IDS,sessionId.toString());
            // 默认验证帐号密码正确，创建code
            String code = UUIDUtil.getUUID();
            // 全局会话的code
            jedisClient.set(getShiroCacheKey(ShiroConstants.TPAY_UPMS_SERVER_SESSION_ID,sessionId),code,(int) subject.getSession().getTimeout() / 1000);
            // code校验值
            jedisClient.set(getShiroCacheKey(ShiroConstants.TPAY_UPMS_SERVER_CODE,code),code, (int) subject.getSession().getTimeout() / 1000);
        }
        // 回跳登录前地址
        String backurl = request.getParameter("backurl");

        if (StringUtils.isBlank(backurl)) {
            return setModelMap(modelMap,WebConstants.REQUEST_SUCCESS,"/manage/index");
        }else {
            return setModelMap(modelMap, WebConstants.REQUEST_SUCCESS,backurl);
        }

    }


    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public Object code(HttpServletRequest request,ModelMap modelMap) {
        String codeParam = request.getParameter("code");
        String code = (String) jedisClient.get(getShiroCacheKey(ShiroConstants.TPAY_UPMS_SERVER_CODE,codeParam));
        if (StringUtils.isBlank(codeParam) || !codeParam.equals(code)) {
             setModelMap(modelMap,WebConstants.REQUEST_FAIL,"无效code");
        }
        return setModelMap(modelMap, WebConstants.REQUEST_SUCCESS,code);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // shiro退出登录
        SecurityUtils.getSubject().logout();
        // 跳回原地址
        String redirectUrl = request.getHeader("Referer");
        if (null == redirectUrl) {
            redirectUrl = "/";
        }
        return "redirect:" + redirectUrl;
    }


}
