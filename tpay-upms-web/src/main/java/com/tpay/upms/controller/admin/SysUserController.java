package com.tpay.upms.controller.admin;



import com.tpay.base.controller.BaseController;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.MD5Util;
import com.tpay.common.utils.Request2ModelUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.user.model.SysRole;
import com.tpay.user.model.SysUser;
import com.tpay.user.service.SysRoleService;
import com.tpay.user.service.SysUserService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-07-04 16:02
 **/
@Controller
@RequestMapping("/user")
@Api(value = "用户管理", description = "用户管理")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;


    @RequiresPermissions("manager:user:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(){
        return "/manager/user/list.jsp";
    }


    @RequiresPermissions("manager:user:read")
    @ResponseBody
    @RequestMapping("findByPage")
    public Page<SysUser> findByPage(String pageNow,
                                    String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", "1");
        params.put("orderBy", "id_");
        Page<SysUser> list=sysUserService.query(params);
        return list;
    }

    @RequiresPermissions("manager:user:add")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(ModelMap modelMap){
        return "/manager/user/add.jsp";
    }

    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist(String name) {
        SysUser sysUser = sysUserService.selectUserByUsername(name);
        if(sysUser == null){
            return true;
        }else{
            return false;
        }
    }

    @RequiresPermissions("manager:user:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ModelMap model, HttpServletRequest request, SysUser sysUser){
        sysUser = Request2ModelUtil.covert(SysUser.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysUser.getCreateBy() == null) {
            sysUser.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysUser.getUpdateBy() == null) {
            sysUser.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysUser.setUpdateBy(uid);
        }
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        sysUser.setSalt(salt);
        sysUser.setPassword(MD5Util.MD5(sysUser.getPassword() + sysUser.getSalt()));
        sysUser.setRegisterType("1");
        sysUser.setStatus("1");
        sysUser.setLoginNum(1);
        sysUserService.insertOrUpdate(sysUser);
        return setSuccessModelMap(model);
    }

    @RequiresPermissions("manager:user:del")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        String[] idArray = ids.split("-");
        int count = 0;
        for (String idStr : idArray) {
            Long id = Long.parseLong(idStr);
            SysUser sysUser = InstanceUtil.newInstance(SysUser.class);
            sysUser.setId(id);
            int result = sysUserService.deleteById(sysUser);
            count += result;
        }
        return setSuccessModelMap(modelMap,count);
    }

    @RequiresPermissions("manager:user:edit")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap){
        SysUser sysUser = sysUserService.queryById(id);
        modelMap.put("sysUser",sysUser);
        return "/manager/user/edit.jsp";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, HttpServletRequest request, SysUser sysUser){
        sysUser = Request2ModelUtil.covert(SysUser.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysUser.getCreateBy() == null) {
            sysUser.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysUser.getUpdateBy() == null) {
            sysUser.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysUser.setUpdateBy(uid);
        }
        sysUserService.insertOrUpdate(sysUser);
        return setSuccessModelMap(model);
    }



//    @ApiOperation(value = "用户角色")
    @RequiresPermissions("manager:user:role")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String role(@PathVariable("id") Long id, ModelMap modelMap) {
        // 所有角色
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("enable",1);
        List<SysRole> sysRoles = sysRoleService.queryList(params);
        // 用户拥有角色

        List<SysRole> sysUserRoles = sysUserService.selectRoleByUserId(id);
        modelMap.put("sysRoles", sysRoles);;
        modelMap.put("sysUserRoles", sysUserRoles);
        return "/manager/user/role.jsp";
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") Long id, HttpServletRequest request, ModelMap modelMap) {
        String[] roleIds = request.getParameterValues("roleId");
        sysUserService.addUserRole(roleIds,id);
        return setSuccessModelMap(modelMap);
    }


}
