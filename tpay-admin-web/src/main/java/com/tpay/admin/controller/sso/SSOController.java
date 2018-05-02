package com.tpay.admin.controller.sso;

import com.tpay.base.controller.BaseController;
import com.tpay.cache.redis.JedisClient;
import com.tpay.shiro.constants.ShiroConstants;
import com.tpay.shiro.session.TpaySessionDao;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tuyong
 * @version 1.0
 * @desc  单点登录Controller
 * @create 2018-03-30 17:24
 **/
@Controller
@RequestMapping("/sso")
public class SSOController extends BaseController{

    @Autowired
    private TpaySessionDao tpaySessionDao;

    @Autowired
    private JedisClient jedisClient;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String key = ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID + "_" + sessionId;
        jedisClient.del(key);
        // 跳回原地址
        String redirectUrl = request.getHeader("Referer");
        if (null == redirectUrl) {
            redirectUrl = "/";
        }
        return "redirect:" + redirectUrl;
    }


}
