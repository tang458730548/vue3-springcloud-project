package com.lihusoft.config;

import com.lihusoft.response.TbUserResp;
import com.lihusoft.service.LoginService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/2/10 11:15
 **/
public class UserRealm extends AuthorizingRealm {

    @Resource
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List<TbUserResp> userList = loginService.getUserByName(userName);
        for (TbUserResp userResp : userList) {
            //添加角色
            String roleName = userResp.getRoleName();
            simpleAuthorizationInfo.addRole(roleName);
            //添加权限
            String permissionsName = userResp.getPermissionsName();
            simpleAuthorizationInfo.addStringPermission(permissionsName);
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String userName = authenticationToken.getPrincipal().toString();
        //根据用户名去数据库查询用户信息
        List<TbUserResp> userRespList = loginService.getUserByName(userName);
        if (CollectionUtils.isEmpty(userRespList)) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            TbUserResp userResp = userRespList.get(0);
            return new SimpleAuthenticationInfo(userName, userResp.getPassword(), getName());
        }
    }
}