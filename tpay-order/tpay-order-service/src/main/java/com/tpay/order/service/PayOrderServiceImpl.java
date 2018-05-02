package com.tpay.order.service;

import com.tpay.base.service.BaseServiceImpl;
import com.tpay.order.model.PayOrder;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-04 12:01
 **/
@Service("payOrderService")
@CacheConfig(cacheNames = "payOrder")
public class PayOrderServiceImpl extends BaseServiceImpl<PayOrder> implements  PayOrderService {
}
