package com.tpay.user.model;

import com.tpay.base.model.BaseModel;


/**
* @desc 菜单用户关联表实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class SysMenuUser extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    private Long menuId;
    /**
     *用户id
     */
    private Long userId;



    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}