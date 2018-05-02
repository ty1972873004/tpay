package com.tpay.upms.controller.sso;




import com.tpay.base.controller.BaseController;
import com.tpay.common.utils.BeanUtils;
import com.tpay.user.model.SysUser;
import com.tpay.user.service.SysAuthorizeService;
import com.tpay.user.service.SysUserService;
import com.tpay.user.utils.MenuTreeUtil;
import com.tpay.user.vo.MenuTreeObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @desc 
 * @author tuyong
 * @since 2017/6/19
 * @version 1.0
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class ManageController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysAuthorizeService sysAuthorizeService;



	@ApiOperation(value = "后台首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) throws Exception {
		// 已注册系统
	    //TODO
		// 当前登录用户权限
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		SysUser sysUser = sysUserService.selectUserByUsername(username);

		List<Map<String, Object>> tPermissions = sysAuthorizeService.selectMenuByUserId(sysUser.getId());
		List<MenuTreeObject> list=new ArrayList<MenuTreeObject>();
		for(Map<String, Object> map:tPermissions){
			MenuTreeObject ts = new MenuTreeObject();
			BeanUtils.flushObject(ts,map);
			list.add(ts);
		}

		MenuTreeUtil menuTreeUtil = new MenuTreeUtil();
		List<MenuTreeObject> ns = menuTreeUtil.getChildTreeObjects(list, 0);

		modelMap.addAttribute("sysUser", sysUser);
		modelMap.put("list", ns);
		return "/manager/index.jsp";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request) throws Exception {
		String v = request.getParameter("v");
		return "/manager/welcome.jsp?v="+v;
	}

}