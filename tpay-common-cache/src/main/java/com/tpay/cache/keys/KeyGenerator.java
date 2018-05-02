package com.tpay.cache.keys;

import java.lang.reflect.Method;


/**
 * @desc  Key生成器接口
 * @author Trazen
 * @since 2018-04-08
 * @version 1.0
 */
public interface KeyGenerator<T> {

    /**
     * 获取key
     * @param target
     * @param method
     * @param params
     * @return
     */
    T getKey(Object target, Method method, Object... params);

}
