package com.tpay.test;

import java.math.BigDecimal;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-03-29 16:21
 **/
public class Test {
    public static void main(String[] args){
        String amount = "9";
        long alipay = new BigDecimal(amount).movePointRight(2).longValue();
         System.out.println(alipay);
    }
}
