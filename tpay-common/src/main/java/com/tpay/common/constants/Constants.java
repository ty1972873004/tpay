package com.tpay.common.constants;

import com.tpay.common.utils.InstanceUtil;

import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc 常量
 * @create 2018-03-22 17:33
 **/
public class Constants {

    /**
     * 异常信息统一头信息
     */
    public static final String Exception_Head = "A SYSTEM ERROR OCCURRED!";

    /** 缓存键值 */
    public static final Map<Class<?>, String> cacheKeyMap = InstanceUtil.newHashMap();

    /** 缓存命名空间 */
    public static final String CACHE_NAMESPACE = "TPay:";

    /** 当前用户 */
    public static final String CURRENT_USER = "CURRENT_USER";



}