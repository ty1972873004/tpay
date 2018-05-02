package com.tpay.user.service;

import com.tpay.base.service.BaseServiceImpl;
import com.tpay.user.model.SysUserRole;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-29 17:57
 **/
@Service("sysUserRoleService")
@CacheConfig(cacheNames = "sysUserRole")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService {

}
