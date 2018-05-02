package com.tpay.sys.service;

/**
 * @desc  序列号服务类
 * @author Trazen
 * @since 2018-04-18
 * @version 1.0
 */
public interface SequenceService {

    /**
     * 创建数据节点
     */
    void createDataNode();

    /**
     * 根据序列号类型获取序列号
     * @param sequenceType
     * @return
     */
    String getSequence(String sequenceType);


}
