package com.tpay.cache.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @desc  jedis接口
 * @author Trazen
 * @since 2018-03-23
 * @version 1.0
 */
public interface JedisClient {
    /**
     * 获取缓存
     * @param key
     * @return
     */
    Object get(String key);

    byte[] get(byte[] key);

    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);



    /**
     * 设置缓存
     * @param key
     * @param value
     * @param expire 过去时间
     * @return
     */
    String set(String key, String value, int expire);

    String set(final String key, final Serializable value);

    String set(byte[] key, byte[] value, int expire);

    /**
     * 哈希 获取缓存
     * @param hkey
     * @param key
     * @return
     */
    String hget(String hkey, String key);

    /**
     * 哈希 设置缓存
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    Long hset(String hkey, String key, String value);

    /**
     *获取自增值
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     *设置有效期
     * @param key
     * @param second
     * @return
     */
    Long expire(String key, int second);

    /**
     *获取有效期
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 删除缓存
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * 删除缓存
     * @param key
     * @return
     */
    Long del(byte[] key);

    /**
     * 删除哈希 缓存
     * @param hKey
     * @param key
     * @return
     */
    Long hDel(String hKey, String key);

    /**
     * 通配符查询
     * 集群禁止使用
     * @param pattern
     * @return
     */
    Set<byte[]> keys(String pattern);

    /**
     * 刷新数据 codis禁止使用
     */
    void flushDB();

    /**
     * 获取数据库key数量
     * 集群禁止使用
     * @return
     */
    Long dbSize();

    /**
     * 判断keys是否存在
     * @param key
     * @return
     */
    Boolean exists(String key);

    /**
     * 指定的 key 不存在时，为 key 设置指定的值。
     * @param key
     * @param value
     * @return
     */
    Long setnx(final String key, final String value);

    /**
     * 删除key
     * @param key
     */
    void unlock(String key);

    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    String set(byte[] key, byte[] value);

    /**
     * 移除集合中的一个或多个成员元素
     * @param key
     * @param members
     * @return
     */
    Long sRem(final String key, final String... members);

    /**
     * 添加元素到集合 key 当中
     * @param key
     * @param value
     * @param seconds
     */
    void sAdd(String key, String value, int seconds);

    /**
     * 返回集合所有成员
     * @param key
     * @return
     */
    Set<String> sMembers(final String key);

    /**
     * 获取存储在集合中的元素的数量
     * @param key
     * @return
     */
    Long sCard(final String key);

    /**
     *  根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
     * @param key
     * @param count
     * @param value
     */
    void lRem(String key, long count, String value);

    /**
     * 用于返回列表的长度
     * @param key
     * @return
     */
    Long lLen(final String key);

    /**
     * 返回列表中指定区间内的元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lRange(final String key, final long start, final long end);

    /**
     * 将所有指定的值插入到存于 key 的列表的头部
     * @param key
     * @param strings
     */
    void lPush(String key, String... strings);


    /**
     *
     * @param key
     * @param value
     */
    void lSet(String key,String value);

    /**
     * 使用 Lua 解释器执行脚本。
     * @param script
     * @param keys
     * @param args
     * @return
     */
    Long eval(String script, List<String> keys, List<String> args);

    /**
     *
     * @param key
     * @param field
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T hGet(String key,String field,Class<T> clazz);

}
