package com.tpay.cache.handler;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author tuyong
 * @version 1.0
 * @desc 缓存处理接口
 * @create 2018-04-08 15:52
 **/
public interface BaseHandler {

    /**
     * 处理缓存
     * @param jp 切面连接点
     * @return 执行结果
     * @throws Throwable
     */
    Object handleCacheable(ProceedingJoinPoint jp) throws Throwable;


    /**
     * 处理删除缓存
     * @param jp 切面连接点
     * @return 执行结果
     * @throws Throwable
     */
    Object handleCacheEvict(ProceedingJoinPoint jp) throws Throwable;

}
