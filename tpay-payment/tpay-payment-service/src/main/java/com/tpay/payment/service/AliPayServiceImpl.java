package com.tpay.payment.service;

import com.tpay.common.utils.InstanceUtil;
import com.tpay.order.mapper.PayChannelMapper;
import com.tpay.order.model.PayChannel;
import com.tpay.payment.base.to.Pay;
import com.tpay.payment.dto.ThirdPayReq;
import com.tpay.payment.exceptions.PayException;
import com.tpay.payment.factory.PayServiceFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-10 9:20
 **/
@Service("aliPayService")
public class AliPayServiceImpl implements AliPayService {

    private static Logger logger = LoggerFactory.getLogger(AliPayServiceImpl.class);

    @Autowired
    private PayChannelMapper payChannelMapper;

    @Override
    public Map<String, Object> doAlipayWapPay(ThirdPayReq pay) throws PayException {
        Map<String, Object> resultMap = InstanceUtil.newHashMap();

        Pay alipay = new Pay();
        alipay.setOrderDesc("tPay测试交易");
        alipay.setOrderMoney(pay.getOrderMoney().toString());
        alipay.setOrderName("tPay测试交易");
        alipay.setOrderNo(pay.getOrderNo());

        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",pay.getChannelId());
        param.put("mchId",pay.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);

        alipay.setPayConfig(payChannel.getParam());

        Map<String, String> result = PayServiceFactory.getPayInstance("aliPayWap").doPay(alipay);
        String payUrl = result.get("payUrl");
        logger.info("=====支付宝  H5支付    返回 ====={}",payUrl);
        resultMap.put("payUrl", payUrl);
        resultMap.put("payOrderNo", pay.getOrderNo());
        return resultMap;
    }

    @Override
    public Map<String, Object> doAlipayPcPay(ThirdPayReq pay) throws PayException {
        Map<String, Object> resultMap = InstanceUtil.newHashMap();

        Pay alipay = new Pay();
        alipay.setOrderDesc(pay.getOrderDesc());
        alipay.setOrderMoney(pay.getOrderMoney().toString());
        alipay.setOrderName(pay.getOrderName());
        alipay.setOrderNo(pay.getOrderNo());

        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",pay.getChannelId());
        param.put("mchId",pay.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);

        alipay.setPayConfig(payChannel.getParam());

        Map<String, String> result = PayServiceFactory.getPayInstance("aliPayPc").doPay(alipay);
        String payForm = result.get("payForm");
        logger.info("=====支付宝  PC支付    返回 ====={}",payForm);
        resultMap.put("payForm", payForm);
        resultMap.put("payOrderNo", pay.getOrderNo());
        return resultMap;
    }

    @Override
    public Map<String, Object> doAlipayQrPay(ThirdPayReq pay) throws PayException {
        Map<String, Object> resultMap = InstanceUtil.newHashMap();

        Pay alipay = new Pay();
        alipay.setOrderDesc(pay.getOrderDesc());
        alipay.setOrderMoney(pay.getOrderMoney().toString());
        alipay.setOrderName(pay.getOrderName());
        alipay.setOrderNo(pay.getOrderNo());

        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",pay.getChannelId());
        param.put("mchId",pay.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);

        alipay.setPayConfig(payChannel.getParam());

        Map<String, String> result = PayServiceFactory.getPayInstance("aliPayQr").doPay(alipay);
        String qrUrl = result.get("qrUrl");
        logger.info("=====支付宝  扫码支付    返回 ====={}",qrUrl);
        resultMap.put("qrUrl", qrUrl);
        resultMap.put("payOrderNo", pay.getOrderNo());
        return resultMap;
    }

    @Override
    public Map<String, Object> doAlipayMobilePay(ThirdPayReq pay) throws PayException {
        Map<String, Object> resultMap = InstanceUtil.newHashMap();

        Pay alipay = new Pay();
        alipay.setOrderDesc(pay.getOrderDesc());
        alipay.setOrderMoney(pay.getOrderMoney().toString());
        alipay.setOrderName(pay.getOrderName());
        alipay.setOrderNo(pay.getOrderNo());

        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",pay.getChannelId());
        param.put("mchId",pay.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);

        alipay.setPayConfig(payChannel.getParam());

        Map<String, String> result = PayServiceFactory.getPayInstance("aliPayMobile").doPay(alipay);
        String payParam = result.get("payParam");
        logger.info("=====支付宝  扫码支付    返回 ====={}",payParam);
        resultMap.put("payParam", payParam);
        resultMap.put("payOrderNo", pay.getOrderNo());
        return resultMap;
    }
}
