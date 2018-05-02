package com.tpay.sys.service;

import com.tpay.base.service.BaseService;
import com.tpay.common.annotation.DataSource;
import com.tpay.sys.model.SysDic;

/**
 * @desc  字典服务类
 * @author Trazen
 * @since 2018-04-17
 * @version 1.0
 */
public interface SysDicService extends BaseService<SysDic> {

    /**
     * 初始化缓存
     */
    @DataSource("read")
    void init();
}
