package com.tpay.user.model;
import com.tpay.base.model.BaseModel;


/**
* @desc 菜单实体
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/

public class SysMenu extends BaseModel {
    private static final long serialVersionUID = 1L;
    
    /**
     * 上级id
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单类型（0：目录 1：菜单 2：按钮）
     */
    private String menuType;
    /**
     * 菜单key
     */
    private String menuKey;
    /**
     * 请求url
     */
    private String request;
    /**
     * css图标样式
     */
    private String icon;
    /**
     * 是否隐藏
     */
    private String isHide;
    /**
     * 排序号
     */
    private String sort;

    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuKey() {
        return menuKey;
    }
    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIsHide() {
        return isHide;
    }
    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }


    @Override
    public String toString() {
        return "SysMenu{" +
                "parentId=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", menuKey='" + menuKey + '\'' +
                ", request='" + request + '\'' +
                ", icon='" + icon + '\'' +
                ", isHide='" + isHide + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}