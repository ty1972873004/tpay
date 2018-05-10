package com.tpay.open.service;

import com.tpay.common.utils.DateUtils;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.TpayUtils;
import com.tpay.order.model.PayOrder;
import com.tpay.order.service.PayOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QueryOrderService {

    @Autowired
    private PayOrderService payOrderService;


    public Map<String,Object> doQueryOrder(String mchId,String mchOrderNo,String payOrderNo){
        Map<String,Object> resultMap = InstanceUtil.newHashMap();
        Map<String,Object> queryMap = InstanceUtil.newHashMap();
        queryMap.put("mchId",Long.valueOf(mchId));
        if(!StringUtils.isEmpty(payOrderNo)){
            queryMap.put("payOrderNo",payOrderNo);
        }else if(!StringUtils.isEmpty(mchOrderNo)){
            queryMap.put("mchOrderNo",mchOrderNo);
        }

        PayOrder payOrder = payOrderService.queryOne(queryMap);
        resultMap.put("mchId",mchId);
        resultMap.put("payOrderNo",payOrderNo);
        resultMap.put("mchOrderNo",mchOrderNo);
        resultMap.put("payChannel",payOrder.getPayChannel());
        resultMap.put("amount",payOrder.getAmount());
        resultMap.put("currency",payOrder.getCurrency());
        resultMap.put("payStatus",payOrder.getPayStatus());
        resultMap.put("clientIp",payOrder.getClientIp());
        resultMap.put("subject",payOrder.getSubject());
        resultMap.put("body",payOrder.getBody());
        resultMap.put("notifyUrl",payOrder.getNotifyUrl());
        resultMap.put("paySuccessTime",DateUtils.parseToString(payOrder.getPaySuccessTime()));
        return resultMap;
    }

}
