package com.tpay.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-07-19 11:38
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-context.xml"})
public class AppNotify {

    @Test
    public void test(){
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
