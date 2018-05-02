package com.tpay.cache.aspect;

import com.tpay.cache.handler.BaseHandler;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author tuyong
 * @version 1.0
 * @desc 拦截{@link CacheEvict}注解
 * @create 2018-04-09 10:13
 **/
public class CacheEvictAspect {
    private BaseHandler handler;

    public CacheEvictAspect(BaseHandler handler) {
        this.handler = handler;
    }

    public Object around(ProceedingJoinPoint jp) throws Throwable {
        return handler.handleCacheEvict(jp);
    }
}
