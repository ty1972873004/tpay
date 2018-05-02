package com.tpay.payment.factory;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.tpay.payment.config.AlipayConfig;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-09 16:13
 **/
public class AlipayApiClientFactory {

    /** API调用客户端 */
    private static AlipayClient alipayClient;

    /**
     * 获得API调用客户端
     * @return
     */
    public static AlipayClient getAlipayClient(AlipayConfig alipayConfig){

        if(null == alipayClient){
            alipayClient = new DefaultAlipayClient(alipayConfig.getGateway_url(), alipayConfig.getApp_id(),
                    alipayConfig.getRsa_private_key(), "json", AlipayConfig.CHARSET,alipayConfig.getAlipay_public__key(),AlipayConfig.SIGNTYPE);
        }
        return alipayClient;
    }


}
