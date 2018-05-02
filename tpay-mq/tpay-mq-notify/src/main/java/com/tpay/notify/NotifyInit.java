package com.tpay.notify;

import com.tpay.dao.plugins.util.Page;
import com.tpay.notify.core.NotifyPersist;
import com.tpay.notify.core.NotifyQueue;
import com.tpay.notify.core.NotifyTask;
import com.tpay.notify.model.NotifyRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-27 17:51
 **/
public class NotifyInit {

    private static Logger logger = LoggerFactory.getLogger(NotifyInit.class);


    @Resource
    private ThreadPoolTaskExecutor threadPool;

    @Resource
    private NotifyQueue notifyQueue;

    @Resource
    public NotifyPersist notifyPersist;

    public void init(){

        try{
            startInitFromDB();
            startThread();
        }catch (Exception e){
            logger.error("========MQ Notify start error",e);
        }


    }



    private void startThread() {
        logger.info("startThread");
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(50);// 50毫秒执行一次
                        // 如果当前活动线程等于最大线程，那么不执行
                        if (threadPool.getActiveCount() < threadPool.getMaxPoolSize()) {
                            final NotifyTask task = notifyQueue.getTasks().poll();
                            if (task != null) {
                                threadPool.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        logger.info(threadPool.getActiveCount() + "---------");
                                        notifyQueue.getTasks().remove(task);
                                        task.run();
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("系统异常", e);
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 从数据库中取一次数据用来当系统启动时初始化
     */
    private  void startInitFromDB() {
        logger.info("get data from database");
        // 查询状态和通知次数符合以下条件的数据进行通知
        String[] status = new String[] { "101", "102", "200", "201" };
        Integer[] notifyTime = new Integer[] { 0, 1, 2, 3, 4 };

        // 组装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("stats", status);
        paramMap.put("notifyTimes", notifyTime);
        Page<NotifyRecord> pages = notifyPersist.selectNotifyRecordPage(paramMap);
        int totalPages = pages.getTotalPages();

        while(pages.getPageNo() <= totalPages){
            logger.info(pages.getPageNo()+"------------"+ totalPages);
            List<NotifyRecord> list = pages.getResult();
            for(NotifyRecord notifyRecord:list){
                notifyRecord.setLastNotifyTime(new Date());
                notifyQueue.addElementToList(notifyRecord);
            }
            logger.info(String.format("调用通知服务.notifyRecordProvider.query(%s, %s)", pages.getPageNo(), pages.getTotalCount()));
            paramMap.put("pageNum",pages.getPageNo() + 1);
            pages = notifyPersist.selectNotifyRecordPage(paramMap);
        }
    }


}
