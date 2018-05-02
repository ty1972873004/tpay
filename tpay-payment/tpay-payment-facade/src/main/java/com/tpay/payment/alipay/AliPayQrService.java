package com.tpay.payment.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.SpringContextUtil;
import com.tpay.payment.base.service.Base3rdPayService;
import com.tpay.payment.base.to.Pay;
import com.tpay.payment.base.to.PayBase;
import com.tpay.payment.config.AlipayConfig;
import com.tpay.payment.factory.AlipayApiClientFactory;
import com.tpay.payment.utils.BeanToMapUtils;

import net.sf.json.JSONObject;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-10 17:44
 **/
public class AliPayQrService extends Base3rdPayService {


    private AlipayConfig alipayConfig;


    @Override
    protected Map<String, String> buildParameters(PayBase base) {
        Pay pay = (Pay)base;
        Map<String, String> parameters = InstanceUtil.newHashMap();
        try {
            parameters = BeanToMapUtils.convertBean(pay);
        }catch(Exception e){
            logger.error("{}支付参数转换错误！",pay.toString());
        }
        return parameters;
    }

    @Override
    protected Map<String, String> execute(Map<String, String> parameters) throws Exception {
        Map<String, String> apilyParams = InstanceUtil.newHashMap();
        apilyParams.put("out_trade_no", parameters.get("orderNo"));
        apilyParams.put("total_amount", parameters.get("orderMoney"));
        apilyParams.put("subject", parameters.get("orderName"));
        JSONObject json = JSONObject.fromObject(apilyParams);
        return doAliPayQr(json.toString(),parameters.get("payConfig"));
    }

    private Map<String,String> doAliPayQr(String jsonParam,String config) {
        Map<String,String> resultMap = InstanceUtil.newHashMap();
        alipayConfig = (AlipayConfig) SpringContextUtil.getBean("alipayConfig");
        alipayConfig.init(config);
        AlipayClient alipayClient = AlipayApiClientFactory.getAlipayClient(alipayConfig);

        // 使用SDK，构建群发请求模型
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        alipayRequest.setBizContent(jsonParam);
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());

        String qrUrl = "";
        try {
            //调用SDK获取二维码地址
            qrUrl = alipayClient.execute(alipayRequest).getBody();
            resultMap.put("qrUrl", qrUrl);
        } catch (AlipayApiException e) {
            logger.error("支付宝支付异常"+e);
        }
        return resultMap;

    }

}
