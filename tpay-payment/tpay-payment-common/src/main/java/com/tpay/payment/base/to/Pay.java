package com.tpay.payment.base.to;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-09 15:01
 **/
public class Pay extends PayBase {

    /**
     * 商城订单号
     */
    private String orderNo;
    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 订单金额(元)
     */
    private String orderMoney;
    /**
     * 订单描述(非必填,传递给上游渠道可原路获取)
     */
    private String orderDesc;
    /**
     * 同步返回URL
     */
    private String returnUrl;
    /**
     * 异步返回URL
     */
    private String notifyUrl;
    /**
     * 银行编号
     */
    private String bankNo;

    /**
     * 支付宝参数
     */
    private String payConfig;

    /**
     * 订单生成的机器IP
     */
    private String spBillCreateIP ;

    private String openId;

    private String extra;



    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
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

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getPayConfig() {
        return payConfig;
    }

    public void setPayConfig(String payConfig) {
        this.payConfig = payConfig;
    }

    public String getSpBillCreateIP() {
        return spBillCreateIP;
    }

    public void setSpBillCreateIP(String spBillCreateIP) {
        this.spBillCreateIP = spBillCreateIP;
    }


    public String getOpenId() {
        return openId;
    }


    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "orderNo='" + orderNo + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderMoney='" + orderMoney + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", payConfig='" + payConfig + '\'' +
                ", spBillCreateIP='" + spBillCreateIP + '\'' +
                ", openId='" + openId + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
