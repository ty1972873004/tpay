package com.tpay.common.aspect;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc 数据源选择
 * @create 2018-03-26 9:19
 **/
public class ChooseDataSource extends AbstractRoutingDataSource {

    public static Map<String, List<String>> METHODTYPE = new HashMap<String, List<String>>();

    /**
     * 获取数据源名称
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return HandleDataSource.getDataSource();
    }

    /**
     * 设置方法名前缀对应的数据源
     * @param map
     */
    public void setMethodType(Map<String,String> map){
        for (String key : map.keySet()) {
            List<String> v = new ArrayList<String>();
            String[] types = map.get(key).split(",");
            for (String type : types) {
                if (StringUtils.isNotBlank(type)) {
                    v.add(type);
                }
            }
            METHODTYPE.put(key, v);
        }
    }
}
