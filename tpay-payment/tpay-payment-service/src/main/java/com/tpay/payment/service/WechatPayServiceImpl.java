package com.tpay.payment.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.order.mapper.PayChannelMapper;
import com.tpay.order.model.PayChannel;
import com.tpay.payment.base.to.Pay;
import com.tpay.payment.dto.ThirdPayReq;
import com.tpay.payment.exceptions.PayException;
import com.tpay.payment.factory.PayServiceFactory;
import com.tpay.payment.utils.RandomStringUtils;
import com.tpay.payment.utils.WechatSignature;

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
    public Map<String, Object> doWechatJsAPI(ThirdPayReq pay) throws PayException {
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
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
        Map<String, String> payInfo = InstanceUtil.newHashMap();
        JSONObject payConfig = JSON.parseObject(payChannel.getParam());
        payInfo.put("appId",result.get("appid"));
        payInfo.put("nonceStr", RandomStringUtils.getRandomStringByLength(32));
        payInfo.put("timeStamp",String.valueOf(System.currentTimeMillis()/1000));
        payInfo.put("package","prepay_id=" + result.get("prepay_id"));
        payInfo.put("signType","MD5");
        payInfo.put("paySign", WechatSignature.getSign(payInfo,payConfig.getString("mchKey")));
        resultMap.put("payOrderNo",pay.getOrderNo());
        resultMap.put("prePayId",result.get("prepay_id"));
        resultMap.put("wxPayParams",payInfo);
        logger.info("=====微信  JsAPI支付    返回 ====={}",resultMap);
        return resultMap;
    }

    @Override
    public Map<String, Object> doWechatAppAPI(ThirdPayReq pay) throws PayException {
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
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
        Map<String, String> signMap = InstanceUtil.newHashMap();
        JSONObject payConfig = JSON.parseObject(payChannel.getParam());
        String prepayId = result.get("prepay_id");
        String appId = result.get("appid");
        String partnerId = payConfig.getString("mchId");
        String timestamp = String.valueOf(System.currentTimeMillis()/1000);
        String noncestr = RandomStringUtils.getRandomStringByLength(32);
        signMap.put("appId",appId);
        signMap.put("prepayid",prepayId);
        signMap.put("partnerid",partnerId);
        signMap.put("package","Sign=WXPay");
        signMap.put("timestamp",timestamp);
        signMap.put("noncestr",noncestr);
        Map<String, String> payInfo = InstanceUtil.newHashMap();
        payInfo.put("sign",WechatSignature.getSign(payInfo,payConfig.getString("mchKey")));
        payInfo.put("prepayId",prepayId);
        payInfo.put("partnerId",partnerId);
        payInfo.put("appId",appId);
        payInfo.put("packageValue","Sign=WXPay");
        payInfo.put("timeStamp",timestamp);
        payInfo.put("nonceStr",noncestr);

        resultMap.put("payOrderNo",pay.getOrderNo());
        resultMap.put("prePayId",prepayId);
        resultMap.put("wxPayParams",payInfo);
        logger.info("=====微信  APP支付    返回 ====={}",resultMap);
        return resultMap;
    }

    @Override
    public Map<String, Object> doWechatNativeAPI(ThirdPayReq pay) throws PayException {
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
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
        resultMap.put("payOrderNo",pay.getOrderNo());
        resultMap.put("prePayId",result.get("prepay_id"));
        resultMap.put("wxPayParams",result.get("code_url"));
        logger.info("=====微信  扫码支付    返回 ====={}",resultMap);
        return resultMap;
    }

    @Override
    public Map<String, Object> doWechatH5API(ThirdPayReq pay) throws PayException {
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
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
        resultMap.put("payOrderNo",pay.getOrderNo());
        resultMap.put("prePayId",result.get("prepay_id"));
        resultMap.put("wxPayParams",result.get("mweb_url"));
        logger.info("=====微信  H5支付    返回 ====={}",result);
        return resultMap;
    }
}
