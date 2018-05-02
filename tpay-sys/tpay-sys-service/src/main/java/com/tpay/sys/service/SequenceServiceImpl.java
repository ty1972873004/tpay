package com.tpay.sys.service;

import com.tpay.cache.dict.CacheUtils;
import com.tpay.common.constants.CacheConstants;
import com.tpay.common.sequence.DistributedSequence;
import com.tpay.common.utils.DateUtils;
import com.tpay.sys.constants.BusinessTypeConstants;
import com.tpay.sys.constants.SequenceConstants;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-17 15:58
 **/
@Service("sequenceService")
public class SequenceServiceImpl implements  SequenceService {

    private static Logger logger = LoggerFactory.getLogger(SequenceServiceImpl.class);


    @Autowired
    private CuratorFramework curatorClient;

    @Autowired
    private DistributedSequence distributedSequence;


    @Override
    public void createDataNode() {
        Map<String, String> seqs = CacheUtils.getDictLinkedHashMap(CacheConstants.SEQUENCE);
        //循环创建zookeeper数据节点
        seqs.forEach((k,v)->{
            System.err.println(" code : " +k + " value : " + v);
            try{
                if (curatorClient.checkExists().forPath(v)  == null) {
                    curatorClient.create().creatingParentsIfNeeded().forPath(v, "".getBytes());
                }
            }catch (Exception e){
                logger.error("创建zookeeper数据节点错误",e.getMessage());
            }catch (Throwable ex){
                logger.error("创建zookeeper数据节点错误",ex.getMessage());
            }
        });
    }

    @Override
    public String getSequence(String sequenceType) {
        Map<String, String> seqs = CacheUtils.getDictLinkedHashMap(CacheConstants.SEQUENCE);

        try {
            if(sequenceType.equals(SequenceConstants.PAY_ORDER)){
                /**
                 * 生成支付订单流水号
                 * 日期+业务号+序列号
                 */
                Long value = distributedSequence.sequence(seqs.get(SequenceConstants.PAY_ORDER));
                return DateUtils.getCurrDateTime() + BusinessTypeConstants.PAY_ORDER + String.format("%06d", new Long(value));
            }
            if(sequenceType.equals(SequenceConstants.TRANSFER_ORDER)){
                /**
                 * 生成转账订单流水号
                 * 日期+业务号+序列号
                 */
                Long value = distributedSequence.sequence(seqs.get(SequenceConstants.TRANSFER_ORDER));
                return DateUtils.getCurrDateTime() + BusinessTypeConstants.TRANSFER_ORDER + String.format("%06d", new Long(value));
            }
            if(sequenceType.equals(SequenceConstants.REFUND_ORDER)){
                /**
                 * 生成退款订单流水号
                 * 日期+业务号+序列号
                 */
                Long value = distributedSequence.sequence(seqs.get(SequenceConstants.REFUND_ORDER));
                return DateUtils.getCurrDateTime() + BusinessTypeConstants.REFUND_ORDER + String.format("%06d", new Long(value));
            }
            if(sequenceType.equals(SequenceConstants.MCH_ID)){
                /**
                 * 生成退款订单流水号
                 * 日期+业务号+序列号
                 */
                Long value = distributedSequence.sequence(seqs.get(SequenceConstants.MCH_ID));
                return  BusinessTypeConstants.MCH_ID + String.format("%07d", new Long(value));
            }
        } catch (Exception e) {
            logger.info("create sequence error!",e.getMessage());
        }
        return null;
    }
}
