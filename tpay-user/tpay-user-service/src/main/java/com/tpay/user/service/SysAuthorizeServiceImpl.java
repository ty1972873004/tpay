package com.tpay.user.service;



import com.tpay.base.service.BaseServiceImpl;
import com.tpay.cache.annotation.Cacheable;
import com.tpay.user.mapper.SysMenuMapper;
import com.tpay.user.mapper.SysMenuUserMapper;
import com.tpay.user.model.SysMenu;

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
 * @create 2017-06-16 16:27
 **/
@Service("sysAuthorizeService")
@CacheConfig(cacheNames = "sysAuthoriz")
public class SysAuthorizeServiceImpl extends BaseServiceImpl<SysMenu> implements SysAuthorizeService {

    @Autowired
    private SysMenuUserMapper sysMenuUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;



    @Override
    public List<SysMenu> selectPermissionByUserId(Long userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId",userId);
        List<Long> ids = sysMenuUserMapper.selectMenuIdPage(params);
        return this.getList(ids);
    }

    @Override
    public List<Map<String, Object>> selectMenuByUserId(Long userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId",userId);
        return sysMenuMapper.selectMenuByUserId(params);
    }

}
