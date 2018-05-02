package com.tpay.cache.handler;

import com.alibaba.fastjson.JSON;
import com.tpay.cache.annotation.CacheEvict;
import com.tpay.cache.annotation.CacheEvictItem;
import com.tpay.cache.annotation.Cacheable;
import com.tpay.cache.keys.HashCodeKeyGenerator;
import com.tpay.cache.keys.KeyGenerator;
import com.tpay.cache.keys.SpringELParser;
import com.tpay.cache.redis.JedisClient;
import com.tpay.common.constants.Constants;
import com.tpay.common.utils.AssertUtil;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author tuyong
 * @version 1.0
 * @desc 缓存处理类
 * @create 2018-04-08 15:54
 **/
public class TpayHandler implements BaseHandler{

    private static Logger logger = LoggerFactory.getLogger(TpayHandler.class);

    private JedisClient jedisClient;

    private final String  nameSpace;

    private final KeyGenerator<Integer> keyGenerator;

    private final SpringELParser elParser;

    private static final String  MUTEX_PREFIX = "mutex_temp_";

    private static final String  MUTEX_VAL   = "temp_mutex_val";



    public TpayHandler(JedisClient jedisClient){
        this.jedisClient = jedisClient;
        this.nameSpace = Constants.CACHE_NAMESPACE;
        keyGenerator = new HashCodeKeyGenerator();
        elParser = new SpringELParser();
    }


    @Override
    public Object handleCacheable(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method =  signature.getMethod();
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        Class returnType = ((MethodSignature)jp.getSignature()).getReturnType();

        //获取注解内参数
        String key = cacheable.key();
        int expireTime = cacheable.expireTime();
        String condition = cacheable.condition();
        boolean sync = cacheable.sync();
        Object[] args = jp.getArgs();
        AssertUtil.assertTrue(expireTime >= 0, "超时时间不能为负");

        logger.info("key={},expireTime={},condition={},sync={},args={}", key, expireTime, condition, sync, args);

        //如果不满足缓存条件直接返回
        if (StringUtils.isNotBlank(condition) && !isCacheable(condition, args)) {
            return jp.proceed();
        }

        //组装CacheKey
        if (StringUtils.isBlank(key)) {
            key = (keyGenerator.getKey(jp.getTarget(), method, args)).toString();
        } else {
            key = getKeySpELValue(key, args);
        }
        return  getValue(jp, key, sync, expireTime,returnType);
    }

    private Object getValue(ProceedingJoinPoint jp, String key, boolean sync, int expireTime,Class returnType) throws Throwable {
        String cacheKey = nameSpace + key;
        Object object = jedisClient.get(cacheKey);
        /**
         * 缓存命中
         */
        if (object != null) {
            return JSON.parseObject((String)object,returnType);
        }
        Object proceedRet = null;
        //是否同步判断
        if (sync) {
            String mutexKey = nameSpace + MUTEX_PREFIX + key;
            if (jedisClient.setnx(mutexKey, MUTEX_VAL) == 1L) {
                proceedRet = jp.proceed();
                //放入缓存
                if (proceedRet != null) {
                    jedisClient.set(cacheKey, JSON.toJSONString(proceedRet));
                    jedisClient.expire(cacheKey,expireTime);
                    jedisClient.del(mutexKey);
                }
                return proceedRet;
            } else {
                Thread.sleep(50);
                return getValue(jp, cacheKey, sync, expireTime,returnType);
            }
        } else {
            proceedRet = jp.proceed();
            //放入缓存
            if (proceedRet != null) {
                jedisClient.set(cacheKey, JSON.toJSONString(proceedRet));
            }
            return proceedRet;
        }
    }


    @Override
    public Object handleCacheEvict(ProceedingJoinPoint jp) throws Throwable {
        Object proceedRet = jp.proceed();

        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        CacheEvict cacheEvict = method.getAnnotation(CacheEvict.class);
        String condition = cacheEvict.condition();

        //不满足缓存删除条件
        if (StringUtils.isNotBlank(condition) && !isDel(condition, proceedRet)) {
            return proceedRet;
        }

        try {
            CacheEvictItem[] items = cacheEvict.items();
            for (CacheEvictItem item : items) {
                String keySpEL = item.key();
                if (StringUtils.isBlank(keySpEL)) {
                    continue;
                }
                String key = getKeySpELValue(keySpEL, jp.getArgs());
                String cacheKey = nameSpace + key;
                jedisClient.del(cacheKey);
            }
        } catch (Exception e) {
            logger.error("删除缓存出错", e);
        }
        return proceedRet;
    }


    private boolean isCacheable(String conditionSpEl, Object[] args) {
        return elParser.getELBooleanValue(conditionSpEl, args);
    }

    private String getKeySpELValue(String keySpEl, Object[] args) {
        return elParser.getELStringValue(keySpEl, args);
    }

    private boolean isDel(String conditionSpE, Object retVal) {
        return elParser.getELRetVal(conditionSpE, null, retVal);
    }
}
