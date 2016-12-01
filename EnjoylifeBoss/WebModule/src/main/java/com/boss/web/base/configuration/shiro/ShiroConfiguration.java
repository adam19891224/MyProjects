package com.boss.web.base.configuration.shiro;

import com.boss.web.base.configuration.shiro.filters.BossRoleFilter;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
@SpringBootConfiguration
public class ShiroConfiguration {
    //shiro url map集合
    private static final Map<String, String> SHIRO_URL_MAP = new LinkedHashMap<>();
    private static final Map<String, Filter> SHIRO_FILTER_MAP = new LinkedHashMap<>();

    static {
        //调用过滤filter方法
        insertUrlFilterToMap();
        //调用注册过滤url string方法
        insertUrlStringToMap();
    }

    @Bean(name = "shiroRealm")
    public ShiroRealm getYishenghuoShiroRealm(){
        return new ShiroRealm();
    }

    @Bean(name = "shiroEhcacheManager")
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie getSimpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(1800);
        return simpleCookie;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager getCookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(getSimpleCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getYishenghuoShiroRealm());
        securityManager.setCacheManager(getEhCacheManager());
        securityManager.setRememberMeManager(getCookieRememberMeManager());
        return securityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getSecurityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterBean getShiroFilterFactoryBean() {
        ShiroFilterBean shiroFilterFactoryBean = new ShiroFilterBean();
        shiroFilterFactoryBean.setSecurityManager(getSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noLogin.html");
        //将控制的参数map放入bean中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(SHIRO_URL_MAP);
        shiroFilterFactoryBean.setFilters(SHIRO_FILTER_MAP);
        return shiroFilterFactoryBean;
    }

    /**
     * 注册shiro角色filter
     *  该方法通过创建易生活角色filter  BossRoleFilter 对象来注册角色，然后放入map中，
     *  而map的key要牢记，在后面配置具体的权限时，要用这个key
     */
    private static void insertUrlFilterToMap(){
        //直接创建单个角色
        SHIRO_FILTER_MAP.put("bossFilter", new BossRoleFilter("ZHOU_YU_HONG"));
    }

    private static void insertUrlStringToMap(){
        SHIRO_URL_MAP.put("/login.html", "anon");
        SHIRO_URL_MAP.put("/doLogin.html", "anon");
        SHIRO_URL_MAP.put("/logout.html", "anon");
        SHIRO_URL_MAP.put("/noLogin.html", "anon");
        SHIRO_URL_MAP.put("/files/**", "anon");
        SHIRO_URL_MAP.put("/**", "authc, bossFilter");
    }


}
