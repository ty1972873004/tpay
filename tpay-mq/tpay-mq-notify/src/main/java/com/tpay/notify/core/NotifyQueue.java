package com.tpay.notify.core;


import com.tpay.notify.enums.NotifyStatusEnum;
import com.tpay.notify.model.NotifyRecord;
import com.tpay.notify.entity.NotifyParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-07-18 15:47
 **/
@Component
public class NotifyQueue implements Serializable{

    private static Logger logger = LoggerFactory.getLogger(NotifyQueue.class);

    @Autowired
    private NotifyParam notifyParam;

    @Autowired
    private NotifyPersist notifyPersist;

    private DelayQueue<NotifyTask> tasks = new DelayQueue<NotifyTask>();

    public DelayQueue<NotifyTask> getTasks() {
        return tasks;
    }



    /**
     * 将传过来的对象进行通知次数判断，之后决定是否放在任务队列中
     * @param notifyRecord
     */
    public void addElementToList(NotifyRecord notifyRecord) {

        if (notifyRecord == null) {
            return;
        }
        Integer notifyTimes = notifyRecord.getNotifyTimes(); // 通知次数
        Integer maxNotifyTime = 0;

        try {
            maxNotifyTime = notifyParam.getMaxNotifyTime();
        } catch (Exception e) {
            logger.error("获取通知次数异常",e);
        }

        if (notifyRecord.getNotifyTimes() == 0) {// 刚刚接收到的数据
            notifyRecord.setLastNotifyTime(new Date());
        }

        long time = notifyRecord.getLastNotifyTime().getTime();
        Map<Integer, Integer> timeMap = notifyParam.getNotifyParams();

        if (notifyTimes < maxNotifyTime) {
            Integer nextKey = notifyTimes + 1;
            Integer next = timeMap.get(nextKey);
            if (next != null) {
                time += 1000 * 60 * next + 1;
                notifyRecord.setLastNotifyTime(new Date(time));
                tasks.put(new NotifyTask(notifyRecord, this, notifyParam));
            }
        } else {
            try {
                // 持久化到数据库中
                notifyPersist.updateNotifyRord(notifyRecord.getId(),
                        notifyRecord.getNotifyTimes(), NotifyStatusEnum.FAILED.getValue());
                logger.info("Update NotifyRecord failed,merchantNo:" + notifyRecord.getCustNo() + ",merchantOrderNo:"
                        + notifyRecord.getOrderNo());
            } catch (Exception e) {
                logger.error("NotifyQueue"+e);
            }
        }
    }
}
