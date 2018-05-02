package com.tpay.payment.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.SpringContextUtil;
import com.tpay.payment.base.service.Base3rdPayService;
import com.tpay.payment.base.to.Pay;
import com.tpay.payment.base.to.PayBase;
import com.tpay.payment.config.AlipayConfig;
import com.tpay.payment.factory.AlipayApiClientFactory;
import com.tpay.payment.utils.BeanToMapUtils;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc 支付宝手机网站支付
 * @create 2018-04-09 15:19
 **/
public class AliPayWapService extends Base3rdPayService {

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
        apilyParams.put("product_code", "QUICK_WAP_PAY");
        JSONObject json = JSONObject.fromObject(apilyParams);
        return doAliPayWap(json.toString(),parameters.get("payConfig"));
    }

    private Map<String,String> doAliPayWap(String jsonParam,String config) {
        Map<String,String> resultMap = InstanceUtil.newHashMap();
        alipayConfig = (AlipayConfig)SpringContextUtil.getBean("alipayConfig");
        alipayConfig.init(config);
        AlipayClient alipayClient = AlipayApiClientFactory.getAlipayClient(alipayConfig);

        // 使用SDK，构建群发请求模型
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        alipayRequest.setBizContent(jsonParam);
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
        AlipayTradeWapPayResponse response = new AlipayTradeWapPayResponse();
        try {
            // 使用SDK，调用交易下单接口
            response = alipayClient.pageExecute(alipayRequest);
            resultMap.put("payUrl", response.getBody());

        }catch (AlipayApiException e) {
            logger.error("支付宝支付异常"+e);
        }
        return resultMap;
    }

}
