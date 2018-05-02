package com.tpay.payment.controller;

import com.tpay.base.controller.BaseController;
import com.tpay.common.utils.WebUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-10 15:55
 **/
@RestController
@RequestMapping(value = "/test", method = RequestMethod.POST)
public class TestNotifyController extends BaseController{
    
    private static Logger logger = LoggerFactory.getLogger(TestNotifyController.class);

    @RequestMapping("/notify")
    public String alipayWap(HttpServletRequest request,HttpServletResponse response)  throws IOException {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        System.out.println(params.toString());
        return "success";
    }
}
