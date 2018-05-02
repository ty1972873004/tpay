package com.tpay.admin.controller.admin;

import com.tpay.base.controller.BaseController;
import com.tpay.cache.dict.CacheUtils;
import com.tpay.common.constants.CacheConstants;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.Request2ModelUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.order.model.PayChannel;
import com.tpay.order.service.PayChannelService;

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

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-23 11:21
 **/
@Controller
@RequestMapping("/channel")
public class PayChannelController extends BaseController{

    @Autowired
    private PayChannelService payChannelService;

    @RequiresPermissions("manager:order:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(){
        return "/manager/channel/list.jsp";
    }

    @RequiresPermissions("manager:channel:read")
    @ResponseBody
    @RequestMapping("findByPage")
    public Page<PayChannel> findByPage(String pageNow,
                                       String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", "1");
        params.put("orderBy", "id_");
        Page<PayChannel> list = payChannelService.query(params);
        return list;
    }

    @RequiresPermissions("manager:channel:read")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap, Map<String ,String> payChannelNameMap){

        //缓存中获取
        payChannelNameMap = CacheUtils.getDictLinkedHashMap(CacheConstants.CHANNEL_NAME);

        PayChannel payChannel = payChannelService.queryById(id);
        modelMap.put("payChannel",payChannel);
        modelMap.put("payChannelNameMap",payChannelNameMap);
        return "/manager/channel/detail.jsp";
    }

    @RequiresPermissions("manager:channel:add")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(ModelMap modelMap,Map<String ,String> payChannelMap,Map<String ,String> payChannelNameMap){
        payChannelMap = CacheUtils.getDictLinkedHashMap(CacheConstants.PAY_CHANNEL);
        payChannelNameMap = CacheUtils.getDictLinkedHashMap(CacheConstants.CHANNEL_NAME);
        modelMap.put("payChannelMap",payChannelMap);
        modelMap.put("payChannelNameMap",payChannelNameMap);
        return "/manager/channel/add.jsp";
    }

    @RequiresPermissions("manager:channel:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ModelMap model, HttpServletRequest request, PayChannel payChannel){
        payChannel = Request2ModelUtil.covert(PayChannel.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (payChannel.getCreateBy() == null) {
            payChannel.setCreateBy(uid == null ? 1 : uid);
        }
        if (payChannel.getUpdateBy() == null) {
            payChannel.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            payChannel.setUpdateBy(uid);
        }
        payChannelService.insertOrUpdate(payChannel);
        return setSuccessModelMap(model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap,Map<String ,String> payChannelMap,Map<String ,String> payChannelNameMap){
        PayChannel payChannel = payChannelService.queryById(id);
        payChannelMap = CacheUtils.getDictLinkedHashMap(CacheConstants.PAY_CHANNEL);
        payChannelNameMap = CacheUtils.getDictLinkedHashMap(CacheConstants.CHANNEL_NAME);
        modelMap.put("payChannelMap",payChannelMap);
        modelMap.put("payChannelNameMap",payChannelNameMap);
        modelMap.put("payChannel",payChannel);

        return "/manager/channel/edit.jsp";
    }

    @RequiresPermissions("manager:channel:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, HttpServletRequest request, PayChannel payChannel){
        payChannel = Request2ModelUtil.covert(PayChannel.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (payChannel.getCreateBy() == null) {
            payChannel.setCreateBy(uid == null ? 1 : uid);
        }
        if (payChannel.getUpdateBy() == null) {
            payChannel.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            payChannel.setUpdateBy(uid);
        }
        payChannelService.insertOrUpdate(payChannel);
        return setSuccessModelMap(model);
    }

    @RequiresPermissions("manager:channel:del")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        String[] idArray = ids.split("-");
        int count = 0;
        for (String idStr : idArray) {
            Long id = Long.parseLong(idStr);
            PayChannel payChannel = InstanceUtil.newInstance(PayChannel.class);
            payChannel.setId(id);
            int result = payChannelService.deleteById(payChannel);
            count += result;
        }
        return setSuccessModelMap(modelMap,count);
    }


}
