package com.tpay.payment.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.tpay.common.utils.BigDecimalUtils;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.MoneyUtils;
import com.tpay.common.utils.SpringContextUtil;
import com.tpay.order.mapper.PayChannelMapper;
import com.tpay.order.mapper.PayOrderMapper;
import com.tpay.order.model.PayChannel;
import com.tpay.order.model.PayOrder;
import com.tpay.payment.config.AlipayConfig;
import com.tpay.payment.config.WechatConfig;
import com.tpay.payment.constants.PayConstants;
import com.tpay.payment.enums.PayStatusEnum;
import com.tpay.payment.utils.AlipayCore;
import com.tpay.payment.utils.MD5;
import com.tpay.payment.utils.WechatUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-10 10:13
 **/
@Service("notifyPayService")
public class NotifyPayServiceImpl implements NotifyPayService {

    private static Logger logger = LoggerFactory.getLogger(NotifyPayServiceImpl.class);

    @Autowired
    private PayChannelMapper payChannelMapper;

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private WechatConfig wechatConfig;

    @Override
    public Map<String, Object> doAliPayNotify(Map<String, String> map) {
        logger.info("开始处理支付宝回调");
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
        Map<String,Object> contextMap = InstanceUtil.newHashMap();
        contextMap.put("reqMap",map);
        if(!alipayVerify(contextMap)){
            resultMap.put("code",0);
            resultMap.put("msg","验签失败");
        }
        //获取交易状态
        String trade_status = map.get("trade_status");
        if(trade_status.equals(PayConstants.AliPayConstant.TRADE_STATUS_SUCCESS)||trade_status.equals(PayConstants.AliPayConstant.TRADE_STATUS_FINISHED)){
            PayOrder payOrder = (PayOrder)contextMap.get("payOrder");
            String payStatus = payOrder.getPayStatus();
            if(payStatus != PayStatusEnum.PAY_SUCCESS.getValue() && payStatus != PayStatusEnum.PAY_COMPLETE.getValue()){
                payOrder.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                payOrder.setPaySuccessTime(new Timestamp(System.currentTimeMillis()));
                payOrder.setChannelOrderNo(map.get("trade_no"));
                int rows = payOrderMapper.update(payOrder);
                if(rows != 1){
                    logger.error("订单更新失败{}",payOrder);
                    resultMap.put("code",0);
                    resultMap.put("msg","订单更新失败");
                }
                resultMap.put("code",1);
                resultMap.put("msg","订单更新成功");
                resultMap.put("data",payOrder);
            }
        }else{
            logger.info("支付宝支付状态{}",trade_status);
        }

        return resultMap;
    }

    @Override
    public Map<String, Object> doWechatPayNotify(Map<String, String> params) {
        logger.info("开始处理微信回调");
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
        Map<String,Object> contextMap = InstanceUtil.newHashMap();
        contextMap.put("reqMap",params);
        if(!wechatPayVerify(contextMap)){
            resultMap.put("code",0);
            resultMap.put("msg","验签失败");
        }
        PayOrder payOrder = (PayOrder)contextMap.get("payOrder");
        String payStatus = payOrder.getPayStatus();
        if(payStatus != PayStatusEnum.PAY_SUCCESS.getValue() && payStatus != PayStatusEnum.PAY_COMPLETE.getValue()){
            payOrder.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
            payOrder.setPaySuccessTime(new Timestamp(System.currentTimeMillis()));
            payOrder.setChannelOrderNo(params.get("transaction_id"));
            int rows = payOrderMapper.update(payOrder);
            if(rows != 1){
                logger.error("订单更新失败{}",payOrder);
                resultMap.put("code",0);
                resultMap.put("msg","订单更新失败");
            }
            resultMap.put("code",1);
            resultMap.put("msg","订单更新成功");
            resultMap.put("data",payOrder);
        }
        logger.info("=====微信支付    异步回调 成功  ====={}",payOrder.getPayOrderNo());
        return resultMap;
    }

