package com.tpay.dao.plugins.dialect;

import com.tpay.dao.plugins.helper.AbstractHelper;
import com.tpay.dao.plugins.util.Page;

/**
 * @author tuyong
 * @version 1.0
 * @desc mssql
 * @create 2018-03-28 11:43
 **/
public class MSSqlPageHelper  extends AbstractHelper {


    /**
     * 得到查询总数的sql
     * @param querySelect
     * @return 组装后的sql
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
     * @param page 偏移量 位置
     * @return 分页SQL
     */
    public static String getLimitString(String querySelect, Page<?> page) {
        querySelect = getLineSql(querySelect);
        int selectIndex = querySelect.toUpperCase().lastIndexOf("SELECT");
        if (selectIndex > -1) {
            querySelect = querySelect.substring(0, selectIndex) + "SELECT TOP " + (page.getLimit() + page.getOffset()) + querySelect.substring(selectIndex + 6);
        }
        String sql = "SELECT * FROM(SELECT ROW_NUMBER () OVER (ORDER BY getdate()) rownum,* FROM( " + querySelect + " ) A ) B WHERE B.rownum > " + page.getOffset() + " AND B.rownum <= "
                + (page.getLimit() + page.getOffset());
        return sql;

    }



}
