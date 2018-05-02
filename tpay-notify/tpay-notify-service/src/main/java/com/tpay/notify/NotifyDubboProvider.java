package com.tpay.notify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @desc 启动Dubbo服务用的MainClass
 * @author Trazen
 * @since 2018-04-04
 * @version 1.0
 */
public class NotifyDubboProvider {
	
	private static Logger logger = LoggerFactory.getLogger(NotifyDubboProvider.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
		} catch (Exception e) {
			logger.error("== OrderDubboProvider context start error:",e);
		}
		synchronized (NotifyDubboProvider.class) {
			while (true) {
				try {
					NotifyDubboProvider.class.wait();
				} catch (InterruptedException e) {
					logger.error("== synchronized error:",e);
				}
			}
		}
	}
    
}