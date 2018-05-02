package com.tpay.order.model;

import com.tpay.base.model.BaseModel;


/**
* @desc 支付渠道表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class PayChannel extends BaseModel {
    private static final long serialVersionUID = 1L;
    
    /**
     * 渠道id
     */
    private String channelId;
    /**
     * 渠道名称 alipay，wechat等
     */
    private String channelName;
    /**
     * 渠道商户id
     */
    private String channelMchId;
    /**
     * 商户id
     */
    private Long mchId;
    /**
     * 渠道状态 0停用 1正常
     */
    private Integer channelStatus;
    /**
     * 配置参数 json
     */
    private String param;


    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelMchId() {
        return channelMchId;
    }
    public void setChannelMchId(String channelMchId) {
        this.channelMchId = channelMchId;
    }

    public Long getMchId() {
        return mchId;
    }
    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    public Integer getChannelStatus() {
        return channelStatus;
    }
    public void setChannelStatus(Integer channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "PayChannel{" +
                "channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelMchId='" + channelMchId + '\'' +
                ", mchId=" + mchId +
                ", channelStatus=" + channelStatus +
                ", param='" + param + '\'' +
                '}';
    }
}