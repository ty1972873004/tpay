package com.tpay.base.controller;


import com.tpay.common.config.Resources;
import com.tpay.common.constants.WebConstants;
import com.tpay.common.utils.InstanceUtil;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION Controller基类
 * @create 2017-06-19 11:16
 **/
public class BaseController {
    public static Logger logger = LoggerFactory.getLogger(BaseController.class);


    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
        return setSuccessModelMap(modelMap, null);
    }
    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, WebConstants.REQUEST_SUCCESS, data);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, int code) {
        return setModelMap(modelMap, code, null);
    }
    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, int code, Object data) {
        Map<String, Object> map = InstanceUtil.newLinkedHashMap();
        map.putAll(modelMap);
        modelMap.clear();
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            if (!key.startsWith("org.springframework.validation.BindingResult") && !key.equals("void")) {
                modelMap.put(key, map.get(key));
            }
        }
        if (data != null) {
            modelMap.put("data", data);
        }
        modelMap.put("code", code);
        modelMap.put("msg", Resources.getMessage(String.valueOf(code)));
        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }

    protected String getShiroCacheKey(String type,Object key) {
        StringBuilder builder = new StringBuilder(type);
        builder.append("_");
        builder.append(key);
        return builder.toString();
    }

    public static Map<String, String> prehandle(Map<String, String[]> paramMap) {
        Map<String, String> newParamMap = InstanceUtil.newHashMap();
        if(paramMap != null && paramMap.size() > 0) {
            Set<String> keySet = paramMap.keySet();
            for(String key : keySet) {
                String value = paramMap.get(key)[0];
                newParamMap.put(key, value);
            }
        }
        return newParamMap;
    }

    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 解析结果存储在HashMap
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList){
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        logger.error("统一异常处理：", exception);
        request.setAttribute("ex", exception);
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            request.setAttribute("requestHeader", "ajax");
        }
        if (exception instanceof UnauthenticatedException){
            return "/unauthenticated.jsp";
        }
        if (exception instanceof UnauthorizedException) {
            return "/403.jsp";
        }
        // shiro会话已过期异常
        if (exception instanceof InvalidSessionException) {
            return "/error.jsp";
        }
        return "/error.jsp";
    }


}
