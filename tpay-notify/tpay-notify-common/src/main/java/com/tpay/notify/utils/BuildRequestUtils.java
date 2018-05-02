package com.tpay.notify.utils;

import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.MD5Util;
import com.tpay.common.utils.MapUtils;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc 构建mq通知url
 * @create 2018-04-27 14:51
 **/
public class BuildRequestUtils {

    /**
     * 构建第三方支付通知URL
     * @param notifyUrl
     * @param returnCode
     * @param returnMsg
     * @param mchId
     * @param outTradeNo
     * @param tradeStatus
     * @param totalAmount
     * @param payTime
     * @param errCode
     * @param errCodeMsg
     * @return
     */
    public static String buildThirdCallBackUrl(String notifyUrl,String returnCode,String returnMsg,String mchId,String outTradeNo,String tradeStatus,String totalAmount,String payTime,
                                                   String errCode,String errCodeMsg){

        Map<String, String> paramValues = InstanceUtil.newHashMap();
        paramValues.put("returnCode",returnCode);
        paramValues.put("returnMsg",returnMsg);
        paramValues.put("mchId",mchId);
        paramValues.put("outTradeNo",outTradeNo);
        paramValues.put("tradeStatus",tradeStatus);
        paramValues.put("totalAmount",totalAmount);
        paramValues.put("payTime",payTime);
        paramValues.put("errCode",errCode);
        paramValues.put("errCodeMsg",errCodeMsg);
        String param = MapUtils.getParamsOrderByKey(paramValues);
        StringBuffer paramBuff = new StringBuffer();

        String sign = MD5Util.signature(param);
        paramBuff.append("?returnCode=" + returnCode);
        paramBuff.append("&returnMsg=" + returnMsg);
        paramBuff.append("&mchId=" + mchId);
        paramBuff.append("&outTradeNo=" + outTradeNo);
        paramBuff.append("&tradeStatus=" + tradeStatus);
        paramBuff.append("&totalAmount=" + totalAmount);
        paramBuff.append("&payTime=" + payTime);
        paramBuff.append("&errCode=" + errCode);
        paramBuff.append("&errCodeMsg=" + errCodeMsg);
        paramBuff.append("&sign=" + sign);
        return notifyUrl + paramBuff.toString();
    }
}