    public boolean alipayVerify(Map<String, Object> contextMap){
        Map<String, String> reqMap = (Map<String, String>)contextMap.get("reqMap");
        //交易状态
        String out_trade_no = reqMap.get("out_trade_no");
        //交易状态
        String total_amount = reqMap.get("total_amount");
        if(StringUtils.isEmpty(out_trade_no)){
            return false;
        }
        if(StringUtils.isEmpty(total_amount)){
            return false;
        }

        //获取订单信息
        PayOrder payOrder = payOrderMapper.selectByOrderNo(out_trade_no);
        if(payOrder == null) {
            logger.error("payOrder not found {}",out_trade_no);
            return false;
        }

        //通过订单号查询支付渠道信息
        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",payOrder.getPayChannel());
        param.put("mchId",payOrder.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);
        if(payChannel == null) {
            logger.error("payChannel not found {} {}",payOrder.getPayChannel(),payOrder.getMchId());
            return false;
        }

        boolean result = false;
        Map<String, String> sParaNew = AlipayCore.paraFilter1(reqMap);
        try {
            result = AlipaySignature.rsaCheckV1(sParaNew,alipayConfig.init(payChannel.getParam()).getAlipay_public__key(),AlipayConfig.CHARSET,AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            logger.error("支付宝验签异常",e);
        }
        if(!result){
            logger.error("eheck sign fail");
            return false;
        }
        BigDecimal alipayAmount = new BigDecimal(total_amount);
        BigDecimal orderAmount = payOrder.getAmount();
        if(BigDecimalUtils.compareTo(orderAmount,alipayAmount)!=0){
            logger.error("实际支付金额和订单金额不等");
            return false;
        }
        contextMap.put("payOrder",payOrder);
        return true;
    }

    /**
     *
     * @param contextMap
     * @return
     */
    public boolean wechatPayVerify(Map<String, Object> contextMap){
        Map<String, String> reqMap = (Map<String, String>)contextMap.get("reqMap");
        if(!PayConstants.WechatPayConstant.RETURN_SUCCESS.equals(reqMap.get("return_code"))){
            logger.error("微信回调失败returnCode={},returnMsg={}",reqMap.get("return_code"),reqMap.get("return_msg"));
            return false;
        }
        String total_fee = reqMap.get("total_fee");
        String out_trade_no = reqMap.get("out_trade_no");
        if(StringUtils.isEmpty(out_trade_no)){
            return false;
        }
        if(StringUtils.isEmpty(total_fee)){
            return false;
        }

        //获取订单信息
        PayOrder payOrder = payOrderMapper.selectByOrderNo(out_trade_no);
        if(payOrder == null) {
            logger.error("payOrder not found {}",out_trade_no);
            return false;
        }

        //通过订单号查询支付渠道信息
        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("channelId",payOrder.getPayChannel());
        param.put("mchId",payOrder.getMchId());
        PayChannel payChannel = payChannelMapper.selectOneByParms(param);
        if(payChannel == null) {
            logger.error("payChannel not found {} {}",payOrder.getPayChannel(),payOrder.getMchId());
            return false;
        }
        //获取微信回调返回的sign
        String sign = reqMap.get("sign");
        //过滤掉sign值
        Map<String, String> filterPara = paraFilter(reqMap);
        wechatConfig = (WechatConfig) SpringContextUtil.getBean("wechatConfig");
        wechatConfig.init(payChannel.getParam());

        String mySign = getSign(filterPara,wechatConfig.getMchKey());
        if(!mySign.equals(sign)){
            return false;
        }

        BigDecimal wechatAmount = new BigDecimal(MoneyUtils.toDollar(total_fee));
        BigDecimal orderAmount = payOrder.getAmount();
        if(BigDecimalUtils.compareTo(wechatAmount,orderAmount)!=0){
            logger.error("实际支付金额和订单金额不等");
            return false;
        }
        contextMap.put("payOrder",payOrder);
        return true;
    }

    private String getSign(Map<String,String> map,String apiKey){
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
        result += "key=" + apiKey;
        result = MD5.MD5Encode(result).toUpperCase();
        WechatUtil.log("Sign Result:" + result);

        return result;
    }

    /**
     * 验签 过滤掉sign
     * @param sArray
     * @return
     */
    private  Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
}
