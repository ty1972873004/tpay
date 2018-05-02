package com.tpay.notify.service;

import com.alibaba.fastjson.JSONObject;
import com.tpay.base.service.BaseServiceImpl;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.notify.enums.NotifyStatusEnum;
import com.tpay.notify.model.NotifyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-27 9:27
 **/
@Service("notifyRecordService")
public class NotifyRecordServiceImpl extends BaseServiceImpl<NotifyRecord> implements NotifyRecordService{

    @Autowired
    private JmsTemplate jmsTemplate;


    @Override
    public void notifySend(String notifyUrl, String orderNo, String custNo, int notifyType, String queueName, String remark) {
        NotifyRecord record = new NotifyRecord();
        record.setNotifyTimes(0);
        record.setLimitNotifyTimes(5);
        record.setStatus(NotifyStatusEnum.CREATED.getValue());
        record.setUrl(notifyUrl);
        record.setOrderNo(orderNo);
        record.setCustNo(custNo);
        record.setNotifyType(notifyType);
        record.setRemark(remark);
        //参数json格式化
        Object toJSON = JSONObject.toJSON(record);
        final String str = toJSON.toString();

        //设置QUEUE队列名称
        jmsTemplate.setDefaultDestinationName(queueName);

        //发送消息到MQ
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(str);
            }
        });
    }


    @Override
    public Page<NotifyRecord> queryBean(Map<String, Object> params) {
        return this.query(params);
    }
}
