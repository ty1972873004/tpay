package com.tpay.user.service;

import com.alibaba.fastjson.JSONArray;
import com.tpay.base.service.BaseService;
import com.tpay.common.annotation.DataSource;
import com.tpay.dao.plugins.util.Page;
import com.tpay.user.model.SysRole;

import java.util.Map;

/**
 * @desc 
 * @author Trazen
 * @since 2018-03-29
 * @version 1.0
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     *
     * @param params
     * @return
     */
    @DataSource("read")
     Page<SysRole> queryBean(Map<String, Object> params) ;

     /**
      * 角色权限
      * @param datas 权限数据
      * @param id 角色id
      * @return
      */
     @DataSource("write")
     Integer rolePermission(JSONArray datas, Long id);
}
