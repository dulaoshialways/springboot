package com.du.springboot.conf.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author djg
 * @date 2018/12/17 23:15
 * @des
 */
@Configuration
public class ShiroConf {

    /**
     * 创建ShiroFilterFactoryBean
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilter.setSecurityManager(securityManager);
        /**
         * shiro内置过滤器，可以实现权限相关的拦截器
         * 常用过滤器：
         *  anon:无需认证（登录）可以访问
         *  authc:必须认证才可以访问
         *  user:如果使用rememberMe可以使用
         *  perms:该资源必须得到资源权限才可以访问
         *  role:该资源必须得到角色权限才可以访问
         */
        //添加shiro内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();

        //设置登录页
        shiroFilter.setLoginUrl("/login.html");

        filterMap.put("/login.html", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean("userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
