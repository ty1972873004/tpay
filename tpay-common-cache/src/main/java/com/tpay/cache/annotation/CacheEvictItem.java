package com.tpay.cache.annotation;

/**
 * @desc  缓存删除注解
 *        一个CacheDelItem表示一个要删除的缓存
 * @author Trazen
 * @since 2018-04-09
 * @version 1.0
 */
public @interface CacheEvictItem {

    /**
     * 要删除的缓存key,支持Spring EL表达式
     */
    String key();
}
