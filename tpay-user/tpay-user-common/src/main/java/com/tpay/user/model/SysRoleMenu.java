package com.tpay.user.model;
import com.tpay.base.model.BaseModel;


/**
* @desc 角色菜单关联表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class SysRoleMenu extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long roleId;
    /**
     *
     */
    private Long menuId;



    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}