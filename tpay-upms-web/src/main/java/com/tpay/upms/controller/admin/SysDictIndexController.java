package com.tpay.upms.controller.admin;


import com.tpay.base.controller.BaseController;
import com.tpay.common.constants.WebConstants;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.Request2ModelUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.sys.model.SysDic;
import com.tpay.sys.model.SysDicIndex;
import com.tpay.sys.service.SysDicIndexService;
import com.tpay.sys.service.SysDicService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-08-10 10:24
 **/
@Controller
@RequestMapping("/dictIndex")
public class SysDictIndexController extends BaseController {

    @Autowired
    private SysDicIndexService sysDicIndexService;

    @Autowired
    private SysDicService sysDicService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(){
        return "/manager/dictindex/list.jsp";
    }

    @RequiresPermissions("manager:dictIndex:read")
    @ResponseBody
    @RequestMapping("findByPage")
    public Page<SysDicIndex> findByPage(String pageNow,
                                        String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", "1");
        Page<SysDicIndex> list=sysDicIndexService.query(params);
        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(){
        return "/manager/dictindex/add.jsp";
    }

    @RequiresPermissions("manager:dictIndex:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ModelMap model, HttpServletRequest request, SysDicIndex sysDicIndex){
        sysDicIndex = Request2ModelUtil.covert(SysDicIndex.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysDicIndex.getCreateBy() == null) {
            sysDicIndex.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysDicIndex.getUpdateBy() == null) {
            sysDicIndex.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysDicIndex.setUpdateBy(uid);
        }
        sysDicIndexService.insertOrUpdate(sysDicIndex);
        return setSuccessModelMap(model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap){
        SysDicIndex sysDicIndex = sysDicIndexService.queryById(id);
        modelMap.put("sysDicIndex",sysDicIndex);
        return "/manager/dictindex/edit.jsp";
    }

    @RequiresPermissions("manager:dictIndex:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, HttpServletRequest request, SysDicIndex sysDicIndex){
        sysDicIndex=Request2ModelUtil.covert(SysDicIndex.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysDicIndex.getCreateBy() == null) {
            sysDicIndex.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysDicIndex.getUpdateBy() == null) {
            sysDicIndex.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysDicIndex.setUpdateBy(uid);
        }
        sysDicIndexService.insertOrUpdate(sysDicIndex);
        return setSuccessModelMap(model);
    }


    @RequiresPermissions("manager:dictIndex:del")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        String[] idArray = ids.split("-");
        int count = 0;
        for (String idStr : idArray) {
            Long id = Long.parseLong(idStr);
            Map<String, Object> params = InstanceUtil.newHashMap();
            params.put("indexId",id);
            List<SysDic> sysDicList = sysDicService.queryList(params);

            if(sysDicList.size() > 0 ){
                return setModelMap(modelMap, WebConstants.REQUEST_FAIL,"存在字典子项");
            }
            SysDicIndex sysDicIndex = new SysDicIndex();
            sysDicIndex.setId(id);
            int result = sysDicIndexService.deleteById(sysDicIndex);
            count += result;
        }
        return setSuccessModelMap(modelMap,count);
    }
}
