package com.tpay.cache.aspect;

import com.tpay.cache.handler.BaseHandler;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author tuyong
 * @version 1.0
 * @desc 拦截{@link Cacheable}注解
 * @create 2018-04-08 15:51
 **/
public class CacheableAspect {

    private BaseHandler handler;

    public CacheableAspect(BaseHandler handler) {
        this.handler = handler;
    }

    public Object around(ProceedingJoinPoint jp) throws Throwable {
        return handler.handleCacheable(jp);
    }
}
