package com.tpay.cache.dict;




import com.tpay.cache.dict.model.Dict;
import com.tpay.cache.dict.model.DictSet;
import com.tpay.cache.redis.JedisClient;
import com.tpay.common.utils.SpringContextUtil;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tuyong
 * @date 2017年1月8日
 * @desc 缓存对象 redis实现
 */
@SuppressWarnings("rawtypes")
@Component
public class RedisCache implements ICacheForDict<Dict> {

	private JedisClient jedisClient;

	@Override
	public void addDictSet(String key, DictSet dictSet) {
		if(jedisClient==null) {
			jedisClient = (JedisClient) SpringContextUtil.getBean("jedisClient");
		}
		jedisClient.set("DICT_"+key,JSONObject.fromObject(dictSet).toString());
	}

	@Override
	public List<Dict> getDicts(String key) {
		if(jedisClient==null) {
			jedisClient = (JedisClient)SpringContextUtil.getBean("jedisClient");
		}
		String jsonString=(String) jedisClient.get("DICT_"+key);
		if(jsonString!=null && !"".equals(jsonString)){
			return getDictSet(jsonString).getDicts();
		}else{
			return null;
		}
	}

	private DictSet getDictSet(String jsonString){
		if(jsonString!=null && !"".equals(jsonString)){
			JSONObject jsonObject=JSONObject.fromObject(jsonString);
			Map<String, Class> map=new HashMap<String, Class>();
			map.put("dicts", Dict.class);
			return (DictSet) JSONObject.toBean(jsonObject, DictSet.class,map);
		}
		return null;
	}
	
	@Override
	public String getDict(String key, String detailKey) {
		if(jedisClient==null) {
			jedisClient = (JedisClient)SpringContextUtil.getBean("jedisClient");
		}
		String jsonString=(String) jedisClient.get(key);
		DictSet dictSet=getDictSet(jsonString);
		if(dictSet!=null){
			return dictSet.getValue(detailKey);
		}
		return null;
	}

	@Override
	public void clearDictsByKeyHead(String keyHead) {
		if(jedisClient==null) {
			jedisClient = (JedisClient)SpringContextUtil.getBean("jedisClient");
		}
		if(keyHead!=null && !"".equals(keyHead)){
			Set<byte[]> keys=jedisClient.keys("DICT_"+keyHead+"*");
			for (byte[] key : keys) {
				jedisClient.del(key.toString());
			}
		}
	}

}
