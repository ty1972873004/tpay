package com.tpay.shiro.session;

import com.tpay.cache.redis.JedisClient;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.shiro.constants.ShiroConstants;
import com.tpay.shiro.enums.OnlineStatusEnum;
import com.tpay.shiro.util.SerializableUtil;

import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tuyong
 * @version 1.0
 * @desc  基于redis的sessionDao，缓存共享session
 * @create 2018-03-30 10:36
 **/
public class TpaySessionDao extends CachingSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(TpaySessionDao.class);

    @Autowired
    private JedisClient jedisClient;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        jedisClient.set(getCacheKey(ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID,sessionId), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        logger.debug("doCreate -----> sessionId=" + session.getId());
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = (String)jedisClient.get(getCacheKey(ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID,sessionId));
        logger.debug("doReadSession -----> sessionId="+ sessionId);
        return SerializableUtil.deserialize(session);
    }


    @Override
    protected void doUpdate(Session session) {
        /**
         * 会话过期或者停止  则不更新
         */
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        /**
         * 更新session的最后一次访问时间
         */
        TpaySession tpaySession = (TpaySession)session;
        TpaySession cacheTpaySession = (TpaySession) doReadSession(session.getId());
        if (null != cacheTpaySession) {
            tpaySession.setStatus(cacheTpaySession.getStatus());
            tpaySession.setAttribute("FORCE_LOGOUT", cacheTpaySession.getAttribute("FORCE_LOGOUT"));
        }
        jedisClient.set(getCacheKey(ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID,session.getId()), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        logger.debug("doUpdate -----> sessionId="+session.getId());

    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        String tPayType = ObjectUtils.toString(session.getAttribute(ShiroConstants.UPMS_TYPE));
        if (ShiroConstants.CLIENT.equals(tPayType)) {
            // 删除局部会话和同一code注册的局部会话
            String code = (String)jedisClient.get(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,sessionId));
            jedisClient.del(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,sessionId));
            jedisClient.sRem(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,code), sessionId);
        }
        if (ShiroConstants.SERVER.equals(tPayType)) {
            // 当前全局会话code
            String code = (String)jedisClient.get(getCacheKey(ShiroConstants.TPAY_UPMS_SERVER_SESSION_ID,sessionId));
            // 清除全局会话
            jedisClient.del(getCacheKey(ShiroConstants.TPAY_UPMS_SERVER_SESSION_ID,sessionId));
            // 清除code校验值
            jedisClient.del(getCacheKey(ShiroConstants.TPAY_UPMS_SERVER_CODE,code));
            // 清除所有局部会话
            Set<String> clientSessionIds = jedisClient.sMembers(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,code));

            clientSessionIds.stream().forEach(clientSessionId -> {
                jedisClient.del(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_ID,clientSessionId));
                jedisClient.sRem(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,code), clientSessionId);
            });
            logger.debug("当前code="+code+"，对应的注册系统个数："+jedisClient.sCard(getCacheKey(ShiroConstants.TPAY_UPMS_CLIENT_SESSION_IDS,code))+"个");
            // 维护会话id列表，提供会话分页管理
            jedisClient.lRem(ShiroConstants.TPAY_UPMS_SERVER_SESSION_ID, 1, sessionId);
        }

        // 删除session
        jedisClient.del(getCacheKey(ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID,sessionId));
        logger.debug("doDelete -----> sessionId="+sessionId);
    }

    /**
     * 获取会话列表
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map sessions = InstanceUtil.newHashMap();
        //获取在线会话总数
        long total =  jedisClient.lLen(ShiroConstants.TPAY_UPMS_SERVER_SESSION_IDS);

        //取当前页会话详情
        List<String> ids = jedisClient.lRange(ShiroConstants.TPAY_UPMS_SERVER_SESSION_IDS,offset, (offset + limit - 1));

        List<Session> rows = InstanceUtil.newArrayList();
        for (String id : ids) {
            String session = (String)jedisClient.get(getCacheKey(ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID,id));
            // 过滤redis过期session
            if (null == session) {
                jedisClient.lRem(ShiroConstants.TPAY_UPMS_SERVER_SESSION_IDS, 1, id);
                total = total - 1;
                continue;
            }
            rows.add(SerializableUtil.deserialize(session));
        }
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            String session = (String)jedisClient.get(getCacheKey(ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID,sessionId));
            TpaySession tPaySession = (TpaySession) SerializableUtil.deserialize(session);
            tPaySession.setStatus(OnlineStatusEnum.FORCE_LOGOUT);
            tPaySession.setAttribute("FORCE_LOGOUT", "FORCE_LOGOUT");
            jedisClient.set(getCacheKey(ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID,sessionId),SerializableUtil.serialize(tPaySession), (int) tPaySession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状态
     *
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, OnlineStatusEnum onlineStatus) {
        TpaySession session = (TpaySession) doReadSession(sessionId);
        if (null == session) {
            return;
        }
        session.setStatus(onlineStatus);
        jedisClient.set(getCacheKey(ShiroConstants.TPAY_UPMS_SHIRO_SESSION_ID,session.getId()),SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
    }

    private String getCacheKey(String type,Object key) {
        StringBuilder builder = new StringBuilder(type);
        builder.append("_");
        builder.append(key);
        return builder.toString();
    }


}
