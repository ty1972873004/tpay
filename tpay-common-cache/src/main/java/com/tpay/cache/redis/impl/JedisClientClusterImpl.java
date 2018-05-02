package com.tpay.cache.redis.impl;

import com.tpay.cache.redis.JedisClient;
import com.tpay.common.utils.JsonUtils;
import com.tpay.common.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.JedisCluster;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-23 10:33
 **/





public class JedisClientClusterImpl implements JedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String set(byte[] key, byte[] value) {
        return null;
    }

    @Override
    public  Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long setnx(String key, String value) {
        return jedisCluster.setnx(key,value);
    }

    @Override
    public void unlock(String key) {
        del(key);
    }

    @Override
    public Object get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public byte[] get(byte[] key) {
        return new byte[0];
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String set(String key, String value, int expire) {
        value = jedisCluster.set(key, value);
        if (expire != 0) {
            jedisCluster.expire(key, expire);
        }
        return value;
    }

    @Override
    public String set(final String key, final Serializable value) {
        return jedisCluster.set(key, JsonUtils.objectToJson(value));
    }

    @Override
    public String set(byte[] key, byte[] value, int expire) {
        return null;
    }

    @Override
    public String hget(String hkey, String key) {
        return jedisCluster.hget(hkey, key);
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        return jedisCluster.hset(hkey, key, value);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long expire(String key, int second) {
        return jedisCluster.expire(key, second);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public Long del(byte[] key) {
        return jedisCluster.del(key);
    }

    @Override
    public Long hDel(String hKey, String key) {
        return jedisCluster.hdel(hKey, key);
    }

    @Override
    public Set<byte[]> keys(String pattern) {
        return null;
    }

    @Override
    public void flushDB() {
    }

    @Override
    public Long dbSize() {
        return null;
    }

    @Override
    public Long sRem(String key, String... members) {
        return jedisCluster.srem(key,members);
    }

    @Override
    public void sAdd(String key, String value, int seconds) {
        jedisCluster.sadd(key, value);
        if(seconds!=0){
            jedisCluster.expire(key, seconds);
        }

    }

    @Override
    public Set<String> sMembers(String key) {
        return jedisCluster.smembers(key);
    }

    @Override
    public Long sCard(String key) {
        return jedisCluster.scard(key);
    }

    @Override
    public void lRem(String key, long count, String value) {
        jedisCluster.lrem(key, count, value);
    }

    @Override
    public Long lLen(String key) {
        return jedisCluster.llen(key);
    }

    @Override
    public List<String> lRange(String key, long start, long end) {
        return jedisCluster.lrange(key, start, end);
    }

    @Override
    public void lPush(String key, String... strings) {
        jedisCluster.lpush(key,strings);
    }


    @Override
    public void lSet(String key, String value) {
        jedisCluster.lpush(key,value);
    }


    @Override
    public Long eval(String script, List<String> keys, List<String> args) {
        Long count = 0L;
        Object obj = jedisCluster.eval(script,keys,args);
        if(!StringUtil.isEmpty(obj)){
            count = Long.parseLong(obj+"");
        }
        return count;
    }

    @Override
    public <T> T hGet(String key, String field, Class<T> clazz) {
        String value = hget(key,field);
        T result = JsonUtils.jsonStringToEntity(value,clazz);
        return result;
    }
}
