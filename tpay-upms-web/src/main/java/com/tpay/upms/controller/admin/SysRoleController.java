package com.tpay.upms.controller.admin;

import com.alibaba.fastjson.JSONArray;
import com.tpay.base.controller.BaseController;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.Request2ModelUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.user.model.SysRole;
import com.tpay.user.service.SysRoleService;


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
 * @DESCRIPTION
 * @create 2017-06-21 15:22
 **/
@Controller
@RequestMapping("/role")
@Api(value = "角色管理", description = "角色管理")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequiresPermissions("manager:role:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(){
        return "/manager/role/list.jsp";
    }

    @RequiresPermissions("manager:role:read")
    @ResponseBody
    @RequestMapping("findByPage")
    public Page<SysRole> findByPage(String pageNow,
                                    String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", 1);
        params.put("orderBy", "id_");
        Page<SysRole> list = sysRoleService.queryBean(params);
        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(){
        return "/manager/role/add.jsp";
    }

//    @ApiOperation(value = "新增菜单")
    @RequiresPermissions("manager:role:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ModelMap model, HttpServletRequest request, SysRole sysRole){
        sysRole = Request2ModelUtil.covert(SysRole.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysRole.getCreateBy() == null) {
            sysRole.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysRole.getUpdateBy() == null) {
            sysRole.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysRole.setUpdateBy(uid);
        }
        sysRoleService.insertOrUpdate(sysRole);
        return setSuccessModelMap(model);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap){
        SysRole sysRole = sysRoleService.queryById(id);
        modelMap.put("sysRole",sysRole);
        return "/manager/role/edit.jsp";
    }
//    @ApiOperation(value = "新增菜单")
    @RequiresPermissions("manager:role:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, HttpServletRequest request, SysRole sysRole){
        sysRole=Request2ModelUtil.covert(SysRole.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysRole.getCreateBy() == null) {
            sysRole.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysRole.getUpdateBy() == null) {
            sysRole.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysRole.setUpdateBy(uid);
        }
        sysRoleService.insertOrUpdate(sysRole);
        return setSuccessModelMap(model);
    }

//    @ApiOperation(value = "删除菜单")
    @RequiresPermissions("manager:role:del")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        String[] idArray = ids.split("-");
        int count = 0;
        for (String idStr : idArray) {
            Long id = Long.parseLong(idStr);
            SysRole sysRole = InstanceUtil.newInstance(SysRole.class);
            sysRole.setId(id);
            int result = sysRoleService.deleteById(sysRole);
            count += result;
        }
        return setSuccessModelMap(modelMap,count);
    }

//    @ApiOperation(value = "角色权限")
    @RequiresPermissions("manager:role:auth")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public String permission(@PathVariable("id") long id, ModelMap modelMap) {
        SysRole sysRole = sysRoleService.queryById(id);
        modelMap.put("role", sysRole);
        return "/manager/role/permission.jsp";
    }


//    @ApiOperation(value = "角色权限")
    @RequiresPermissions("manager:role:auth")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object permission(@PathVariable("id") long id, HttpServletRequest request, ModelMap modelMap) {
        JSONArray datas = JSONArray.parseArray(request.getParameter("datas"));
        int result = sysRoleService.rolePermission(datas,id);
        return setSuccessModelMap(modelMap,result);
    }


}
