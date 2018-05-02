package com.tpay.sys.listener;

import com.tpay.sys.service.SequenceService;
import com.tpay.sys.service.SysDicService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-17 17:11
 **/
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
    
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            logger.info(">>>>> spring初始化完毕 <<<<<");
            Object sysDicService = contextRefreshedEvent.getApplicationContext().getBean(SysDicService.class);
            Object sequenceService = contextRefreshedEvent.getApplicationContext().getBean(SequenceService.class);
            try {
                Method initMethod = sysDicService.getClass().getMethod("init");
                initMethod.invoke(sysDicService);

                Method createNodeMethod = sequenceService.getClass().getMethod("createDataNode");
                createNodeMethod.invoke(sequenceService);


            } catch (Exception e) {
                logger.error("初始化service的init方法异常", e);
                e.printStackTrace();
            }

        }


    }
}
