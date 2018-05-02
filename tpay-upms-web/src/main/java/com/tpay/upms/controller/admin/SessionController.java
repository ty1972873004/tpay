package com.tpay.upms.controller.admin;


import com.tpay.base.controller.BaseController;
import com.tpay.shiro.session.TpaySessionDao;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-07-07 9:10
 **/
@Controller
@Api(value = "会话管理", description = "会话管理")
@RequestMapping("/session")
public class SessionController extends BaseController {

    @Autowired
    private TpaySessionDao tpaySessionDao;


    @ApiOperation(value = "会话首页")
    @RequiresPermissions("manager:session:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index() {
        return "/manager/session/list.jsp";
    }


    @ApiOperation(value = "会话列表")
    @RequiresPermissions("manager:session:read")
    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit) {
        return tpaySessionDao.getActiveSessions(offset, limit);
    }

    @ApiOperation(value = "强制退出")
    @RequiresPermissions("manager:session:forceout")
    @RequestMapping(value = "/forceout/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object forceout(@PathVariable("ids") String ids, ModelMap modelMap) {
        int count = tpaySessionDao.forceout(ids);
        return setSuccessModelMap(modelMap,count);
    }


}
