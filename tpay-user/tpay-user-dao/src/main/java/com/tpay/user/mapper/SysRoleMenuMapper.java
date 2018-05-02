package com.tpay.user.mapper;

import com.tpay.base.mapper.BaseMapper;
import com.tpay.user.model.SysRoleMenu;

import org.springframework.stereotype.Component;

import java.util.List;

/**
* @desc 角色菜单关联表Mapper接口
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/
@Component
public class SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 菜单id
     * @param ids
     */
    public int batchDeleteByMenuId(List<Long> ids){
        try {
            return getSqlSession().delete(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName(),ids);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return 0;
        }
    }


    public List<Long>  selectMenuIds( List<Long> roleIds){
        try {
            return getSqlSession().selectList(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName(),roleIds);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return null;
        }
    }


}