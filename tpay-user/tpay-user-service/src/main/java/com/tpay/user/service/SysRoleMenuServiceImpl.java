package com.tpay.user.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tpay.base.service.BaseServiceImpl;
import com.tpay.cache.annotation.Cacheable;
import com.tpay.user.mapper.SysMenuMapper;
import com.tpay.user.mapper.SysRoleMenuMapper;
import com.tpay.user.model.SysMenu;
import com.tpay.user.model.SysRoleMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-07-03 15:14
 **/
@Service("sysRoleMenuService")
@CacheConfig(cacheNames = "sysRoleMenu")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {

    private static Logger logger = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public String getTreeByRoleId(Long roleId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId",roleId);
        params.put("enable",1);
        List<Long> ids = sysRoleMenuMapper.selectIdPage(params);
        // 角色已有权限
        List<SysRoleMenu> rolePermissions = this.getList(ids);

        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("enable",1);
        List<SysMenu> menuList = sysMenuMapper.selectByParam(columnMap);

        // 目录
        JSONArray folders = new JSONArray();
        for (SysMenu sysMenu: menuList) {
            if (sysMenu.getParentId() != 0 ||  Integer.valueOf(sysMenu.getMenuType()).intValue()!=1) {
                continue;
            }
            JSONObject node = new JSONObject();
            node.put("id", sysMenu.getTid());
            node.put("name", sysMenu.getMenuName());
            node.put("open", true);
            for (SysRoleMenu sysRoleMenu : rolePermissions) {
                if (sysRoleMenu.getMenuId().equals(sysMenu.getId()) ) {
                    node.put("checked", true);
                }
            }
            folders.add(node);
            // 菜单
            JSONArray menus = new JSONArray();
            for (Object folder : folders) {
                for (SysMenu sysMenu2: menuList) {
                    if (sysMenu2.getParentId() != ((JSONObject) folder).getLongValue("id") ||  Integer.valueOf(sysMenu2.getMenuType()).intValue()!= 2) {
                        continue;
                    }
                    JSONObject node2 = new JSONObject();
                    node2.put("id", sysMenu2.getTid());
                    node2.put("name", sysMenu2.getMenuName());
                    node2.put("open", true);
                    for (SysRoleMenu sysRoleMenu : rolePermissions) {
                        if (sysRoleMenu.getMenuId() .equals(sysMenu2.getId())) {
                            node2.put("checked", true);
                        }
                    }
                    menus.add(node2);
                    // 按钮
                    JSONArray buttons = new JSONArray();
                    for (Object menu : menus) {
                        for (SysMenu sysMenu3: menuList) {
                            if (sysMenu3.getParentId() != ((JSONObject) menu).getLongValue("id") ||  Integer.valueOf(sysMenu3.getMenuType()).intValue()!= 3) {
                                continue;
                            }
                            JSONObject node3 = new JSONObject();
                            node3.put("id", sysMenu3.getTid());
                            node3.put("name", sysMenu3.getMenuName());
                            node3.put("open", true);
                            for (SysRoleMenu sysRoleMenu : rolePermissions) {
                                if (sysRoleMenu.getMenuId() .equals(sysMenu3.getId())) {
                                    node3.put("checked", true);
                                }
                            }
                            buttons.add(node3);
                        }
                        if (buttons.size() > 0) {
                            ((JSONObject) menu).put("children", buttons);
                            buttons = new JSONArray();
                        }
                    }
                }
                if (menus.size() > 0) {
                    ((JSONObject) folder).put("children", menus);
                    menus = new JSONArray();
                }
            }
        }
        return folders.toString();
    }
}
