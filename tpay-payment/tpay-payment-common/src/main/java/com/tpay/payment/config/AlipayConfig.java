package com.tpay.payment.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpay.common.utils.AssertUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-09 17:27
 **/
@Component
@Configuration
@ComponentScan(basePackages = "com.tpay.payment")
public class AlipayConfig {

    private String app_id;

    private String rsa_private_key;

    @Value("${alipay.notify.url}")
    private String notify_url;

    @Value("${alipay.return.url}")
    private String return_url;

    private String gateway_url = "https://openapi.alipay.com/gateway.do";

    public static String DEV_GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    public static String CHARSET = "UTF-8";

    public static String FORMAT = "json";

    private String alipay_public__key;

    public static String SIGNTYPE = "RSA2";



    /**
     * 1沙箱  0 正式
     */
    private Short is_sandbox = 0;

    public AlipayConfig init(String configParam){
        AssertUtil.assertNotNull(configParam,"init error");
        JSONObject json = JSON.parseObject(configParam);
        this.setApp_id(json.getString("appid"));
        this.setRsa_private_key(json.getString("private_key"));
        this.setAlipay_public__key(json.getString("alipay_public_key"));
        this.setIs_sandbox(json.getShortValue("isSandbox"));
        if(this.getIs_sandbox() == 1){
            this.setGateway_url(DEV_GATEWAY_URL);
        }
        return  this;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getRsa_private_key() {
        return rsa_private_key;
    }

    public void setRsa_private_key(String rsa_private_key) {
        this.rsa_private_key = rsa_private_key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getGateway_url() {
        return gateway_url;
    }

    public void setGateway_url(String gateway_url) {
        this.gateway_url = gateway_url;
    }

    public Short getIs_sandbox() {
        return is_sandbox;
    }

    public void setIs_sandbox(Short is_sandbox) {
        this.is_sandbox = is_sandbox;
    }

    public String getAlipay_public__key() {
        return alipay_public__key;
    }

    public void setAlipay_public__key(String alipay_public__key) {
        this.alipay_public__key = alipay_public__key;
    }
}
