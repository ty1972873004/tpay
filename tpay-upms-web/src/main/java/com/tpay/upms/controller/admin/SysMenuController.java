package com.tpay.upms.controller.admin;



import com.tpay.base.controller.BaseController;
import com.tpay.common.utils.BeanUtils;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.Request2ModelUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.user.model.SysMenu;
import com.tpay.user.service.SysAuthorizeService;
import com.tpay.user.service.SysMenuService;
import com.tpay.user.service.SysRoleMenuService;
import com.tpay.user.utils.MenuTreeUtil;
import com.tpay.user.vo.MenuTreeObject;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-06-27 15:20
 **/
@Controller
@RequestMapping("/menu")
@Api(value = "菜单管理", description = "菜单管理")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysAuthorizeService sysAuthorizeService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(){
        return "/manager/menu/list.jsp";
    }

    @RequiresPermissions("manager:menu:read")
    @ResponseBody
    @RequestMapping("findByPage")
    public Page<SysMenu> findByPage(String pageNow,
                                    String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", 1);
        params.put("orderBy", "sort_");
        Page<SysMenu> list = sysMenuService.queryBean(params);
        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(){
        return "/manager/menu/add.jsp";
    }


    @ResponseBody
    @RequestMapping(value = "/reslists", method = RequestMethod.POST)
    public List<MenuTreeObject> reslists(Model model, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        List<Map<String, Object>> menuList = sysAuthorizeService.selectMenuByUserId(null);
        List<MenuTreeObject> list = new ArrayList<MenuTreeObject>();
        for(Map<String, Object> map:menuList){
            MenuTreeObject ts = new MenuTreeObject();
            BeanUtils.flushObject(ts, map);
            list.add(ts);
        }
        MenuTreeUtil menuTreeUtil=new MenuTreeUtil();
        List<MenuTreeObject> ns = menuTreeUtil.getChildTreeObjects(list, 0, "　");
        return ns;
    }

//    @ApiOperation(value = "新增菜单")
    @RequiresPermissions("manager:menu:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ModelMap model, HttpServletRequest request, SysMenu sysMenu){
        sysMenu= Request2ModelUtil.covert(SysMenu.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysMenu.getCreateBy() == null) {
            sysMenu.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysMenu.getUpdateBy() == null) {
            sysMenu.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysMenu.setUpdateBy(uid);
        }
        sysMenuService.insertOrUpdate(sysMenu);
        return setSuccessModelMap(model);
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap){
        SysMenu sysMenu = sysMenuService.queryById(id);
        modelMap.put("sysMenu",sysMenu);
        return "/manager/menu/edit.jsp";
    }

//    @ApiOperation(value = "新增菜单")
    @RequiresPermissions("manager:menu:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, HttpServletRequest request, SysMenu sysMenu){
        sysMenu=Request2ModelUtil.covert(SysMenu.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysMenu.getCreateBy() == null) {
            sysMenu.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysMenu.getUpdateBy() == null) {
            sysMenu.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysMenu.setUpdateBy(uid);
        }
        sysMenuService.insertOrUpdate(sysMenu);
        return setSuccessModelMap(model);
    }


//    @ApiOperation(value = "删除菜单")
    @RequiresPermissions("manager:menu:del")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        String[] idArray = ids.split("-");
        int count = 0;
        for (String idStr : idArray) {
            Long id = Long.parseLong(idStr);
            SysMenu sysMenu = InstanceUtil.newInstance(SysMenu.class);
            sysMenu.setId(id);
            int result = sysMenuService.deleteById(sysMenu);
            count += result;
        }
      return setSuccessModelMap(modelMap,count);
    }


//    @ApiOperation(value = "角色权限列表")
    @RequiresPermissions("manager:role:auth")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object role(@PathVariable("id") long id) {
        return JSONArray.fromObject(sysRoleMenuService.getTreeByRoleId(id));
    }
}
