package com.tpay.common.utils;

import java.util.UUID;

/**
 * @author tuyong
 * @version 1.0
 * @desc UUID工具类
 * @create 2018-03-30 17:46
 **/
public class UUIDUtil {
    /**
     * 带-的UUID
     *
     * @return 36位的字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 去掉-的UUID
     *
     * @return 32位的字符串
     */
    public static String getUUID2() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
