package com.tpay.sys.config;


import com.tpay.sys.listener.ApplicationContextListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tuyong
 * @version 1.0
 * @desc 监听器
 * @create 2018-04-17 17:13
 **/
@Configuration
public class ListenerConfig {

    @Bean
    public ApplicationContextListener applicationStartListener(){
        return new ApplicationContextListener();
    }
}