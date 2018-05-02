package com.tpay.common.aspect;

/**
 * @author tuyong
 * @version 1.0
 * @desc 获取数据源
 * @create 2018-03-26 9:10
 **/
public class HandleDataSource {

    private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();

    public static void putDataSource(String dataSource){
        dataSourceHolder.set(dataSource);
    }

    public static String getDataSource(){
        return dataSourceHolder.get();
    }

    public static void clear(){
        dataSourceHolder.remove();
    }



}
