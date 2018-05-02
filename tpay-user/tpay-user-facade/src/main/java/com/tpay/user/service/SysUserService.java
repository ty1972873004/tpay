package com.tpay.user.service;

import com.tpay.base.service.BaseService;
import com.tpay.common.annotation.DataSource;
import com.tpay.user.model.SysRole;
import com.tpay.user.model.SysUser;

import java.util.List;

/**
 * @desc 
 * @author Trazen
 * @since 2018-03-26
 * @version 1.0
 */
public interface SysUserService extends BaseService<SysUser> {


    /**
     * 通過登錄名查詢
     * @param username
     * @return
     */
    @DataSource("read")
     SysUser selectUserByUsername(String username);


    /**
     * 通过用户id查找角色
     * @param userId
     * @return
     */
    @DataSource("read")
     List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 添加用户角色
     * @param roleIds
     * @param userId
     */
    @DataSource("write")
    void addUserRole(String[] roleIds, long userId);
}
