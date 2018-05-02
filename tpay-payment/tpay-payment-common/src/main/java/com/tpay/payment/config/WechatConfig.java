package com.tpay.payment.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpay.common.utils.AssertUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-11 11:51
 **/
@Component
@Configuration
public class WechatConfig {

    private String appId;

    private String mchId;

    private String keyPath;

    private String mchKey;

    @Value("${wechat.cert.path}")
    private String certRootPath;

    @Value("${wechat.notify.url}")
    private String notifyUrl;

    private String certPassword;

    public WechatConfig init(String configParam){
        AssertUtil.assertNotNull(configParam,"init error");
        JSONObject json = JSON.parseObject(configParam);
        this.setAppId(json.getString("appId"));
        this.setMchId(json.getString("mchId"));
        this.setMchKey(json.getString("mchKey"));
        this.setCertPassword(json.getString("certPassword"));
        this.setKeyPath(certRootPath + File.separator + json.getString("certLocalPath"));
        this.setNotifyUrl(notifyUrl);
        return this;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCertRootPath() {
        return certRootPath;
    }

    public void setCertRootPath(String certRootPath) {
        this.certRootPath = certRootPath;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
    }
}
