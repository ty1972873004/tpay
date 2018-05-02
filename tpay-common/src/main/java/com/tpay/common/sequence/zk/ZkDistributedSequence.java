package com.tpay.common.sequence.zk;


import com.tpay.common.sequence.DistributedSequence;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @desc  通过zookeeper节点获取连续自增数
 * @author Administrator
 * @since 2017/7/24
 * @version 1.0
 */
public class ZkDistributedSequence implements DistributedSequence {
    
    private static Logger logger = LoggerFactory.getLogger(ZkDistributedSequence.class);

    private CuratorFramework client;

    public ZkDistributedSequence(CuratorFramework client) {
        this.client = client;
    }


    @Override
    public Long sequence(String sequenceName) {
        try {
            int value = client.setData().withVersion(-1).forPath(sequenceName,"".getBytes()).getVersion();
            return new Long(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
