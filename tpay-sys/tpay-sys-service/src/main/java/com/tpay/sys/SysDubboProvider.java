package com.tpay.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @desc  启动Dubbo服务用的MainClass
 * @author Trazen
 * @since 2018-04-08
 * @version 1.0
 */
public class SysDubboProvider {
	
	private static Logger logger = LoggerFactory.getLogger(SysDubboProvider.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
		} catch (Exception e) {
			logger.error("== UserDubboProvider context start error:",e);
		}
		synchronized (SysDubboProvider.class) {
			while (true) {
				try {
					SysDubboProvider.class.wait();
				} catch (InterruptedException e) {
					logger.error("== synchronized error:",e);
				}
			}
		}
	}
    
}