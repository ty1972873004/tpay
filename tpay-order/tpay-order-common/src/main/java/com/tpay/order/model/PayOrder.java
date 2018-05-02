package com.tpay.order.model;

import com.tpay.base.model.BaseModel;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
* @desc 订单表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class PayOrder extends BaseModel {
    private static final long serialVersionUID = 1L;
    
    /**
     * 支付订单号
     * */
    private String payOrderNo;
    /**
     * 订单类型
     * */
    private String orderType;
    /**
     * 商户id
     * */
    private Long mchId;
    /**
     * 商户订单号
     * */
    private String mchOrderNo;
    /**
     * 支付渠道id
     * */
    private String payChannel;
    /**支付金额（分）*/
    private BigDecimal amount;
    /**三位货币代码 人民币:cny*/
    private String currency;
    /**支付状态（00：订单生成；01：支付中；02：支付成功；03：处理完成）*/
    private String payStatus;
    /**客户端ip*/
    private String clientIp;
    /**支付设备*/
    private String deviceInfo;
    /**商品标题*/
    private String subject;
    /**商品描述*/
    private String body;
    /**额外参数*/
    private String extra;
    /**渠道商户ID*/
    private String channelMchId;
    /**渠道订单号*/
    private String channelOrderNo;
    /**渠道支付错误码*/
    private String errCode;
    /**渠道支付错误描述*/
    private String errMsg;
    /**通知地址*/
    private String notifyUrl;
    /**通知次数*/
    private Integer notifyCount;
    /**最后一次通知事件*/
    private Timestamp lastNotifyTime;
    /**订单失效时间*/
    private Timestamp expireTime;
    /**订单支付成功时间*/
    private Timestamp paySuccessTime;



    public String getPayOrderNo() {
        return payOrderNo;
    }
    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getMchId() {
        return mchId;
    }
    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    public String getMchOrderNo() {
        return mchOrderNo;
    }
    public void setMchOrderNo(String mchOrderNo) {
        this.mchOrderNo = mchOrderNo;
    }

    public String getPayChannel() {
        return payChannel;
    }
    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayStatus() {
        return payStatus;
    }
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getClientIp() {
        return clientIp;
    }
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getExtra() {
        return extra;
    }
    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getChannelMchId() {
        return channelMchId;
    }
    public void setChannelMchId(String channelMchId) {
        this.channelMchId = channelMchId;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }
    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    public String getErrCode() {
        return errCode;
    }
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Integer getNotifyCount() {
        return notifyCount;
    }
    public void setNotifyCount(Integer notifyCount) {
        this.notifyCount = notifyCount;
    }

    public Timestamp getLastNotifyTime() {
        return lastNotifyTime;
    }
    public void setLastNotifyTime(Timestamp lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public Timestamp getPaySuccessTime() {
        return paySuccessTime;
    }
    public void setPaySuccessTime(Timestamp paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
                "payOrderNo='" + payOrderNo + '\'' +
                ", orderType='" + orderType + '\'' +
                ", mchId=" + mchId +
                ", mchOrderNo='" + mchOrderNo + '\'' +
                ", payChannel=" + payChannel +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", extra='" + extra + '\'' +
                ", channelMchId='" + channelMchId + '\'' +
                ", channelOrderNo='" + channelOrderNo + '\'' +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", notifyCount=" + notifyCount +
                ", lastNotifyTime=" + lastNotifyTime +
                ", expireTime=" + expireTime +
                ", paySuccessTime=" + paySuccessTime +
                '}';
    }
}