package com.tpay.cache.redis;

import com.tpay.common.utils.SpringContextUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION redis锁工具类
 * @create 2017-06-15 14:14
 **/
public class CacheUtil {
    private static Logger logger = LoggerFactory.getLogger(CacheUtil.class);

    private static JedisClient jedisClient;


    public static JedisClient getCache() {
        if (jedisClient == null) {
            synchronized (CacheUtil.class) {
                if(jedisClient==null) {
                    jedisClient = (JedisClient) SpringContextUtil.getBean("jedisClient");
                }
            }
        }
        return jedisClient;
    }


    public  static boolean getLock(String key) {
        if(jedisClient==null) {
            jedisClient = (JedisClient) SpringContextUtil.getBean("jedisClient");
        }
        try {
            if (!jedisClient.exists(key)) {
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
