package com.tpay.dao.plugins.dialect;

import com.tpay.dao.plugins.util.Page;

/**
 * @author tuyong
 * @version 1.0
 * @desc MSSQL 数据库方言
 * @create 2018-03-28 11:42
 **/
public class MSSqlDialect extends Dialect {


    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, Page<?> page) {
        return MSSqlPageHelper.getLimitString(sql, page);
    }

    @Override
    public String getCountString(String sql) {
        return MSSqlPageHelper.getCountString(sql);
    }


}
