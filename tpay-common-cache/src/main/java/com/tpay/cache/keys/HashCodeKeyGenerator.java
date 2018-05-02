package com.tpay.cache.keys;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Method;

/**
 * @desc 生成对象的HashCode值作为缓存的key
 * @author Trazen
 * @since 2018-04-08
 * @version 1.0
 */
public class HashCodeKeyGenerator implements KeyGenerator<Integer> {

    @Override
    public Integer getKey(Object target, Method method, Object... params) {
        KeyWrapper keyWrapper = new KeyWrapper(target.getClass().getName(), method.getName(), params);
        int code = HashCodeBuilder.reflectionHashCode(keyWrapper.toString(), true);
        return code;
    }

}
