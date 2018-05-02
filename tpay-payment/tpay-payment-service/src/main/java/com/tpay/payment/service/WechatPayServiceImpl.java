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
 * @create 2018-04-11 16:01
 **/
@Service("wechatPayService")
public class WechatPayServiceImpl implements WechatPayService {

    private static Logger logger = LoggerFactory.getLogger(WechatPayServiceImpl.class);

    @Autowired
    private PayChannelMapper payChannelMapper;

    @Override
    public Map<String, String> doWechatJsAPI(ThirdPayReq pay) throws PayException {

        Pay wechat = new Pay();
        wechat.setOrderDesc(pay.getOrderDesc());
        wechat.setOrderMoney(pay.getOrderMoney().toString());
        wechat.setOrderName(pay.getOrderName());
        wechat.setOrderNo(pay.getOrderNo());
        wechat.setSpBillCreateIP(pay.getClientIp());
        wechat.setOpenId(pay.getOpenId());

        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",pay.getChannelId());
        param.put("mchId",pay.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);
        wechat.setPayConfig(payChannel.getParam());

        Map<String, String> result = PayServiceFactory.getPayInstance("wechatJsPay").doPay(wechat);
        logger.info("=====微信  JsAPI支付    返回 ====={}",result);
        return result;
    }

    @Override
    public Map<String, String> doWechatAppAPI(ThirdPayReq pay) throws PayException {
        Pay wechat = new Pay();
        wechat.setOrderDesc(pay.getOrderDesc());
        wechat.setOrderMoney(pay.getOrderMoney().toString());
        wechat.setOrderName(pay.getOrderName());
        wechat.setOrderNo(pay.getOrderNo());
        wechat.setSpBillCreateIP(pay.getClientIp());

        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",pay.getChannelId());
        param.put("mchId",pay.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);
        wechat.setPayConfig(payChannel.getParam());

        Map<String, String> result = PayServiceFactory.getPayInstance("wechatAppPay").doPay(wechat);
        logger.info("=====微信  APP支付    返回 ====={}",result);
        return result;
    }

    @Override
    public Map<String, String> doWechatNativeAPI(ThirdPayReq pay) throws PayException {
        Pay wechat = new Pay();
        wechat.setOrderDesc(pay.getOrderDesc());
        wechat.setOrderMoney(pay.getOrderMoney().toString());
        wechat.setOrderName(pay.getOrderName());
        wechat.setOrderNo(pay.getOrderNo());
        wechat.setSpBillCreateIP(pay.getClientIp());
        wechat.setExtra(pay.getExtra());

        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",pay.getChannelId());
        param.put("mchId",pay.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);
        wechat.setPayConfig(payChannel.getParam());

        Map<String, String> result = PayServiceFactory.getPayInstance("wechatNativePay").doPay(wechat);
        logger.info("=====微信  扫码支付    返回 ====={}",result);
        return result;
    }

    @Override
    public Map<String, String> doWechatH5API(ThirdPayReq pay) throws PayException {
        Pay wechat = new Pay();
        wechat.setOrderDesc(pay.getOrderDesc());
        wechat.setOrderMoney(pay.getOrderMoney().toString());
        wechat.setOrderName(pay.getOrderName());
        wechat.setOrderNo(pay.getOrderNo());
        wechat.setSpBillCreateIP(pay.getClientIp());
        wechat.setExtra(pay.getExtra());

        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",pay.getChannelId());
        param.put("mchId",pay.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);
        wechat.setPayConfig(payChannel.getParam());

        Map<String, String> result = PayServiceFactory.getPayInstance("wechatH5Pay").doPay(wechat);
        logger.info("=====微信  H5支付    返回 ====={}",result);
        return result;
    }
}
