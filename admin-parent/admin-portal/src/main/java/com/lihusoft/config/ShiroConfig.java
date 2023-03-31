package com.lihusoft.config;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author tsc
 * @since 2023/2/10 11:15
 */
@Configuration
public class ShiroConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAap = new DefaultAdvisorAutoProxyCreator();
        defaultAap.setProxyTargetClass(true);
        return defaultAap;
    }

    /**
     * 将自己的验证方式加入容器
     */
    @Bean
    public UserRealm myShiroRealm() {
        return new UserRealm();
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setRememberMeManager(this.rememberMeManager());
        return securityManager;
    }

    /**
     * 对url的过滤筛选
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        LOGGER.info("shiroFilterFactoryBean.........................");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>(4);
        //不需要登陆的接口
        map.put("/login", "anon");
        map.put("/toLogin", "anon");
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        map.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //设置跨域
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        return cookie;
    }

    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("1145141919810000".getBytes());
        return cookieRememberMeManager;
    }

}