package com.tpay.payment.utils;

import com.tpay.common.utils.InstanceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


/**
 * @desc 
 * @author Trazen
 * @since 2018-04-11
 * @version 1.0
 */
public class WechatSignature {

    /**
     * @param map 参与签名map
     * @return 签名
     */
    public static String getSign(Map<String,String> map,String mchKey){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,String> entry : map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + mchKey;
        result = MD5.MD5Encode(result).toUpperCase();
        return result;
    }

    public static void main(String[] args){
        Map<String,String> map = InstanceUtil.newHashMap();
        map.put("appId","wx5eb4a9e67862aa84");
        map.put("timeStamp","1414411784");
        map.put("nonceStr","pNwRTYG3szUUMq0n");
        map.put("package","prepay_id=wx080948379414578bb02312111377115967");
        map.put("signType","MD5");
         System.out.println(getSign(map,"3sdrew32432de34r445434353535fr43"));

    }



}
