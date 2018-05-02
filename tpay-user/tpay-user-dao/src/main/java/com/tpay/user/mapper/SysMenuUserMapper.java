package com.tpay.user.mapper;

import com.tpay.base.mapper.BaseMapper;
import com.tpay.user.model.SysMenuUser;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
* @desc 菜单用户关联表Mapper接口
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/
@Component
public class SysMenuUserMapper extends BaseMapper<SysMenuUser> {


    public List<Long> selectMenuIdPage(Map<String, Object> params){
        try {
            return getSqlSession().selectList(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() , params);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e);
            return null;
        }
    }

    public int realDeleteBatch(List<Long> list) {
        try {
            return getSqlSession().delete(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName(),list);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return 0;
        }
    }

}