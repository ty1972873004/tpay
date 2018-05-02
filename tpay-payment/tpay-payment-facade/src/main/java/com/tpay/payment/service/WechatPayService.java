package com.tpay.payment.service;

import com.tpay.payment.dto.ThirdPayReq;
import com.tpay.payment.exceptions.PayException;

import java.util.Map;

/**
 * @desc  微信支付服务
 * @author Trazen
 * @since 2018-04-11
 * @version 1.0
 */
public interface WechatPayService {

    /**
     * 公众号支付
     * @param pay
     * @return
     * @throws PayException
     */
    Map<String, String> doWechatJsAPI(ThirdPayReq pay) throws PayException;

    /**
     * APP支付
     * @param pay
     * @return
     * @throws PayException
     */
    Map<String, String> doWechatAppAPI(ThirdPayReq pay) throws PayException;

    /**
     * 扫码支付
     * @param pay
     * @return
     * @throws PayException
     */
    Map<String, String> doWechatNativeAPI(ThirdPayReq pay) throws PayException;


    /**
     * H5支付
     * @param pay
     * @return
     * @throws PayException
     */
    Map<String, String> doWechatH5API(ThirdPayReq pay) throws PayException;
}
