package com.tpay.payment.base.service;

import com.tpay.payment.base.to.Pay;
import com.tpay.payment.base.to.PayBase;
import com.tpay.payment.exceptions.PayException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-09 14:07
 **/
public abstract  class Base3rdPayService {

    protected static Logger logger = LoggerFactory.getLogger(Base3rdPayService.class);


    /**
     * 封装支付参数
     * @param base
     * @return
     */
    protected abstract Map<String, String> buildParameters(PayBase base);

    /**
     * 执行
     * @param parameters
     * @return
     * @throws Exception
     */
    protected abstract Map<String, String> execute(Map<String, String> parameters) throws Exception;


    /**
     * 支付
     */
    public Map<String, String> doPay(Pay pay) {
        try {
            Map<String, String> parameters = this.buildParameters(pay);
            logger.info("调用第三方支付，参数为：" + parameters);
            return this.execute(parameters);
        } catch (Exception e) {
            logger.error("调用支付接口异常", e);
            throw new PayException("调用支付接口异常", e);
        }
    }

}
