package com.tpay.payment.wechat;

import com.alibaba.fastjson.JSON;
import com.tpay.common.utils.BigDecimalUtils;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.SpringContextUtil;
import com.tpay.payment.base.service.Base3rdPayService;
import com.tpay.payment.base.to.Pay;
import com.tpay.payment.base.to.PayBase;
import com.tpay.payment.config.WechatConfig;
import com.tpay.payment.utils.Configure;
import com.tpay.payment.utils.HttpsRequest;
import com.tpay.payment.utils.RandomStringUtils;
import com.tpay.payment.utils.WechatSignature;
import com.tpay.payment.utils.XMLParser;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-11 11:24
 **/
public class WechatH5PayService extends Base3rdPayService {

    private WechatConfig wechatConfig;

    @Override
    protected Map<String, String> buildParameters(PayBase base) {
        Pay pay = (Pay)base;
        Integer totalFee = BigDecimalUtils.multiply(pay.getOrderMoney(), "100").intValue();// 订单总金额，单位为“分”，只能整数
        Map<String, String> parameters = InstanceUtil.newHashMap();
        String payConfig = pay.getPayConfig();
        wechatConfig = (WechatConfig) SpringContextUtil.getBean("wechatConfig");
        wechatConfig.init(payConfig);

        parameters.put("appid",wechatConfig.getAppId());
        parameters.put("mch_id",wechatConfig.getMchId());
        parameters.put("nonce_str", RandomStringUtils.getRandomStringByLength(32));
        parameters.put("body", pay.getOrderName());
        parameters.put("notify_url",wechatConfig.getNotifyUrl());
        parameters.put("out_trade_no", pay.getOrderNo());
        parameters.put("spbill_create_ip", pay.getSpBillCreateIP());
        parameters.put("total_fee",totalFee.toString());
        parameters.put("trade_type", "MWEB");
        parameters.put("scene_info", JSON.parseObject((pay.getExtra())).getString("sceneInfo"));
        parameters.put("sign", WechatSignature.getSign(parameters,wechatConfig.getMchKey()));
        return parameters;
    }

    @Override
    protected Map<String, String> execute(Map<String, String> parameters) throws Exception {
        String requestXML = XMLParser.getXmlFromMap(parameters);
        logger.info("调用微信统一下单接口向微信发送数据================{}",requestXML);
        wechatConfig = (WechatConfig) SpringContextUtil.getBean("wechatConfig");
        String result = HttpsRequest.sendPost(Configure.PAY_API, requestXML,false,wechatConfig.getCertRootPath(),wechatConfig.getCertPassword());
        //XML数据转为map
        Map<String,String> mapResult = XMLParser.parseXml(result);
        logger.info("调用微信统一下单接口接收微信返回数据================{}",mapResult);
        return mapResult;
    }
}
