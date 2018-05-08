package com.tpay.payment.controller;

import com.tpay.base.controller.BaseController;
import com.tpay.common.utils.PropertiesFileUtil;
import com.tpay.notify.constants.MqConstants;
import com.tpay.notify.enums.NotifyTypeEnum;
import com.tpay.notify.service.NotifyRecordService;
import com.tpay.notify.utils.BuildRequestUtils;
import com.tpay.order.model.PayOrder;
import com.tpay.payment.service.NotifyPayService;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;

/**
 * @author tuyong
 * @version 1.0
 * @desc 异步回调通知
 * @create 2018-04-10 9:51
 **/
@Controller
@Api(value = "支付回调", description = "支付回调")
public class NotifyController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private NotifyPayService notifyPayService;

    @Autowired
    private NotifyRecordService notifyRecordService;


    /**
     * 支付宝支付回调
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/pay/asyRequestForAliPay", method = RequestMethod.POST)
    @ResponseBody
    public void asyRequestForAliPay(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

        Map<String, String> params =  prehandle(request.getParameterMap());
        Map<String,Object> map = notifyPayService.doAliPayNotify(params);
        if((int)map.get("code") == 1){
            PayOrder payOrder = (PayOrder)map.get("data");
            String notifyUrl = BuildRequestUtils.buildThirdCallBackUrl(payOrder.getNotifyUrl(),"SUCCESS","OK",payOrder.getMchId().toString(),payOrder.getPayOrderNo(),payOrder.getPayStatus(),payOrder.getAmount().toString(),payOrder.getPaySuccessTime().toString(),"","");
            notifyRecordService.notifySend(notifyUrl,payOrder.getPayOrderNo(),payOrder.getMchId().toString(), NotifyTypeEnum.THIRD_CALL_BACK_NOTIFY.getValue(), PropertiesFileUtil.getInstance("system").get("third.callback"),"");
            response.getWriter().write("success");
        }
    }

    /**
     * 微信支付回调
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/weixinAction/asyRequestForWechat", method = RequestMethod.POST)
    @ResponseBody
    public void asyRequestForWechat(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String succResult = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        String failResult = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[验签失败]]></return_msg></xml>";
        Map<String, String> params = parseXml(request);
        Map<String,Object> map = notifyPayService.doWechatPayNotify(params);
        if((int)map.get("code") == 1){
            PayOrder payOrder = (PayOrder)map.get("data");
            String notifyUrl = BuildRequestUtils.buildThirdCallBackUrl(payOrder.getNotifyUrl(),"SUCCESS","OK",payOrder.getMchId().toString(),payOrder.getPayOrderNo(),payOrder.getPayStatus(),payOrder.getAmount().toString(),payOrder.getPaySuccessTime().toString(),"","");
            notifyRecordService.notifySend(notifyUrl,payOrder.getPayOrderNo(),payOrder.getMchId().toString(), NotifyTypeEnum.THIRD_CALL_BACK_NOTIFY.getValue(), PropertiesFileUtil.getInstance("system").get("third.callback"),"");
            response.getOutputStream().print(succResult);
        } else{
            response.getOutputStream().print(failResult);
        }
        response.getOutputStream().close();
    }

}
