package com.tpay.payment.base.to;

import java.math.BigDecimal;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-11 10:29
 **/
public class Trans extends TransBase {

    /**
     * 流水号
     */
    private String bizNo;

    /**
     * 收款账号
     */
    private String payeeAccount;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 付款方名称
     */
    private String showName;

    /**
     * 收款方真实姓名
     */
    private String payeeRealName;

    private String remark;

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPayeeRealName() {
        return payeeRealName;
    }

    public void setPayeeRealName(String payeeRealName) {
        this.payeeRealName = payeeRealName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
