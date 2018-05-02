package com.tpay.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tuyong
 * @version 1.0
 * @desc session监听器
 * @create 2018-03-30 16:06
 **/
public class TpaySessionListener implements SessionListener {

    private static Logger logger = LoggerFactory.getLogger(TpaySessionListener.class);

    @Override
    public void onStart(Session session) {
        logger.debug("会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        logger.debug("会话停止：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        logger.debug("会话过期：" + session.getId());
    }
}
