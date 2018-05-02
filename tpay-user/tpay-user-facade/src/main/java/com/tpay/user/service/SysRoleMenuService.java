package com.tpay.user.service;


import com.tpay.base.service.BaseService;
import com.tpay.common.annotation.DataSource;
import com.tpay.user.model.SysRoleMenu;

/**
 * @desc
 * @author Trazen
 * @since 2018-03-29
 * @version 1.0
 */
public interface SysRoleMenuService  extends BaseService<SysRoleMenu> {


    /**
     * 获取菜单树
     * @param roleId
     * @return
     */
    @DataSource("read")
    String getTreeByRoleId(Long roleId);
}
