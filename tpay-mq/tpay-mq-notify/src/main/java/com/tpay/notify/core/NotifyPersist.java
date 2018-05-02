package com.tpay.notify.core;



import com.tpay.common.utils.DateUtils;
import com.tpay.dao.plugins.util.Page;
import com.tpay.notify.model.NotifyRecord;
import com.tpay.notify.model.NotifyRecordLog;
import com.tpay.notify.service.NotifyRecordLogService;
import com.tpay.notify.service.NotifyRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-07-18 10:52
 **/
@Service("notifyPersist")
public class NotifyPersist {

    @Autowired
    private NotifyRecordService notifyRecordService;

    @Autowired
    private NotifyRecordLogService notifyRecordLogService;

    public NotifyRecord saveNotifyRecord(NotifyRecord notifyRecord){
         return notifyRecordService.insertOrUpdate(notifyRecord);
    }

    public void updateNotifyRord(Long id, Integer notifyTimes, Integer status) {
        NotifyRecord notifyRecord = notifyRecordService.queryById(id);
        notifyRecord.setNotifyTimes(notifyTimes);
        notifyRecord.setStatus(status);
        notifyRecord.setUpdateTime(new Timestamp(DateUtils.getCurrentTimeStamp()));
        notifyRecordService.insertOrUpdate(notifyRecord);
    }

    public NotifyRecordLog saveNotifyRecordLogs(Long notifyId, String custNo, String orderNo, String request, String response,
                                                int httpStatus) {
       NotifyRecordLog notifyRecordLog = new NotifyRecordLog();
        notifyRecordLog.setHttpStatus(httpStatus);
        notifyRecordLog.setCustNo(custNo);
        notifyRecordLog.setOrderNo(orderNo);
        notifyRecordLog.setNotifyId(notifyId);
        notifyRecordLog.setRequest(request);
        notifyRecordLog.setResponse(response);
        return notifyRecordLogService.insertOrUpdate(notifyRecordLog);
    }

    public Page<NotifyRecord> selectNotifyRecordPage(Map<String, Object> params){
        return notifyRecordService.query(params);
    }

    public Page<NotifyRecord> queryBean(Map<String, Object> params){
        return  this.notifyRecordService.query(params);
    }

}
