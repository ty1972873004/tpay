package com.tpay.open.service;

import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.RsaUtils;
import com.tpay.common.utils.TpayUtils;
import com.tpay.order.model.PayChannel;
import com.tpay.order.model.PayOrder;
import com.tpay.order.service.PayChannelService;
import com.tpay.order.service.PayOrderService;
import com.tpay.payment.constants.PayConstants;
import com.tpay.payment.dto.ThirdPayReq;
import com.tpay.payment.enums.PayStatusEnum;
import com.tpay.payment.service.AliPayService;
import com.tpay.payment.service.WechatPayService;
import com.tpay.sys.constants.SequenceConstants;
import com.tpay.sys.service.SequenceService;
import com.tpay.user.model.MchInfo;
import com.tpay.user.service.MchInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-05-07 15:32
 **/
@Service
public class OrderService {

    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private PayChannelService payChannelService;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private WechatPayService wechatPayService;

    @Autowired
    private MchInfoService mchInfoService;

    public boolean verifySign(Map<String,String> signMap,String sign) throws  Exception{
        Long mchId = Long.valueOf((String)signMap.get("mchId"));

        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("mchId",mchId);
        MchInfo mchInfo = mchInfoService.queryOne(params);
        signMap.put("sign_param",TpayUtils.getSignMapKeys(signMap));
        signMap.put("content",RsaUtils.getSignContent(signMap));
        signMap.put("sign_type","RSA2");
        signMap.put("publicKey",mchInfo.getMchPublicKey());
        boolean verifyFlag = TpayUtils.verifySign(signMap,sign);
        return  verifyFlag;
    }

    public PayOrder createOrder(String mchId,String mchOrderNo,String channelId,String amount,String currency,String clientIp,String device,String notifyUrl,String subject,
                           String body,String extra,String remark){


        Map<String, Object> params = InstanceUtil.newHashMap();
        params.put("channelId",channelId);
        params.put("mchId",mchId);
        PayChannel payChannel = payChannelService.queryOne(params);

        String orderNo = sequenceService.getSequence(SequenceConstants.PAY_ORDER);
        PayOrder payOrder = new PayOrder();
        payOrder.setPayOrderNo(orderNo);
        payOrder.setMchOrderNo(mchOrderNo);
        payOrder.setClientIp(clientIp);
        payOrder.setPayChannel(channelId);
        payOrder.setChannelMchId(payChannel.getChannelMchId());
        payOrder.setAmount(new BigDecimal(amount));
        payOrder.setCurrency(currency);
        payOrder.setDeviceInfo(device);
        payOrder.setNotifyUrl(notifyUrl);
        payOrder.setSubject(subject);
        payOrder.setBody(body);
        payOrder.setExtra(extra);
        payOrder.setRemark(remark);
        payOrder.setPayStatus(PayStatusEnum.PAY_INIT.getValue());
        payOrder.setMchId(Long.valueOf(mchId));

        payOrder = payOrderService.insertOrUpdate(payOrder);
        return payOrder;
    }

    public Map<String,Object>  doAliPay(PayOrder payOrder){
        ThirdPayReq thirdPayReq = new ThirdPayReq();
        thirdPayReq.setMchId(payOrder.getMchId());
        thirdPayReq.setClientIp(payOrder.getClientIp());
        thirdPayReq.setOrderName(payOrder.getSubject());
        thirdPayReq.setOrderMoney(payOrder.getAmount());
        thirdPayReq.setOrderDesc(payOrder.getBody());
        thirdPayReq.setChannelId(payOrder.getPayChannel());
        thirdPayReq.setExtra(payOrder.getExtra());
        thirdPayReq.setOrderNo(payOrder.getPayOrderNo());
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
        switch (payOrder.getPayChannel()){
            case PayConstants.PAY_CHANNEL_ALIPAY_MOBILE :
                resultMap = aliPayService.doAlipayMobilePay(thirdPayReq);
                break;
            case PayConstants.PAY_CHANNEL_ALIPAY_PC :
                resultMap = aliPayService.doAlipayPcPay(thirdPayReq);
                break;
            case PayConstants.PAY_CHANNEL_ALIPAY_QR :
                resultMap = aliPayService.doAlipayQrPay(thirdPayReq);
                break;
            case PayConstants.PAY_CHANNEL_ALIPAY_WAP :
                resultMap = aliPayService.doAlipayWapPay(thirdPayReq);
                break;
            default:
                resultMap = null;
                break;
        }
        return TpayUtils.setSuccessResultMap(null,resultMap);
    }

    public Map<String,Object> doWxPay(PayOrder payOrder){
        ThirdPayReq thirdPayReq = new ThirdPayReq();
        thirdPayReq.setMchId(payOrder.getMchId());
        thirdPayReq.setClientIp(payOrder.getClientIp());
        thirdPayReq.setOrderName(payOrder.getSubject());
        thirdPayReq.setOrderMoney(payOrder.getAmount());
        thirdPayReq.setOrderDesc(payOrder.getBody());
        thirdPayReq.setChannelId(payOrder.getPayChannel());
        thirdPayReq.setOrderNo(payOrder.getPayOrderNo());

        Map<String,Object> resultMap = InstanceUtil.newHashMap();
        Map<String,Object> extra = InstanceUtil.newHashMap();
        String payParam = null;
        switch (payOrder.getPayChannel()){
            case PayConstants.PAY_CHANNEL_WX_APP :
                resultMap = wechatPayService.doWechatAppAPI(thirdPayReq);
                break;
            case PayConstants.PAY_CHANNEL_WX_JSAPI :
                extra.put("openId",payOrder.getExtra());
                thirdPayReq.setOpenId(JSONObject.fromObject(extra).toString());
                resultMap = wechatPayService.doWechatAppAPI(thirdPayReq);
                break;
            case PayConstants.PAY_CHANNEL_WX_MWEB :
                extra.clear();
                extra.put("sceneInfo",payOrder.getExtra());
                thirdPayReq.setExtra(JSONObject.fromObject(extra).toString());
                resultMap = wechatPayService.doWechatH5API(thirdPayReq);
                break;

            case PayConstants.PAY_CHANNEL_WX_NATIVE :
                extra.clear();
                extra.put("productId",payOrder.getPayOrderNo());
                thirdPayReq.setExtra(JSONObject.fromObject(extra).toString());
                resultMap = wechatPayService.doWechatNativeAPI(thirdPayReq);
                break;
            default:
                resultMap = null;
                break;


        }
        return TpayUtils.setSuccessResultMap("",resultMap);
    }

}
