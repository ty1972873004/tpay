package com.tpay.dao.plugins.dialect;

import com.tpay.dao.plugins.util.Page;

/**
 * @author tuyong
 * @version 1.0
 * @desc MySQL数据库方言
 * @create 2018-03-28 9:34
 **/
public class MySql5Dialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, Page<?> page) {
        return MySql5PageHelper.getLimitString(sql, page);
    }

    @Override
    public String getCountString(String sql) {
        return MySql5PageHelper.getCountString(sql);
    }
}
