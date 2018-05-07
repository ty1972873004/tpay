package com.tpay.open.controller;


import com.tpay.common.constants.WebConstants;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.TpayUtils;
import com.tpay.open.service.OrderService;
import com.tpay.order.model.PayOrder;
import com.tpay.payment.constants.PayConstants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-05-07 11:10
 **/
@RestController
@Api(value = "订单", description = "订单")
@RequestMapping(value = "/open/v1", method = RequestMethod.POST)

public class PayOrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="统一下单接口")
    @PostMapping("/unifiedOrder")
    public Object unifiedorder(ModelMap modelMap,
                               @ApiParam(required = true, value = "商户ID") @RequestParam(value = "mchId", required =  true) String mchId,
                               @ApiParam(required = true, value = "商户订单号") @RequestParam(value = "mchOrderNo", required =  true) String mchOrderNo,
                               @ApiParam(required = true, value = "渠道ID") @RequestParam(value = "channelId", required =  true) String channelId,
                               @ApiParam(required = true, value = "支付金额（分）") @RequestParam(value = "amount", required =  true) String amount,
                               @ApiParam(required = true, value = "币种") @RequestParam(value = "currency", required =  true) String currency,
                               @ApiParam(required = true, value = "客户端IP") @RequestParam(value = "clientIp", required =  true) String clientIp,
                               @ApiParam(required = true, value = "设备") @RequestParam(value = "device", required =  true) String device,
                               @ApiParam(required = true, value = "通知地址") @RequestParam(value = "notifyUrl", required =  true) String notifyUrl,
                               @ApiParam(required = true, value = "商品标题") @RequestParam(value = "subject", required =  true) String subject,
                               @ApiParam(required = true, value = "商品描述") @RequestParam(value = "body", required =  true) String body,
                               @ApiParam(required = true, value = "扩展参数") @RequestParam(value = "extra", required =  true) String extra,
                               @ApiParam(required = true, value = "备注") @RequestParam(value = "remark", required =  true) String remark,
                               @ApiParam(required = true, value = "签名") @RequestParam(value = "sign", required =  true) String sign)  throws Exception{

        Map<String,Object> signMap = InstanceUtil.newHashMap();
        signMap.put("mchId",mchId);
        signMap.put("mchOrderNo",mchOrderNo);
        signMap.put("channelId",channelId);
        signMap.put("amount",amount);
        signMap.put("currency",currency);
        signMap.put("clientIp",clientIp);
        signMap.put("device",device);
        signMap.put("notifyUrl",notifyUrl);
        signMap.put("subject",subject);
        signMap.put("body",body);

        boolean verifyFlag = TpayUtils.verifySign(signMap,sign);
        if(!verifyFlag){
            return  TpayUtils.setResultMap(String.valueOf(WebConstants.CHECK_FAIL),"订单验签失败",null);
        }

        PayOrder payOrder = orderService.createOrder(mchId,mchOrderNo,channelId,amount,currency,clientIp,device,notifyUrl,subject,body,extra,remark);
        if(StringUtils.isEmpty(payOrder.getTid())){
            return  TpayUtils.setFailResultMap("订单创建失败",null);
        }

        switch (channelId){
            case PayConstants.PAY_CHANNEL_ALIPAY_MOBILE :
                return orderService.doAliPay(payOrder);
            case PayConstants.PAY_CHANNEL_ALIPAY_PC :
                return orderService.doAliPay(payOrder);
            case PayConstants.PAY_CHANNEL_ALIPAY_QR :
                return orderService.doAliPay(payOrder);
            case PayConstants.PAY_CHANNEL_ALIPAY_WAP :
                return orderService.doAliPay(payOrder);
            case PayConstants.PAY_CHANNEL_WX_APP :
                return  orderService.doWxPay(payOrder);
            case PayConstants.PAY_CHANNEL_WX_JSAPI :
                return  orderService.doWxPay(payOrder);
            case PayConstants.PAY_CHANNEL_WX_MWEB :
                return  orderService.doWxPay(payOrder);
            case PayConstants.PAY_CHANNEL_WX_NATIVE :
                return  orderService.doWxPay(payOrder);
            default:
                return TpayUtils.setFailResultMap("支付渠道错误",null);

        }

    }


}
