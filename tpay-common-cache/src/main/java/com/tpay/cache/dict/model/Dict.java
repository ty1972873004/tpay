package com.tpay.cache.dict.model;

/**
 * @author tuyong
 * @date 2017年1月7日
 * @desc 数据字典明细项
 */
public class Dict implements Comparable<Dict>{

	
	/** 键值 **/
	private String key;
	
	/** 显示值 **/
	private String name;

	/** 排序字段 **/
	private String order;
	
	
	public Dict(){}

	public Dict(String key, String name, String order) {
		super();
		this.key = key;
		this.name = name;
		this.order = order;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public int compareTo(Dict o) {
		if(this.order!=null){
			return this.order.compareTo(o.getOrder());
		}else{
			return 0;
		}
		
	}
}
