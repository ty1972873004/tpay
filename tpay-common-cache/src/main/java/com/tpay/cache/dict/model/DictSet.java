package com.tpay.cache.dict.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @date 2017年1月7日
 * @desc 数据字典明细集合
 */
public class DictSet implements Serializable{

	private static final long serialVersionUID = -6284403545562916929L;
	
	/** key **/
	private String key="";
	
	/** 数据字典项信息 **/
	private Map<String,String> details=null;
	
	/** 据字典集合 **/
	private List<Dict> list = new ArrayList<Dict>();

	/** 根据key 获取数据字典明细项的显示值 **/
	public String getValue(String key) {
		if (details == null) {
			initMap();
		}
		return details.get(key);
	}

	/**  初始化map **/
	private void initMap() {
		details = new HashMap<String, String>();
		for (Dict dict : this.list) {
			details.put(dict.getKey(), dict.getName());
		}

	}

	/**  添加一个数据字典信息 **/
	public void addDict(Dict dict) {
		this.list.add(dict);
	}


	/** 获取数据字典集合 **/
	public List<Dict> getDicts() {
		Collections.sort(list);
		return list;
	}

	public void setDicts(List<Dict> list) {
		this.list = list;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	
	public String getKey() {
		return key;
	}

	public DictSet() {
		super();
	}

	public DictSet(String key) {
		super();
		this.key = key;
	}
}
