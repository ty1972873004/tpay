package com.tpay.payment.controller;

import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.payment.dto.ThirdPayReq;
import com.tpay.payment.service.AliPayService;
import com.tpay.payment.service.WechatPayService;


import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-10 15:55
 **/
@RestController
@RequestMapping(value = "/pay", method = RequestMethod.GET)
public class TestController {
    
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private WechatPayService wechatPayService;


    @ApiOperation(value = "支付测试")
    @RequestMapping("/alipayWap")
    public void alipayWap(HttpServletResponse response,String orderNo)   {
        ThirdPayReq req = new ThirdPayReq();
        req.setChannelId("ALIPAY_WAP");
        req.setMchId(1000001L);
        req.setOrderDesc("测试订单详情");
        req.setOrderNo(orderNo);
        req.setOrderMoney(new BigDecimal(1));
        req.setOrderName("测试订单");

        Map<String, Object> map = aliPayService.doAlipayWapPay(req);

		 String payUrl =(String) map.get("payUrl");
		 try{
			 response.setContentType("text/html;charset=UTF-8");
	         response.setCharacterEncoding("UTF-8");
	         response.getWriter().write(payUrl);
	         response.setStatus(HttpServletResponse.SC_OK);
		 }catch (IOException e) {
	            e.printStackTrace();
	        }
    }

    @ApiOperation(value = "支付测试")
    @RequestMapping("/alipayPc")
    public void alipayPc(HttpServletResponse response,String orderNo)   {
        ThirdPayReq req = new ThirdPayReq();
        req.setChannelId("ALIPAY_PC");
        req.setMchId(1000001L);
        req.setOrderDesc("测试PC订单详情");
        req.setOrderNo(orderNo);
        req.setOrderMoney(new BigDecimal(1));
        req.setOrderName("测试PC订单");

        Map<String, Object> map = aliPayService.doAlipayPcPay(req);

        String payForm =(String) map.get("payForm");
        try{
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(payForm);
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "支付测试")
    @RequestMapping("/alipayQr")
    @ResponseBody
    public String alipayQr(HttpServletResponse response,String orderNo)   {
        ThirdPayReq req = new ThirdPayReq();
        req.setChannelId("ALIPAY_QR");
        req.setMchId(1000001L);
        req.setOrderDesc("测试QR订单详情");
        req.setOrderNo(orderNo);
        req.setOrderMoney(new BigDecimal(1));
        req.setOrderName("测试QR订单");

        Map<String, Object> map = aliPayService.doAlipayQrPay(req);

        String qrUrl =(String) map.get("qrUrl");
        return qrUrl;
    }

    @ApiOperation(value = "支付测试")
    @RequestMapping("/alipayMobile")
    @ResponseBody
    public String alipayMobile(HttpServletResponse response,String orderNo)   {
        ThirdPayReq req = new ThirdPayReq();
        req.setChannelId("ALIPAY_MOBILE");
        req.setMchId(1000001L);
        req.setOrderDesc("测试APP订单详情");
        req.setOrderNo(orderNo);
        req.setOrderMoney(new BigDecimal(1));
        req.setOrderName("测试APP订单");

        Map<String, Object> map = aliPayService.doAlipayMobilePay(req);

        String payParam =(String) map.get("payParam");
        return payParam;
    }

    @ApiOperation(value = "支付测试")
    @RequestMapping("/wechatJsAPI")
    @ResponseBody
    public Object wechatJsAPI(HttpServletResponse response,String orderNo)   {
        ThirdPayReq req = new ThirdPayReq();
        req.setChannelId("WX_JSAPI");
        req.setMchId(1000001L);
        req.setOrderDesc("测试JSAPI订单详情");
        req.setOrderNo(orderNo);
        req.setOrderMoney(new BigDecimal(0.01));
        req.setOrderName("测试JSAPI订单");
        req.setClientIp("127.0.0.1");
        req.setOpenId("o9JDMwoWgzcrBirYVf_MEG9X0gtM");
        Map<String, String> map = wechatPayService.doWechatJsAPI(req);
        return map;
    }

    @ApiOperation(value = "支付测试")
    @RequestMapping("/wechatAppAPI")
    @ResponseBody
    public Object wechatAppAPI(HttpServletResponse response,String orderNo)   {
        ThirdPayReq req = new ThirdPayReq();
        req.setChannelId("WX_APP");
        req.setMchId(1000001L);
        req.setOrderDesc("测试APP订单详情");
        req.setOrderNo(orderNo);
        req.setOrderMoney(new BigDecimal(0.01));
        req.setOrderName("测试APP订单");
        req.setClientIp("127.0.0.1");
        Map<String, String> map = wechatPayService.doWechatAppAPI(req);
        return map;
    }

    @ApiOperation(value = "支付测试")
    @RequestMapping("/wechatNativeAPI")
    @ResponseBody
    public Object wechatNativeAPI(HttpServletResponse response,String orderNo)   {
        ThirdPayReq req = new ThirdPayReq();
        req.setChannelId("WX_NATIVE");
        req.setMchId(1000001L);
        req.setOrderDesc("测试扫码订单详情");
        req.setOrderNo(orderNo);
        req.setOrderMoney(new BigDecimal(0.01));
        req.setOrderName("测试扫码订单");
        req.setClientIp("127.0.0.1");
        Map<String,Object> extra = InstanceUtil.newHashMap();
        extra.put("productId",orderNo);
        req.setExtra(JSONObject.fromObject(extra).toString());
        Map<String, String> map = wechatPayService.doWechatNativeAPI(req);
        return map;
    }

    @ApiOperation(value = "支付测试")
    @RequestMapping("/wechatH5API")
    @ResponseBody
    public Object wechatH5API(HttpServletRequest request,HttpServletResponse response, String orderNo)   {
        ThirdPayReq req = new ThirdPayReq();
        req.setChannelId("WX_MWEB");
        req.setMchId(1000001L);
        req.setOrderDesc("测试H5订单详情");
        req.setOrderNo(orderNo);
        req.setOrderMoney(new BigDecimal(0.01));
        req.setOrderName("测试H5订单");
        req.setClientIp("27.154.164.192");
        Map<String,Object> extra = InstanceUtil.newHashMap();
        extra.put("sceneInfo","{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://pay.qq.com\",\"wap_name\": \"腾讯充值\"}}");
        req.setExtra(JSONObject.fromObject(extra).toString());
        logger.info("请求参数{}",req);
        Map<String, String> map = wechatPayService.doWechatH5API(req);
        return map;
    }


}
