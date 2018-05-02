package com.tpay.payment.service;

import com.tpay.payment.dto.ThirdPayReq;
import com.tpay.payment.exceptions.PayException;

import java.util.Map;

/**
 * @desc 支付宝支付服务
 * @author Trazen
 * @since 2018-04-10
 * @version 1.0
 */
public interface AliPayService {

    /**
     * 支付宝wap手机网站支付
     * @param pay
     * @return
     * @throws PayException
     */
    Map<String, Object> doAlipayWapPay(ThirdPayReq pay) throws PayException;

    /**
     * 支付宝PC网页支付
     * @param pay
     * @return
     * @throws PayException
     */
    Map<String, Object> doAlipayPcPay(ThirdPayReq pay) throws PayException;


    /**
     * 支付宝当面付扫码支付
     * @param pay
     * @return
     * @throws PayException
     */
    Map<String, Object> doAlipayQrPay(ThirdPayReq pay) throws PayException;


    /**
     * 支付宝APP支付
     * @param pay
     * @return
     * @throws PayException
     */
    Map<String, Object> doAlipayMobilePay(ThirdPayReq pay) throws PayException;

}
