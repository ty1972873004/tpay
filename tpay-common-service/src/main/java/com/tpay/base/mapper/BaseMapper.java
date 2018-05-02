package com.tpay.base.mapper;

import com.tpay.dao.GenericDao;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author tuyong
 * @version 1.0
 * @desc mapper接口基类
 * @create 2018-03-23 9:02
 **/

public  abstract class BaseMapper <T extends Serializable> extends SqlSessionDaoSupport implements GenericDao<T> {

    protected static Logger logger = LoggerFactory.getLogger(BaseMapper.class);

    protected Class<T> entityClass;

    /**
     * sqlmap.xml定义文件中对应的sqlid
     */
    public static final String SQLID_INSERT = "insert";

    public static final String SQLID_INSERT_BATCH = "insertBatch";

    public static final String SQLID_UPDATE = "update";

    public static final String SQLID_UPDATE_BY_PARAM = "updateByParam";

    public static final String SQLID_DELETE_BY_PK = "deleteByPk";

    public static final String SQLID_DELETE_BY_PARAM = "deleteByParam";

    public static final String SQLID_DELETE_BATCH = "deleteBatch";

    public static final String SQLID_COUNT = "count";

    public static final String SQLID_COUNT_PARAM = "countByParam";

    public static final String SQLID_SELECT_BY_PK = "selectByPk";

    public static final String SQLID_SELECT = "selectAll";

    public static final String SQLID_SELECT_BY_PARAM = "selectByParam";

    public static final String SELECT_ID_PAGE = "selectIdPage";

    @Resource(name = "sqlSessionTemplate")
    public void setSuperSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    @SuppressWarnings("unchecked")
    public BaseMapper(){
        this.entityClass = (Class<T>) getClass();
    }

    @Override
    public int insert(T entity) {
        int rows = 0;
        try {
            rows = getSqlSession().insert(entityClass.getName() + "." + SQLID_INSERT , entity);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
        }
        return rows;
    }

    @Override
    public int insertBatch(List<T> list) {
        try {
            return getSqlSession().insert(entityClass.getName() + "." + SQLID_INSERT_BATCH , list);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(T entity) {
        int rows = 0;
        try {
            rows = getSqlSession().update(entityClass.getName() + "." + SQLID_UPDATE , entity);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
        }
        return rows;
    }

    @Override
    public int updateByParam(Map<String, Object> param) {
        int rows = 0;
        try {
            rows = getSqlSession().update(entityClass.getName() + "." + SQLID_UPDATE_BY_PARAM , param);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
        }
        return rows;
    }

    @Override
    public int deleteByPk(T entity) {
        int rows = 0;
        try {
            rows = getSqlSession().delete(entityClass.getName() + "." + SQLID_DELETE_BY_PK , entity);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
        }
        return rows;
    }

    @Override
    public int deleteByParam(Map<String, Object> param) {
        int rows = 0;
        try {
            rows = getSqlSession().delete(entityClass.getName() + "." + SQLID_DELETE_BY_PARAM , param);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
        }
        return rows;
    }

    @Override
    public int deleteBatch(List<Serializable> list) {
        try {
            return getSqlSession().delete(entityClass.getName() + "." + SQLID_DELETE_BATCH,list);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return 0;
        }
    }

    @Override
    public int count() {
        int result = 0;
        try {
            result = getSqlSession().selectOne(entityClass.getName() + "." + SQLID_COUNT);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
        }
        return result;
    }

    @Override
    public int countByParam(Map<String,Object> param) {
        int result = 0;
        try {
            result = getSqlSession().selectOne(entityClass.getName() + "." + SQLID_COUNT_PARAM , param);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
        }
        return result;
    }

    @Override
    public T get(Serializable primaryKey) {
        try {
            return getSqlSession().selectOne(entityClass.getName() + "." + SQLID_SELECT_BY_PK , primaryKey);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> selectAll() {
        try {
            return getSqlSession().selectList(entityClass.getName() + "." + SQLID_SELECT);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> selectByParam(Map<String, Object> param) {
        try {
            return getSqlSession().selectList(entityClass.getName() + "." + SQLID_SELECT_BY_PARAM , param);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return null;
        }
    }


    public List<Long> selectIdPage(Map<String, Object> params){
        try {
            return getSqlSession().selectList(entityClass.getName() + "." + SELECT_ID_PAGE , params);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return null;
        }
    }

    public  List<Long> selectIdPage(RowBounds rowBounds,  Map<String, Object> params){
        try {
            return getSqlSession().selectList(entityClass.getName() + "." + SELECT_ID_PAGE , params, rowBounds);
        } catch (Exception e) {
            logger.error("mybatis操作异常"+e.getMessage());
            return null;
        }
    }
    /**
     * 日志打印
     * @param sqlId
     * @param param
     */
    public void printLog(String sqlId,Object param){
        Configuration configuration = getSqlSession().getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(sqlId);
        BoundSql boundSql = mappedStatement.getBoundSql(param);
        String sql = boundSql.getSql().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("info-sql: "+sdf.format(new Date())+"  "+sql);
    }

}
