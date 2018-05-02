package com.tpay.shiro.realm;

import com.tpay.common.utils.MD5Util;
import com.tpay.common.utils.PropertiesFileUtil;
import com.tpay.common.utils.StringUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.shiro.constants.ShiroConstants;
import com.tpay.user.model.SysMenu;
import com.tpay.user.model.SysRole;
import com.tpay.user.model.SysUser;
import com.tpay.user.service.SysAuthorizeService;
import com.tpay.user.service.SysUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author tuyong
 * @version 1.0
 * @desc 用户认证和授权
 * @create 2018-03-28 13:53
 **/
public class TpayRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(TpayRealm.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysAuthorizeService sysAuthorizeService;


    /**
     * 授权：验证权限时调用
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SysUser sysUser = sysUserService.selectUserByUsername(username);

        // 当前用户所有角色
        List<SysRole> sysRoles = sysUserService.selectRoleByUserId(sysUser.getId());
        Set<String> roles = new HashSet<>();
        sysRoles.stream().forEach(sysRole -> {
            if (StringUtils.isNotBlank(sysRole.getRoleName())) {
                roles.add(sysRole.getRoleName());
            }
        });

        // 当前用户所有权限
        List<SysMenu> tPayPermissions = sysAuthorizeService.selectPermissionByUserId(sysUser.getId());
        Set<String> permissions = new HashSet<>();

        tPayPermissions.stream().forEach(sysMenu -> {
            if (StringUtils.isNotBlank(sysMenu.getMenuKey())) {
                permissions.add(sysMenu.getMenuKey());
            }
        });

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        // client无密认证
        String tPayType = PropertiesFileUtil.getInstance("tpay-upms-client").get("tpay.upms.type");

        if (ShiroConstants.CLIENT.equals(tPayType)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }
        // 查询用户信息
        SysUser sysUser = sysUserService.selectUserByUsername(username);

        if (null == sysUser) {
            throw new UnknownAccountException();
        }
        if (!sysUser.getPassword().equals(MD5Util.MD5(password + sysUser.getSalt()))) {
            throw new IncorrectCredentialsException();
        }
        if (sysUser.getEnable().equals(false)) {
            throw new LockedAccountException();
        }
        WebUtil.saveCurrentUser(sysUser.getId());
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
