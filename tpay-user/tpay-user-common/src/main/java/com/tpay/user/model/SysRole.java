package com.tpay.user.model;

import com.tpay.base.model.BaseModel;


/**
* @desc 角色实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class SysRole extends BaseModel {
    private static final long serialVersionUID = 1L;
    

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     *
     */
    private String roleKey;
    /**
     *状态
     */
    private String status;


    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleKey() {
        return roleKey;
    }
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "SysRole{" +
                "roleName='" + roleName + '\'' +
                ", roleType='" + roleType + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}