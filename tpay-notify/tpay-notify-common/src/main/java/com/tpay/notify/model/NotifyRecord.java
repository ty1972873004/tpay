package com.tpay.notify.model;
import com.tpay.base.model.BaseModel;

import java.io.Serializable;
import java.util.Date;



/**
* @desc 通知记录表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class NotifyRecord extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Date lastNotifyTime;
    /**
     *
     */
    private Integer notifyTimes;
    /**
     *
     */
    private Integer limitNotifyTimes;
    /**
     *
     */
    private String url;
    /**
     *
     */
    private String custNo;
    /**
     *
     */
    private String orderNo;
    /**
     *通知状态(100:成功:101:未成功,默认101)
     */
    private Integer status;
    /**
     *
     */
    private Integer notifyType;




    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }
    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    public Integer getNotifyTimes() {
        return notifyTimes;
    }
    public void setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
    }

    public Integer getLimitNotifyTimes() {
        return limitNotifyTimes;
    }
    public void setLimitNotifyTimes(Integer limitNotifyTimes) {
        this.limitNotifyTimes = limitNotifyTimes;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getCustNo() {
        return custNo;
    }
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNotifyType() {
        return notifyType;
    }
    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }



}