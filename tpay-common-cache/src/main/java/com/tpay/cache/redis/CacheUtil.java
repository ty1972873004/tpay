package com.tpay.cache.redis;

import com.tpay.cache.redis.impl.JedisClientSingleImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION redis锁工具类
 * @create 2017-06-15 14:14
 **/
@Configuration
public class CacheUtil {
    private static Logger logger = LoggerFactory.getLogger(CacheUtil.class);

    private static JedisClient jedisClient;

    private static JedisClientSingleImpl jedisClientSingleImpl;


    @Bean
    public static JedisClient getCache() {
        if (jedisClient == null) {
            synchronized (CacheUtil.class) {
                if (jedisClient == null) {
                    jedisClient = new JedisClientSingleImpl();
                }
            }
        }
        return jedisClient;
    }


    @Bean
    public JedisClientSingleImpl setRedisHelper() {
        jedisClientSingleImpl = getJedisClientSingle();
        return jedisClientSingleImpl;
    }

    public static JedisClientSingleImpl getJedisClientSingle() {
        if (jedisClientSingleImpl == null) {
            synchronized (CacheUtil.class) {
                if (jedisClientSingleImpl == null) {
                    jedisClientSingleImpl = new JedisClientSingleImpl();
                }
            }
        }
        return jedisClientSingleImpl;
    }


    public  static boolean getLock(String key) {
        try {
            if (!getJedisClientSingle().exists(key)) {
                synchronized (CacheUtil.class) {
                    if (!jedisClient.exists(key)) {
                        if (jedisClient.setnx(key, String.valueOf(System.currentTimeMillis())) == 1) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("getLock", e);
        }
        int expires = 1000 * 60 * 3;
        String currentValue = (String) jedisClient.get(key);
        if (currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis() - expires) {
            if (jedisClient.setnx("UNLOCK_" + key, "0")==1) {
                unlock(key);
                jedisClient.set("UNLOCK_" + key, "0", 1);
            }
            return getLock(key);
        }
        return false;
    }

    public  static void unlock(String key) {
        jedisClient.unlock(key);
    }

    public  static Object  get(String key) {
       return  jedisClient.get(key);
    }
}
