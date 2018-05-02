package com.tpay.user.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.imadcn.framework.idworker.generator.IdGenerator;
import com.tpay.base.service.BaseServiceImpl;
import com.tpay.cache.annotation.CacheEvict;
import com.tpay.cache.annotation.CacheEvictItem;
import com.tpay.dao.plugins.util.Page;
import com.tpay.user.mapper.SysRoleMenuMapper;
import com.tpay.user.model.SysRole;
import com.tpay.user.model.SysRoleMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-06-15 17:00
 **/
@Service("sysRoleService")
@CacheConfig(cacheNames = "sysRole")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    public IdGenerator generator;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public Page<SysRole> queryBean(Map<String, Object> params) {
        Page<SysRole> pageInfo = super.query(params);
        return pageInfo;
    }

    @Transactional
    @Override
    public Integer rolePermission(JSONArray datas, Long id) {
        List<Long> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getLongValue("id"));
            } else {
                // 新增权限
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(generator.nextId());
                sysRoleMenu.setRoleId(id);
                sysRoleMenu.setMenuId(json.getLongValue("id"));
                sysRoleMenu.setEnable(true);
                sysRoleMenu.setCreateTime(new Timestamp(System.currentTimeMillis()));
                sysRoleMenu.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            sysRoleMenuMapper.batchDeleteByMenuId(deleteIds);

        }
        return datas.size();
    }
}
