package com.tpay.common.utils;

import com.tpay.common.config.Resources;
import com.tpay.common.constants.Constants;
import com.tpay.common.constants.WebConstants;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc Tpay工具类
 * @create 2018-05-07 11:33
 **/
public class TpayUtils {

    private static Logger logger = LoggerFactory.getLogger(TpayUtils.class);

    public static Map<String,Object> setSuccessResultMap(String msg,Object data){
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
        resultMap.put(Constants.CODE, WebConstants.HANDLE_SUCCESS);
        if(StringUtils.isEmpty(msg)){
            resultMap.put(Constants.MSG, Resources.getMessage(WebConstants.HANDLE_SUCCESS));
        }else{
            resultMap.put(Constants.MSG, msg);
        }
        resultMap.put(Constants.DATA,data);
        return resultMap;
    }


    public static Map<String,Object> setFailResultMap(String msg,Object data){
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
        resultMap.put(Constants.CODE, WebConstants.HANDLE_FAIL);
        if(StringUtils.isEmpty(msg)){
            resultMap.put(Constants.MSG, Resources.getMessage(WebConstants.HANDLE_FAIL));
        }else{
            resultMap.put(Constants.MSG, msg);
        }
        resultMap.put(Constants.DATA,data);
        return resultMap;
    }

    public static Map<String,Object> setResultMap(String code,String msg,Object data){
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
        resultMap.put(Constants.CODE, code);
        if(StringUtils.isEmpty(msg)){
            resultMap.put(Constants.MSG, Resources.getMessage(code));
        }else{
            resultMap.put(Constants.MSG, msg);
        }
        resultMap.put(Constants.DATA,data);
        return resultMap;
    }

    public static boolean verifySign(Map<String,String> params,String sign) throws Exception{
        return RsaUtils.rsaCheck(params,sign);
    }

    public static String getSignMapKeys(Map<String,String> signMap){
        List<String> keys = new ArrayList<String>(signMap.keySet());
        String signKey = StringUtils.join(keys,",");
        return signKey;
    }

}
