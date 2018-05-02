package com.tpay.notify.entity;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION  消息通知参数
 * @create 2017-07-18 8:54
 **/
public class NotifyParam {

    /**
     *  通知时间次数
     */
    private Map<Integer,Integer> notifyParams;

    /**
     * 通知后用于判断是否成功的返回值。由HttpResponse获取
     */
    private String successValue;

    public Map<Integer, Integer> getNotifyParams() {
        return notifyParams;
    }

    public void setNotifyParams(Map<Integer, Integer> notifyParams) {
        this.notifyParams = notifyParams;
    }

    public String getSuccessValue() {
        return successValue;
    }

    public void setSuccessValue(String successValue) {
        this.successValue = successValue;
    }

    public Integer getMaxNotifyTime() {
        return notifyParams == null ? 0 : notifyParams.size();
    }




}
