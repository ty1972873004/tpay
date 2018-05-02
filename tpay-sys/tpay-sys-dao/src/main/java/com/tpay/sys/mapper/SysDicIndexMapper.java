package com.tpay.sys.mapper;

import com.tpay.base.mapper.BaseMapper;
import com.tpay.sys.model.SysDicIndex;

import org.springframework.stereotype.Component;

/**
* @desc 字典表Mapper接口
* @author Trazen
* @since 2018-03-28
* @version 1.0
*/
@Component
public class SysDicIndexMapper extends BaseMapper<SysDicIndex> {

    public SysDicIndex selectOne(SysDicIndex sysDicIndex) {
        try {
            return getSqlSession().selectOne(entityClass.getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() , sysDicIndex);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return null;
        }
    }
}