package com.tpay.notify.core;

import com.alibaba.fastjson.JSONObject;
import com.tpay.common.exceptions.BusinessException;
import com.tpay.common.utils.SpringContextUtil;
import com.tpay.common.utils.httpclient.SimpleHttpParam;
import com.tpay.common.utils.httpclient.SimpleHttpResult;
import com.tpay.common.utils.httpclient.SimpleHttpUtils;
import com.tpay.notify.entity.NotifyParam;
import com.tpay.notify.enums.NotifyStatusEnum;
import com.tpay.notify.model.NotifyRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-07-18 15:57
 **/
public class NotifyTask implements  Runnable,Delayed{

    private static Logger logger = LoggerFactory.getLogger(NotifyTask.class);

    private long executeTime;

    private NotifyRecord notifyRecord;

    private NotifyQueue notifyQueue;

    private NotifyParam notifyParam;

    private NotifyPersist notifyPersist;

    public NotifyTask() {
    }

    public NotifyTask(NotifyRecord notifyRecord, NotifyQueue notifyQueue, NotifyParam notifyParam) {
        super();
        this.notifyRecord = notifyRecord;
        this.notifyQueue = notifyQueue;
        this.notifyParam = notifyParam;
        this.executeTime = getExecuteTime(notifyRecord);
    }

    private long getExecuteTime(NotifyRecord record) {
        long lastTime = record.getLastNotifyTime().getTime();
        Integer nextNotifyTime = notifyParam.getNotifyParams().get(record.getNotifyTimes());
        return (nextNotifyTime == null ? 0 : nextNotifyTime * 1000) + lastTime;
    }

    @Override
    public int compareTo(Delayed o) {
        NotifyTask task = (NotifyTask) o;
        return executeTime > task.executeTime ? 1 : (executeTime < task.executeTime ? -1 : 0);
    }



    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(),TimeUnit.SECONDS);
    }



    @Override
    public void run() {

        // 得到当前通知对象的通知次数
        Integer notifyTimes = notifyRecord.getNotifyTimes();

        try {
            logger.info("Notify Url " + notifyRecord.getUrl()+" ;notify id:"+notifyRecord.getId()+";notify times:"+notifyRecord.getNotifyTimes());

            /** 采用 httpClient */
            SimpleHttpParam param = new SimpleHttpParam(notifyRecord.getUrl());
            param.setMethod("POST");
            SimpleHttpResult result = SimpleHttpUtils.httpRequest(param);
            notifyRecord.setNotifyTimes(notifyTimes + 1);
            String successValue = notifyParam.getSuccessValue();
            String responseMsg = "";
            Integer responseStatus = result.getStatusCode();

            if(null == notifyPersist){

                notifyPersist = SpringContextUtil.getBean(NotifyPersist.class);
            }

            // 得到返回状态，如果是200，也就是通知成功
            if (result != null
                    && (responseStatus == 200 || responseStatus == 201 || responseStatus == 202 || responseStatus == 203
                    || responseStatus == 204 || responseStatus == 205 || responseStatus == 206)) {
                responseMsg = result.getContent().trim();
                responseMsg = responseMsg.length() >= 600 ? responseMsg.substring(0, 600) : responseMsg;

                logger.info("订单号： " + notifyRecord.getOrderNo() + " HTTP_STATUS：" + responseStatus + "请求返回信息：" + responseMsg);

                // 通知成功
                if (responseMsg.equals(successValue)) {
                    //http请求处理成功
                    notifyPersist.updateNotifyRord(notifyRecord.getId(), notifyRecord.getNotifyTimes(), NotifyStatusEnum.SUCCESS.getValue());
                } else {
                    notifyQueue.addElementToList(notifyRecord);
                    notifyPersist.updateNotifyRord(notifyRecord.getId(), notifyRecord.getNotifyTimes(),
                            NotifyStatusEnum.HTTP_REQUEST_SUCCESS.getValue());

                }
                logger.info("Update NotifyRecord:" + JSONObject.toJSONString(notifyRecord)+";responseMsg:"+responseMsg);
            } else {
                //添加任务队列
                notifyQueue.addElementToList(notifyRecord);
                // 再次放到通知列表中，由添加程序判断是否已经通知完毕或者通知失败
                notifyPersist.updateNotifyRord(notifyRecord.getId(), notifyRecord.getNotifyTimes(),
                        NotifyStatusEnum.HTTP_REQUEST_FALIED.getValue());
            }
            // 写通知日志表
            notifyPersist.saveNotifyRecordLogs(notifyRecord.getId(), notifyRecord.getCustNo(), notifyRecord.getOrderNo(),
                    notifyRecord.getUrl(), responseMsg, responseStatus);
            logger.info("Insert NotifyRecordLog, CustNo:" + notifyRecord.getCustNo() + ",OrderNo:"
                    + notifyRecord.getOrderNo());

        } catch (BusinessException e) {
            logger.error("NotifyTask", e);
        } catch (Exception e) {
            logger.error("NotifyTask", e);

            //添加任务队列
            notifyQueue.addElementToList(notifyRecord);

            //更新消息状态
            notifyPersist.updateNotifyRord(notifyRecord.getId(), notifyRecord.getNotifyTimes(),
                    NotifyStatusEnum.HTTP_REQUEST_FALIED.getValue());
            //保存日志
            notifyPersist.saveNotifyRecordLogs(notifyRecord.getId(), notifyRecord.getCustNo(), notifyRecord.getOrderNo(),
                    notifyRecord.getUrl(), "", 0);
        }


    }
}
