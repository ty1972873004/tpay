package com.tpay.cache.dict;


import com.tpay.cache.dict.model.DictSet;

import java.util.List;

/**
 * @author tuyong
 * @date 2017年1月7日
 * @desc 数据字典缓存接口
 */
public interface ICacheForDict<T> {
	
	
	/***
	 * 添加一个数据字典项目
	 * @param key 键值
	 * @param dictSet
	 */
	public void addDictSet(String key, DictSet dictSet);
	
	/***
	 * 根据键值 获取一组数据字典信息
	 * @param key
	 * @return
	 */
	public List<T> getDicts(String key);
	
	/***
	 * 根据键值，和明细的键值 获取一个数据字典明细信息
	 * @param key
	 * @param detailKey 明细项的KEY
	 * @return
	 */
	public String getDict(String key, String detailKey);
	
	/***
	 * 根据key的开头关键字 清除相关数据字典
	 * @param keyHead
	 * @return
	 */
	public void clearDictsByKeyHead(String keyHead);
	
	
	
}
