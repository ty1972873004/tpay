package com.tpay.notify.service;

import com.tpay.base.service.BaseService;
import com.tpay.dao.plugins.util.Page;
import com.tpay.notify.model.NotifyRecord;

import java.util.Map;

/**
 * @desc
 * @author Trazen
 * @since 2018-04-27
 * @version 1.0
 */
public interface NotifyRecordService extends BaseService<NotifyRecord> {


    /**
     * 发送通知消息
     * @param notifyUrl  发送消息url
     * @param orderNo 订单编号
     * @param custNo 会员编号
     * @param notifyType 通知类型
     * @param queueName 队列名称
     * @param  remark
     */
    void notifySend(String notifyUrl, String orderNo, String custNo,int notifyType,String queueName,String remark);


    /**
     * 分页查询
     * @param params
     * @return
     */
    Page<NotifyRecord> queryBean(Map<String, Object> params) ;



}
