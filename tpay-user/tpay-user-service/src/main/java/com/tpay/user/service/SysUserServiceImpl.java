package com.tpay.user.service;

import com.imadcn.framework.idworker.generator.IdGenerator;
import com.tpay.base.service.BaseServiceImpl;
import com.tpay.cache.annotation.Cacheable;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.user.mapper.SysMenuUserMapper;
import com.tpay.user.mapper.SysRoleMapper;
import com.tpay.user.mapper.SysRoleMenuMapper;
import com.tpay.user.mapper.SysUserRoleMapper;
import com.tpay.user.model.SysMenuUser;
import com.tpay.user.model.SysRole;
import com.tpay.user.model.SysUser;
import com.tpay.user.model.SysUserRole;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-26 11:17
 **/
@Service("sysUserService")
@CacheConfig(cacheNames = "sysUser")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    public IdGenerator generator;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuUserMapper sysMenuUserMapper;

    @Override
    public SysUser selectUserByUsername(String username) {
        Map<String, Object> queryMap = InstanceUtil.newHashMap();
        queryMap.put("loginName", username);
        return this.queryOne(queryMap);
    }

    @Override
    public List<SysRole> selectRoleByUserId(Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        List<Long> ids = sysUserRoleMapper.selectIdPage(map);
        List<SysUserRole> userRoles = sysUserRoleService.getList(ids);
        ;
        if (userRoles.size() == 0 || userRoles == null) {
            return null;
        } else {
            return sysRoleMapper.selectBatchIdByList(userRoles);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserRole(String[] roleIds, long userId) {
        int result = 0;
        Map<String, Object> columnMap = InstanceUtil.newHashMap();
        columnMap.put("userId",userId);
        columnMap.put("updateTime",new Timestamp(System.currentTimeMillis()));
        columnMap.put("updateBy",1L);
        sysUserRoleMapper.deleteByParam(columnMap);
        // 增加新记录
        if (null != roleIds) {
            for (String roleId : roleIds) {
                if (StringUtils.isBlank(roleId)) {
                    continue;
                }
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setId(generator.nextId());
                sysUserRole.setRoleId(Long.parseLong(roleId));
                sysUserRole.setUserId(userId);
                sysUserRole.setCreateTime(new Timestamp(System.currentTimeMillis()));
                sysUserRole.setEnable(true);
                result = sysUserRoleMapper.insert(sysUserRole);
            }
        }

        //添加用户权限
        List<Long> idList = new ArrayList<Long>();
        for (int i = 0; i < roleIds.length; i++) {
            idList.add(Long.parseLong(roleIds[i]));
        }
        List<Long> menuIds =  sysRoleMenuMapper.selectMenuIds(idList);


        Map<String, Object> selectParams = InstanceUtil.newHashMap();
        selectParams.put("userId",userId);
        List<Long> ids = sysMenuUserMapper.selectIdPage(selectParams);
        int count = sysMenuUserMapper.realDeleteBatch(ids);
        for(Long menuId:menuIds){
            Map<String, Object> params = InstanceUtil.newHashMap();
            params.put("userId",userId);
            params.put("menuId",menuId);
            params.put("enable",1);
           //增加角色添加的新权限
            SysMenuUser sysMenuUser = new SysMenuUser();
            sysMenuUser.setId(generator.nextId());
            sysMenuUser.setUserId(userId);
            sysMenuUser.setMenuId(menuId);
            sysMenuUser.setEnable(true);
            sysMenuUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
            sysMenuUserMapper.insert(sysMenuUser);
    }
    }
}
