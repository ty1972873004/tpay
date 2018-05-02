package com.tpay.user.service;


import com.tpay.base.service.BaseServiceImpl;
import com.tpay.cache.annotation.CacheEvict;
import com.tpay.cache.annotation.CacheEvictItem;
import com.tpay.cache.annotation.Cacheable;
import com.tpay.dao.plugins.util.Page;
import com.tpay.user.model.SysMenu;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-06-16 16:20
 **/
@Service("sysMenuService")
@CacheConfig(cacheNames = "sysMenu")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements SysMenuService {


    @Override
    public Page<SysMenu> queryBean(Map<String, Object> params) {
        Page<SysMenu> pageInfo = super.query(params);
        return pageInfo;
    }

}
