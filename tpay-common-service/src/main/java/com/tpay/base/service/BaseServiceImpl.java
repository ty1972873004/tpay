package com.tpay.base.service;

import com.imadcn.framework.idworker.generator.IdGenerator;
import com.tpay.base.mapper.BaseMapper;
import com.tpay.base.model.BaseModel;
import com.tpay.cache.redis.CacheUtil;
import com.tpay.cache.redis.JedisClient;
import com.tpay.common.constants.Constants;
import com.tpay.common.utils.DataUtil;
import com.tpay.common.utils.ExceptionUtil;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.SerializeUtils;
import com.tpay.dao.plugins.util.Page;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-23 9:45
 **/
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    protected BaseMapper<T> baseMapper;

    @Autowired
    protected JedisClient jedisClient;

    @Autowired
    public IdGenerator generator;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public T insertOrUpdate(T t) {
        Integer result = 0;
        try {
            t.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            //执行新增数据操作
            if (t.getId() == null) {
                t.setId(generator.nextId());
                t.setCreateTime(new Timestamp(System.currentTimeMillis()));
                t.setEnable(true);
                result = baseMapper.insert(t);
            } else {//更新数据
                T org = this.queryById(t.getId());
                String lockKey = getLockKey(t.getId());
                if (CacheUtil.getLock(lockKey)) {
                    try {
                        T update = InstanceUtil.getDiff(org, t);
                        update.setId(t.getId());
                        result = baseMapper.update(update);
                       // CacheUtil.getCache().set(getCacheKey(t.getId()).getBytes(), SerializeUtils.serialize(t));
                    } finally {
                        CacheUtil.unlock(lockKey);
                    }
                } else {
                    sleep(20);
                    return insertOrUpdate(t);
                }
            }
        } catch (DuplicateKeyException e) {
            String msg = ExceptionUtil.getStackTraceAsString(e);
            logger.error(Constants.Exception_Head + msg, e);
            throw new RuntimeException("已经存在相同的配置.");
        } catch (Exception e) {
            String msg = ExceptionUtil.getStackTraceAsString(e);
            logger.error(Constants.Exception_Head + msg, e);
            throw new RuntimeException(msg);
        }
        return t;

    }

    @Override
    public Integer insertBatch(List<T> list) {
        return baseMapper.insertBatch(list);
    }

    @Override
    public T queryById(Long id) {
        return queryById(id, 1);
    }

    public T queryById(Long id,int times) {
        String key = getCacheKey(id);
        T record = null;

        //TODO

//        byte[] value = jedisClient.get(key.getBytes());
//        Object object = SerializeUtils.deserialize(value);
//        record = (T)object;
        if (record == null) {
            String lockKey = getLockKey(id);
            if (CacheUtil.getLock(lockKey)) {
                try {
                    record = baseMapper.get(id);
                    //jedisClient.set(key.getBytes(), SerializeUtils.serialize(record));
                }  catch (Exception e) {
                    logger.error(Constants.Exception_Head, e);
                }finally {
                    CacheUtil.unlock(lockKey);
                }
            } else {
                if (times > 3) {
                    return baseMapper.get(id);
                }
                logger.debug(getClass().getSimpleName() + ":" + id + " retry queryById.");
                sleep(20);
                return queryById(id, times + 1);
            }
        }
        return record;
    }




    @Override
    public List<T> getAll() {
        return baseMapper.selectAll();
    }

    @Override
    public List<T> queryList(Map<String, Object> params) {
        params.put("enable",1);
        List<Long> ids = baseMapper.selectIdPage(params);
        List<T> list = getList(ids);
        return list;
    }

    @Override
    public T queryOne(Map<String, Object> params) {
        T record = null;
        params.put("enable",1);
        List<Long> ids = baseMapper.selectIdPage(params);
        List<T> list = getList(ids);
        if(list!=null && !list.isEmpty() && list.size() == 1){
            record = list.get(0);
            return record;
        }else{
            return record;
        }
    }

    @Override
    /** 根据Id查询(默认类型T) */
    public List<T> getList(List<Long> ids) {
        List<T> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (int i = 0; i < ids.size(); i++) {
                list.add(null);
            }

            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (int i = 0; i < ids.size(); i++) {
                final int index = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        list.set(index, queryById(ids.get(index)));
                    }
                });
            }
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                logger.error("awaitTermination");
            }
        }
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteById(T t) {
        Integer i = 0;
        try {
            t.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            t.setUpdateBy(1L);
            i = baseMapper.deleteByPk(t);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        try {
            CacheUtil.getCache().del(getCacheKey(t.getId()));
        } catch (Exception e) {
            logger.error(Constants.Exception_Head, e);
        }
        return i;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteByMap(Map<String, Object> columnMap) {
        return baseMapper.deleteByParam(columnMap);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteBatch(List<Serializable> list) {
        return baseMapper.deleteBatch(list);
    }

    @Override
    public Page<T> query(Map<String, Object> params) {
        Page<Long> page = getPage(params);
        List<Long> records = baseMapper.selectIdPage(page, params);
        page.setResult(records);
        return getPage(page);
    }

    /** 分页查询 */
    public static Page<Long> getPage(Map<String, Object> params) {
        Integer current = 1;
        Integer size = 10;
        String orderBy = "id_";
        if (DataUtil.isNotEmpty(params.get("pageNum"))) {
            current = Integer.valueOf(params.get("pageNum").toString());
        }
        if (DataUtil.isNotEmpty(params.get("pageSize"))) {
            size = Integer.valueOf(params.get("pageSize").toString());
        }
        if (DataUtil.isNotEmpty(params.get("orderBy"))) {
            orderBy = (String) params.get("orderBy");
        }
        if (size == -1) {
            return new Page<Long>();
        }
        Page<Long> page = new Page<Long>(current, size, orderBy);
        page.setAsc(true);
        return page;
    }

    /** 根据Id查询(默认类型T) */
    public Page<T> getPage(Page<Long> ids) {
        if (ids != null) {
            Page<T> page = new Page<T>(ids.getPageNo(), ids.getPageSize());
            page.setTotalCount(ids.getTotalCount());
            List<T> records = InstanceUtil.newArrayList();
            for (int i = 0; i < ids.getResult().size(); i++) {
                records.add(null);
            }
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (int i = 0; i < ids.getResult().size(); i++) {
                final int index = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        records.set(index, queryById(ids.getResult().get(index)));
                    }
                });
            }
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                logger.error("awaitTermination");
            }
            page.setResult(records);
            return page;
        }
        return new Page<T>();
    }



    protected void sleep(int millis) {
        try {
            Thread.sleep(RandomUtils.nextLong(10, millis));
        } catch (InterruptedException e) {
            logger.error("", e);
        }
    }

    /** 获取缓存键值 */
    protected String getCacheKey(Object id) {
        String cacheName = getCacheKey();
        return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
    }

    /** 获取缓存键值 */
    protected String getLockKey(Object id) {
        String cacheName = getCacheKey();
        return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":LOCK:").append(id).toString();
    }

    private String getCacheKey() {
        Class<?> cls = getClass();
        String cacheName = Constants.cacheKeyMap.get(cls);
        if (StringUtils.isBlank(cacheName)) {
            CacheConfig cacheConfig = cls.getAnnotation(CacheConfig.class);
            if (cacheConfig == null || cacheConfig.cacheNames() == null || cacheConfig.cacheNames().length < 1) {
                cacheName = getClass().getName();
            } else {
                cacheName = cacheConfig.cacheNames()[0];
            }
            Constants.cacheKeyMap.put(cls, cacheName);
        }
        return cacheName;
    }
}
