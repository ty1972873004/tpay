package com.tpay.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @desc  缓存删除注解
 * @author Trazen
 * @since 2018-04-08
 * @version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheEvict {

    /**
     * {@link CacheEvictItem}数组,可以一个或多个,表示删除一个或多个缓存
     */
    CacheEvictItem[] items();

    /**
     * 缓存删除条件,满足条件才删除缓存,支持Spring EL表达式
     */
    String condition() default "";

}
