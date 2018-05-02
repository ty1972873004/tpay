package com.tpay.dao.plugins.dialect;

import org.apache.ibatis.session.Configuration;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-28 9:30
 **/
public class DialectFactory {

    public static String dialectClass = null;

    public static Dialect buildDialect(Configuration configuration) {
        if (dialectClass == null) {
            synchronized (DialectFactory.class) {
                if (dialectClass == null) {
                    dialectClass = configuration.getVariables().getProperty("dialectClass");
                }
            }
        }
        Dialect dialect = null;
        try {
            //利用反射机制获取方言对象
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialect;
    }
}