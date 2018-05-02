package com.tpay.order.mapper;

import com.tpay.base.mapper.BaseMapper;
import com.tpay.order.model.PayChannel;
import com.tpay.order.model.PayOrder;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
* @desc 支付渠道表Mapper接口
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/
@Component
public class PayChannelMapper extends BaseMapper<PayChannel> {

    public PayChannel selectOneByParms(Map<String, Object> param) {
        try {
            return getSqlSession().selectOne(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() , param);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e);
            return null;
        }
    }



}