package com.tpay.dao.plugins.dialect;

import com.tpay.dao.plugins.util.Page;

/**
 * @author tuyong
 * @version 1.0
 * @desc oraclef方言
 * @create 2018-03-28 11:56
 **/
public class OracleDialect extends Dialect {

    @Override
    public String getLimitString(String sql, Page<?> page) {
        return OraclePageHelper.getLimitString(sql, page);
    }

    @Override
    public String getCountString(String sql) {
        return OraclePageHelper.getCountString(sql);
    }
}
