package com.tpay.base.service;

import com.tpay.base.model.BaseModel;
import com.tpay.common.annotation.DataSource;
import com.tpay.dao.plugins.util.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * @desc 
 * @author Trazen
 * @since 2018-03-23
 * @version 1.0
 */
public interface BaseService<T extends BaseModel> {

    /**
     * 插入or更新（是否传入id判断）
     * @param t
     * @return
     */
    @DataSource("write")
    T insertOrUpdate(T t);

    /**
     * 批量插入
     * @param list
     * @return
     */
    @DataSource("write")
    Integer insertBatch(List<T> list);

    /**
     * 根据PK查找
     * @param id
     * @return
     */
    @DataSource("read")
    T queryById(Long id);

    /**
     * 获取所有记录
     * @return
     */
    @DataSource("read")
    List<T> getAll();

    /**
     * 条件查询
     * @param params
     * @return
     */
    @DataSource("read")
    List<T> queryList(Map<String, Object> params);

    /**
     * 查询唯一记录
     * @param params
     * @return
     */
    @DataSource("read")
    T queryOne(Map<String, Object> params);

    /**
     * 根据id删除
     * @param t
     * @return
     */
    @DataSource("write")
    Integer deleteById(T t);

    /**
     * 多条件删除
     * @param columnMap
     * @return
     */
    @DataSource("write")
    Integer deleteByMap(Map<String, Object> columnMap);

    /**
     * 批量删除
     * @param list
     * @return
     */
    @DataSource("write")
    Integer deleteBatch(List<Serializable> list);

    /**
     * 分页查找
     * @param params
     * @return
     */
    @DataSource("write")
    Page<T> query(Map<String, Object> params);


    /**
     * 获取List列表
     * @param ids
     * @return
     */
     @DataSource("read")
     List<T> getList(List<Long> ids) ;





}
