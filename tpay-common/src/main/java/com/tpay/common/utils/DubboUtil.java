package com.tpay.common.utils;

import com.alibaba.dubbo.config.spring.ReferenceBean;

import org.springframework.context.ApplicationContext;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @desc 
 * @author Trazen
 * @since 2018-04-16
 * @version 1.0
 */
public class DubboUtil {
    private DubboUtil() {
    }

    private static final ConcurrentMap<String, ReferenceBean<?>> referenceConfigs = new ConcurrentHashMap<String, ReferenceBean<?>>();

    /** 获取Dubbo服务 */
    public static Object refer(ApplicationContext applicationContext, String interfaceName) {
        String key = "/" + interfaceName + ":";
        ReferenceBean<?> referenceConfig = referenceConfigs.get(key);
        if (referenceConfig == null) {
            referenceConfig = new ReferenceBean<Object>();
            referenceConfig.setInterface(interfaceName);
            if (applicationContext != null) {
                referenceConfig.setApplicationContext(applicationContext);
                try {
                    referenceConfig.afterPropertiesSet();
                } catch (Exception e) {
                    throw new IllegalStateException(e.getMessage(), e);
                }
            }
            referenceConfigs.putIfAbsent(key, referenceConfig);
            referenceConfig = referenceConfigs.get(key);
        }
        return referenceConfig.get();
    }
}
