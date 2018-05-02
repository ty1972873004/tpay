package com.tpay.notify.model;
import com.tpay.base.model.BaseModel;

import java.io.Serializable;



/**
* @desc 通知记录日志表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class NotifyRecordLog extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long notifyId;
    /**
     *
     */
    private String request;
    /**
     *
     */
    private String response;
    /**
     *
     */
    private String custNo;
    /**
     *
     */
    private String orderNo;
    /**
     *
     */
    private Integer httpStatus;


    public Long getNotifyId() {
        return notifyId;
    }
    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
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

    public Integer getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

}