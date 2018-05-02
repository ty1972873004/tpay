package com.tpay.notify.message;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpay.common.utils.DateUtils;
import com.tpay.common.utils.JsonUtils;
import com.tpay.common.utils.StringUtil;
import com.tpay.notify.core.NotifyPersist;
import com.tpay.notify.core.NotifyQueue;
import com.tpay.notify.enums.NotifyStatusEnum;
import com.tpay.notify.model.NotifyRecord;
import com.tpay.notify.service.NotifyRecordService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.sql.Timestamp;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION MQ消费者
 * @create 2017-07-10 14:04
 **/

public class DefaultMessageQueueListener implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(DefaultMessageQueueListener.class);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    NotifyRecordService notifyRecordService;

    @Autowired
    NotifyPersist notifyPersist;

    @Autowired
    private NotifyQueue notifyQueue;

    @Override
    public void onMessage(final Message message) {
        // 使用线程池多线程处理
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        final String ms = textMessage.getText();
                        logger.info("消费："+textMessage.getText());
                        JSON json = (JSON) JSONObject.parse(ms);
                        NotifyRecord notifyRecord = JsonUtils.jsonToPojo(ms, NotifyRecord.class);
                        if (notifyRecord == null) {
                            return;
                        }

                        notifyRecord.setStatus(NotifyStatusEnum.CREATED.getValue());
                        notifyRecord.setCreateTime(new Timestamp(DateUtils.getCurrentTimeStamp()));
                        notifyRecord.setLastNotifyTime(new Timestamp(DateUtils.getCurrentTimeStamp()));

                        if(!StringUtil.isEmpty(notifyRecord.getId())){
                            NotifyRecord notifyRecordById = notifyRecordService.queryById(notifyRecord.getId());
                            if (notifyRecordById != null){
                                return;
                            }
                        }

                        while (notifyRecordService == null) {
                            // 主动休眠，防止类Spring 未加载完成，监听服务就开启监听出现空指针异常
                            Thread.currentThread().sleep(1000);
                        }

                        try{
                            notifyRecord = notifyPersist.saveNotifyRecord(notifyRecord);
                            // 添加到通知队列
                            notifyQueue.addElementToList(notifyRecord);
                        }catch (Exception e){
                            logger.error("notify消息保存异常"+e);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
