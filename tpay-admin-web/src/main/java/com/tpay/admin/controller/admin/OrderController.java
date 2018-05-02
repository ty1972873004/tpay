package com.tpay.admin.controller.admin;

import com.tpay.base.controller.BaseController;
import com.tpay.cache.dict.CacheUtils;
import com.tpay.common.constants.CacheConstants;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.order.model.PayOrder;
import com.tpay.order.service.PayOrderService;
import com.tpay.user.model.SysMenu;
import com.tpay.user.model.SysUser;

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
 * @create 2018-04-04 14:20
 **/
@Controller
@RequestMapping("/order")
@Api(value = "订单管理", description = "订单管理")
public class OrderController extends BaseController{

    @Autowired
    private PayOrderService payOrderService;

    @RequiresPermissions("manager:order:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(){
        return "/manager/order/list.jsp";
    }

    @RequiresPermissions("manager:order:read")
    @ResponseBody
    @RequestMapping("findByPage")
    public Page<PayOrder> findByPage(String pageNow,
                                    String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", "1");
        params.put("orderBy", "id_");
        Page<PayOrder> list = payOrderService.query(params);
        return list;
    }

    @RequiresPermissions("manager:order:read")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap, Map<String ,String> payStatusMap){

        //缓存中获取
        payStatusMap = CacheUtils.getDictLinkedHashMap(CacheConstants.PAYSTATUS);

        PayOrder payOrder = payOrderService.queryById(id);
        modelMap.put("payOrder",payOrder);
        modelMap.put("payStatusMap",payStatusMap);
        return "/manager/order/detail.jsp";
    }

}
