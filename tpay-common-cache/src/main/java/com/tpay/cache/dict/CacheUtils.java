package com.tpay.cache.dict;


import com.tpay.cache.dict.model.Dict;

import org.apache.commons.collections.CollectionUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存基础类
 * @author tuyong
 * example 获取性别
 * 获取数据字典
 * Map<String, String> map=CacheUtils.getDictLinkedHashMap(CacheConstants.SEX);
 */
public final class CacheUtils {

	private static RedisCache cache = new RedisCache();


	/***
	 * 获取数据字典缓存对象
	 * @return
	 */
	public static ICacheForDict getCacheForDict(){
		return cache ;
	}
	

	
	/**
	 * 获取数据字典list并转Map
	 * @since 
	 * @param key
	 * @return
	 * @author wmchen
	 * @throws Exception 
	 */
    public static Map getDictMap(String key) {
		return getDictLinkedHashMap(key);
	}
	
	/**
	 * 获取数据字典list并转带顺序的Map
	 * @since 2016-05-06
	 * @param key
	 * @return
	 */
	public static Map<String, String> getDictLinkedHashMap(String key){
	    List<Dict> list = getCacheForDict().getDicts(key);
	    Map<String, String> map = new LinkedHashMap<String, String>();
	    if(CollectionUtils.isNotEmpty(list) && list.size() > 0) {
	    	for(Iterator<Dict> it=list.iterator(); it.hasNext();){
	    		Dict dict = (Dict)it.next();
	    		map.put(dict.getKey(), dict.getName());
	    	}
	    }
        return map;
	}
	
}	
