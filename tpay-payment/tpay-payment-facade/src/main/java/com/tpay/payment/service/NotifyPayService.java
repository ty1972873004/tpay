package com.tpay.payment.service;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-10 10:11
 **/
public interface NotifyPayService {

    /**
     * 支付宝回调通知处理
     * @param map
     * @return
     */
    Map<String,Object> doAliPayNotify(Map<String, String> map);

    /**
     * 微信支付回调处理
     * @param map
     * @return
     */
    Map<String,Object> doWechatPayNotify(Map<String, String> map);
}
