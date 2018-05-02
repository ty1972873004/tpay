package com.tpay.dao.plugins.dialect;

import com.tpay.dao.plugins.util.Page;

/**
 * @author tuyong
 * @version 1.0
 * @desc  数据库方言抽象类
 * @create 2018-03-27 14:03
 **/
public abstract class Dialect {


    /**
     * 得到分页sql
     * @param sql
     * @param page
     * @return
     */
    public abstract String getLimitString(String sql, Page<?> page);


    /**
     * 得到总数量 sql
     * @param sql
     * @return
     */
    public abstract String getCountString(String sql);

}