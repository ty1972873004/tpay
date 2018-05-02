package com.tpay.payment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author tuyong
 * @version 1.0
 * @desc  第三方支付接口请求参数
 * @create 2018-04-10 9:23
 **/
public class ThirdPayReq implements Serializable {

    private static final long serialVersionUID = 797638830309589397L;


    /**订单名称**/
    private String orderName;

    /**订单金额(元)**/
    private BigDecimal orderMoney;

    /**订单描述(非必填,传递给上游渠道可原路获取)**/
    private String orderDesc;

    /**同步返回URL**/
    private String returnUrl;

    /**异步返回URL**/
    private String notifyUrl;

    /**交易渠道**/
    private String pcId;

    /**商户id**/
    private Long mchId;

    /**商户类型**/
    private String mchType;

    /**订单号**/
    private String orderNo;

    /**收款流水号**/
    private String ptoId;

    /**支付渠道id**/
    private String ChannelId;

    private String clientIp;

    private String openId;

    private String extra;


    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public Long getMchId() {
        return mchId;
    }

    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    public String getMchType() {
        return mchType;
    }

    public void setMchType(String mchType) {
        this.mchType = mchType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPtoId() {
        return ptoId;
    }

    public void setPtoId(String ptoId) {
        this.ptoId = ptoId;
    }

    public String getChannelId() {
        return ChannelId;
    }

    public void setChannelId(String channelId) {
        ChannelId = channelId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ThirdPayReq{" +
                "orderName='" + orderName + '\'' +
                ", orderMoney=" + orderMoney +
                ", orderDesc='" + orderDesc + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", pcId='" + pcId + '\'' +
                ", mchId=" + mchId +
                ", mchType='" + mchType + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", ptoId='" + ptoId + '\'' +
                ", ChannelId='" + ChannelId + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", openId='" + openId + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
