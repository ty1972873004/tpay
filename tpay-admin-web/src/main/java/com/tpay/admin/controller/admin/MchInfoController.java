package com.tpay.admin.controller.admin;

import com.tpay.base.controller.BaseController;
import com.tpay.cache.dict.CacheUtils;
import com.tpay.common.constants.CacheConstants;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.Request2ModelUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.sys.constants.SequenceConstants;
import com.tpay.sys.service.SequenceService;
import com.tpay.user.model.MchInfo;
import com.tpay.user.service.MchInfoService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-18 11:53
 **/
@Controller
@RequestMapping("/mchinfo")
@Api(value = "商户管理", description = "商户管理")
public class MchInfoController extends BaseController {

    @Autowired
    private MchInfoService mchInfoService;

    @Autowired
    private SequenceService sequenceService;

    @RequiresPermissions("manager:mchinfo:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(){
        return "/manager/mchinfo/list.jsp";
    }

    @RequiresPermissions("manager:mchinfo:read")
    @ResponseBody
    @RequestMapping("findByPage")
    public Page<MchInfo> findByPage(String pageNow,
                                     String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", "1");
        params.put("orderBy", "id_");
        Page<MchInfo> list = mchInfoService.query(params);
        return list;
    }

    @RequiresPermissions("manager:mchinfo:add")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(ModelMap modelMap){
        return "/manager/mchinfo/add.jsp";
    }

    @RequiresPermissions("manager:mchinfo:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ModelMap model, HttpServletRequest request, MchInfo mchInfo){
        mchInfo = Request2ModelUtil.covert(MchInfo.class,request);
        mchInfo.setMchId(sequenceService.getSequence(SequenceConstants.MCH_ID));
        Long uid = WebUtil.getCurrentUser();
        if (mchInfo.getCreateBy() == null) {
            mchInfo.setCreateBy(uid == null ? 1 : uid);
        }
        if (mchInfo.getUpdateBy() == null) {
            mchInfo.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            mchInfo.setUpdateBy(uid);
        }
        mchInfoService.insertOrUpdate(mchInfo);
        return setSuccessModelMap(model);
    }

    @RequiresPermissions("manager:mchinfo:read")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap,Map<String ,String> mchTypeMap){

        mchTypeMap = CacheUtils.getDictLinkedHashMap(CacheConstants.MCHTYPE);
        MchInfo mchInfo = mchInfoService.queryById(id);
        modelMap.put("mchInfo",mchInfo);
        modelMap.put("mchTypeMap",mchTypeMap);
        return "/manager/mchinfo/detail.jsp";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap){
        MchInfo mchInfo = mchInfoService.queryById(id);
        modelMap.put("mchInfo",mchInfo);
        return "/manager/mchinfo/edit.jsp";
    }

    @RequiresPermissions("manager:mchinfo:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, HttpServletRequest request, MchInfo mchInfo){
        mchInfo = Request2ModelUtil.covert(MchInfo.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (mchInfo.getCreateBy() == null) {
            mchInfo.setCreateBy(uid == null ? 1 : uid);
        }
        if (mchInfo.getUpdateBy() == null) {
            mchInfo.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            mchInfo.setUpdateBy(uid);
        }
        mchInfoService.insertOrUpdate(mchInfo);
        return setSuccessModelMap(model);
    }


    @RequiresPermissions("manager:mchinfo:del")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        String[] idArray = ids.split("-");
        int count = 0;
        for (String idStr : idArray) {
            Long id = Long.parseLong(idStr);
            MchInfo mchInfo = InstanceUtil.newInstance(MchInfo.class);
            mchInfo.setId(id);
            int result = mchInfoService.deleteById(mchInfo);
            count += result;
        }
        return setSuccessModelMap(modelMap,count);
    }


}
