package com.du.springboot.conf.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import com.du.springboot.entity.User;
import com.du.springboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author djg
 * @date 2018/12/17 23:04
 * @des
 */
public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    /**
     * 执行授权登陆逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //查询用户信息
        System.out.println(userService.getUserByUserName(token.getUsername()));
        User user = userService.getUserByUserName(token.getUsername());

        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        return new SimpleAuthenticationInfo(user,user.getPassWord(),"");
    }
}
