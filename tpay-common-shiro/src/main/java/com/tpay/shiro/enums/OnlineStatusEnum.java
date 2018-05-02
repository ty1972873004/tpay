package com.tpay.shiro.enums;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-30 10:32
 **/
public enum  OnlineStatusEnum {

    ON_LINE("在线"),
    OFF_LINE("离线"),
    FORCE_LOGOUT("强制退出");

    private final String info;

    private OnlineStatusEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
