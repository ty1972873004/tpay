package com.tpay.user.model;
import com.tpay.base.model.BaseModel;


/**
* @desc 用户角色关联表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class SysUserRole extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long userId;
    /**
     *
     */
    private Long roleId;


    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}