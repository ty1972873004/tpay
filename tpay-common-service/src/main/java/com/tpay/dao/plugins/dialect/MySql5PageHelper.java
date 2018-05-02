package com.tpay.dao.plugins.dialect;

import com.tpay.dao.plugins.helper.AbstractHelper;
import com.tpay.dao.plugins.util.Page;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tuyong
 * @version 1.0
 * @desc MySQL数据库方言 分页
 * @create 2018-03-28 9:33
 **/
public class MySql5PageHelper extends AbstractHelper{


    /**
     * 得到查询总数的sql
     * @param querySelect
     * @return
     */
    public static String getCountString(String querySelect) {

        querySelect = getLineSql(querySelect);
        int orderIndex = getLastOrderInsertPoint(querySelect);

        int formIndex = getAfterFormInsertPoint(querySelect);
        String select = querySelect.substring(0, formIndex);

        // 如果SELECT 中包含 DISTINCT 只能在外层包含COUNT
        if (select.toLowerCase().indexOf("select distinct") != -1 || querySelect.toLowerCase().indexOf("group by") != -1) {
            return new StringBuffer(querySelect.length()).append("select count(1) count from (").append(querySelect.substring(0, orderIndex)).append(" ) t").toString();
        } else {
            return new StringBuffer(querySelect.length()).append("select count(1) count ").append(querySelect.substring(formIndex, orderIndex)).toString();
        }
    }


    /**
     * 得到分页的SQL
     * @param querySelect
     * @param page  获取 偏移量 位置 排序列
     * @return 分页SQL
     */
    public static String getLimitString(String querySelect, Page<?> page) {
        querySelect = getLineSql(querySelect);
        //添加排序条件
        StringBuilder sql = new StringBuilder(querySelect);
        if(StringUtils.isNotEmpty(page.getOrderByField())){
            sql.append(" order by "+page.getOrderByField());
        }
        if(page.getAsc()){
            sql.append(" asc ");
        }else{
            sql.append(" desc ");
        }
        sql.append(" limit " + page.getOffset() + " ," + page.getLimit());
        return sql.toString();
    }

}
