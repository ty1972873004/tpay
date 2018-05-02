package com.tpay.order.mapper;

import com.tpay.base.mapper.BaseMapper;
import com.tpay.order.model.PayOrder;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-04 13:47
 **/
@Component
public class PayOrderMapper extends BaseMapper<PayOrder> {


    public PayOrder selectByOrderNo(String orderNo) {
        try {
            return getSqlSession().selectOne(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() , orderNo);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e);
            return null;
        }
    }


}
