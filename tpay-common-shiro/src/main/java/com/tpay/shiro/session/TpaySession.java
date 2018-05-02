package com.tpay.shiro.session;

import com.tpay.shiro.enums.OnlineStatusEnum;

import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @author tuyong
 * @version 1.0
 * @desc 重写session
 * @create 2018-03-30 10:30
 **/
public class TpaySession extends SimpleSession {

    private static final long serialVersionUID = -2717034727725707977L;

    /**
     * 用户浏览器类型
     */
    private String userAgent;

    /**
     *在线状态
     */
    private OnlineStatusEnum status = OnlineStatusEnum.OFF_LINE;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public OnlineStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OnlineStatusEnum status) {
        this.status = status;
    }
}
