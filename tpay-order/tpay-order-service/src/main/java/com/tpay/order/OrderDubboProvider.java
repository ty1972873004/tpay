package com.tpay.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @desc 启动Dubbo服务用的MainClass
 * @author Trazen
 * @since 2018-04-04
 * @version 1.0
 */
public class OrderDubboProvider {
	
	private static Logger logger = LoggerFactory.getLogger(OrderDubboProvider.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
		} catch (Exception e) {
			logger.error("== OrderDubboProvider context start error:",e);
		}
		synchronized (OrderDubboProvider.class) {
			while (true) {
				try {
					OrderDubboProvider.class.wait();
				} catch (InterruptedException e) {
					logger.error("== synchronized error:",e);
				}
			}
		}
	}
    
}