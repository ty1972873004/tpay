package com.tpay.user.mapper;

import com.tpay.base.mapper.BaseMapper;
import com.tpay.user.model.SysRole;
import com.tpay.user.model.SysUserRole;

import org.springframework.stereotype.Component;

import java.util.List;

/**
* @desc 角色Mapper接口
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/
@Component
public class SysRoleMapper extends BaseMapper<SysRole> {

    /**
     *
     * @param userRoleList
     * @return
     */
    public List<SysRole> selectBatchIdByList(List<SysUserRole> userRoleList){
        try {
            return getSqlSession().selectList(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() , userRoleList);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e);
            return null;
        }
    }

}