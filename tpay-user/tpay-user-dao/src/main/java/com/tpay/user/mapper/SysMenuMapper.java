package com.tpay.user.mapper;

import com.tpay.base.mapper.BaseMapper;
import com.tpay.user.model.SysMenu;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
* @desc 菜单Mapper接口
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/
@Component
public class SysMenuMapper extends BaseMapper<SysMenu> {

    public List<Map<String,Object>> selectMenuByUserId( Map<String, Object> params){
        try {
            return getSqlSession().selectList(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() , params);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return null;
        }
    }

}