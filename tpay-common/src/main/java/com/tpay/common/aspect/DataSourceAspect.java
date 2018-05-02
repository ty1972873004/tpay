package com.tpay.common.aspect;

import com.tpay.common.annotation.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author tuyong
 * @version 1.0
 * @desc  切换数据源切面
 *         业务代码中不同方法调用不同数据源
 * @create 2018-03-26 9:43
 **/
public class DataSourceAspect {

    private static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    public void pointCut(){};

    public void before(JoinPoint point)
    {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        logger.info(target + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                HandleDataSource.putDataSource(data.value());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void after(JoinPoint point) {
        HandleDataSource.clear();
    }

}
