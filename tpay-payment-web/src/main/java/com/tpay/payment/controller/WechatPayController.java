package com.tpay.payment.controller;

import com.alibaba.fastjson.JSONArray;
import com.tpay.payment.utils.Configure;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-11 16:51
 **/
@Controller
@RequestMapping(value = "/weixinAction", method = RequestMethod.GET)
public class WechatPayController {


    @RequestMapping(value = "/jsApi", method = RequestMethod.GET)
    public String jsApi(ModelMap modelMap){
        return "jsapi";

    }

    @RequestMapping(value = "/h5Pay", method = RequestMethod.GET)
    public String h5Pay(ModelMap modelMap){
        return "h5Pay";

    }



}
