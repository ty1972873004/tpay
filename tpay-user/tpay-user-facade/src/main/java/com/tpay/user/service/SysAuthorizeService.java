package com.tpay.user.service;



import com.tpay.base.service.BaseService;
import com.tpay.common.annotation.DataSource;
import com.tpay.user.model.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysAuthorizeService extends BaseService<SysMenu> {

    /**
     * 查找用户权限
     * @param userId
     * @return
     */
    @DataSource("read")
    List<SysMenu>  selectPermissionByUserId(Long userId);

    /**
     * 查找用户菜单权限
     * @param userId
     * @return
     */
    @DataSource("read")
    List<Map<String,Object>> selectMenuByUserId(Long userId);
}
