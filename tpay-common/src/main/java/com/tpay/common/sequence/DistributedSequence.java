package com.tpay.common.sequence;


/**
 * @desc 序列号
 * @author Administrator
 * @since 2017/7/24
 * @version 1.0
 */
public interface DistributedSequence {

    /**
     * 获取序列号
     * @param sequenceName
     * @return
     */
     Long sequence(String sequenceName);
}
