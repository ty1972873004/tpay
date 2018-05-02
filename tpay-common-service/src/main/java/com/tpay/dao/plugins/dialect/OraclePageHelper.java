package com.tpay.dao.plugins.dialect;

import com.tpay.dao.plugins.helper.AbstractHelper;
import com.tpay.dao.plugins.util.Page;

/**
 * @author tuyong
 * @version 1.0
 * @desc Oracle 数据库方言
 * @create 2018-03-28 12:00
 **/
public class OraclePageHelper extends AbstractHelper{

    /**
     * 得到分页的SQL
     * @param sql
     * @param page 偏移量  位置
     * @return
     */
    public static String getLimitString(String sql, Page<?> page) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if (sql.toLowerCase().endsWith(" for update")) {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }

        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

        pagingSelect
                .append("select * from ( select row_.*, rownum rownum_ from ( ");

        pagingSelect.append(sql);

        pagingSelect.append(" ) row_ ) where rownum_ > " + page.getOffset()
                + " and rownum_ <= " + (page.getOffset() + page.getLimit()));

        if (isForUpdate) {
            pagingSelect.append(" for update");
        }

        return pagingSelect.toString();
    }

    /**
     * 得到查询总数的sql
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
}
