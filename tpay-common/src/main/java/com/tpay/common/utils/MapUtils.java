package com.tpay.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-18 14:47
 **/
public class MapUtils {

    /**
     * 得到参数列表字符串
     * @param paramValues 参数map对象
     * @return  参数列表字符串
     */
    public static String getParams(Map<String, String> paramValues)
    {
        String params="";
        Set<String> key = paramValues.keySet();
        String beginLetter="";

        for (Iterator<String> it = key.iterator(); it.hasNext();) {
            String s = (String) it.next();
            if (params.equals(""))
            {
                params += beginLetter + s + "=" + paramValues.get(s);
            }
            else
            {
                params += "&" + s + "=" + paramValues.get(s);
            }
        }
        return params;
    }


    /**
     * 按照key排序得到参数列表字符串
     * @param paramValues 参数map对象
     * @return  参数列表字符串
     */
    public static String getParamsOrderByKey(Map<String, String> paramValues)
    {
        String params="";
        Set<String> key = paramValues.keySet();
        String beginLetter="";

        List<String> paramNames = new ArrayList<String>(paramValues.size());
        paramNames.addAll(paramValues.keySet());
        Collections.sort(paramNames);
        for (String paramName : paramNames) {

            if (params.equals(""))
            {
                params += beginLetter + paramName + "=" + paramValues.get(paramName);
            }
            else
            {
                params += "&" + paramName + "=" + paramValues.get(paramName);
            }
        }

        return params;
    }

    public static void main(String[] args){
        Map<String,String> signMap=new HashMap<String,String>();
        signMap.put("appId", "aaabbbccc");
        signMap.put("thirdCustId", "gl00001032");
        signMap.put("body", "测试商品");
        signMap.put("detail", "测试商品详情");
        signMap.put("tranAmount", "0.01");
        signMap.put("tradeNo", "201712011709210001");
        signMap.put("notifyUrl", "http://27.154.231.158:8092/epay/notify");

        System.out.println(" params-->"+getParamsOrderByKey(signMap));

    }


}
