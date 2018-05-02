package com.tpay.sys.service;

import com.tpay.base.service.BaseServiceImpl;
import com.tpay.cache.dict.CacheUtils;
import com.tpay.cache.dict.model.Dict;
import com.tpay.cache.dict.model.DictSet;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.sys.mapper.SysDicIndexMapper;
import com.tpay.sys.mapper.SysDicMapper;
import com.tpay.sys.model.SysDic;
import com.tpay.sys.model.SysDicIndex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-17 16:45
 **/
@Service("sysDicService")
public class SysDicServiceImpl extends BaseServiceImpl<SysDic> implements SysDicService {

    @Autowired
    private SysDicMapper dicMapper;
    @Autowired
    private SysDicIndexMapper dicIndexMapper;

    @Override
    public void init() {
        List<DictSet> dictSets=new ArrayList<DictSet>();
        List<Long> records = dicIndexMapper.selectIdPage(Collections.<String, Object>emptyMap());
        Map<String, Object> params = InstanceUtil.newHashMap();
        List<SysDic> lists = InstanceUtil.newArrayList();
        for(Long id:records){
            SysDicIndex sysDicIndex = dicIndexMapper.get(id);
            dictSets.add(new DictSet(sysDicIndex.getKeyValue()));
        }

        for (DictSet dictSet : dictSets) {
            SysDicIndex sysDicIndex = new SysDicIndex();
            sysDicIndex.setKeyValue(dictSet.getKey());
            sysDicIndex.setEnable(true);
            sysDicIndex = dicIndexMapper.selectOne(sysDicIndex);
            params.put("indexId", sysDicIndex.getId());
            lists = dicMapper.selectByParam(params);
            for(SysDic sdic:lists){
                dictSet.addDict(new Dict(sdic.getCode(), sdic.getCodeText(), sdic.getSortNo().toString()));
            }
        }
        for (DictSet dictSet : dictSets) {
            CacheUtils.getCacheForDict().addDictSet(dictSet.getKey(), dictSet);
        }
    }
}
