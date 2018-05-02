package com.tpay.user.service;

import com.tpay.base.service.BaseService;
import com.tpay.common.annotation.DataSource;
import com.tpay.dao.plugins.util.Page;
import com.tpay.user.model.SysMenu;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-29 15:33
 **/
public interface SysMenuService extends BaseService<SysMenu>{

    /**
     * 分页查询
     * @param params
     * @return
     */
    @DataSource("read")
    Page<SysMenu> queryBean(Map<String, Object> params) ;
}
