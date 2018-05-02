package com.tpay.payment.factory;

import com.tpay.payment.alipay.AliPayMobileService;
import com.tpay.payment.alipay.AliPayPcService;
import com.tpay.payment.alipay.AliPayQrService;
import com.tpay.payment.alipay.AliPayWapService;
import com.tpay.payment.base.service.Base3rdPayService;
import com.tpay.payment.wechat.WechatAppPayService;
import com.tpay.payment.wechat.WechatH5PayService;
import com.tpay.payment.wechat.WechatJsPayService;
import com.tpay.payment.wechat.WechatNativePayService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc  服务工厂
 * @create 2018-04-09 14:09
 **/
public class PayServiceFactory {

    /**
     * 支付服务基类
     */
    private static Map<String, Base3rdPayService> registerPay;

    static{
        /**
         *支付宝支付服务
         */
        registerPay = new HashMap<String, Base3rdPayService>();
        registerPay.put("aliPayWap", new AliPayWapService());
        registerPay.put("aliPayPc", new AliPayPcService());
        registerPay.put("aliPayMobile",new AliPayMobileService());
        registerPay.put("aliPayQr",new AliPayQrService());

        /**
         *微信支付服务
         */
        registerPay.put("wechatJsPay",new WechatJsPayService());
        registerPay.put("wechatAppPay",new WechatAppPayService());
        registerPay.put("wechatNativePay",new WechatNativePayService());
        registerPay.put("wechatH5Pay",new WechatH5PayService());

    }

    /**
     * 获取支付实例
     * @param channel
     * @return
     */
    public static Base3rdPayService getPayInstance(String channel) {
        return registerPay.get(channel);
    }



}
