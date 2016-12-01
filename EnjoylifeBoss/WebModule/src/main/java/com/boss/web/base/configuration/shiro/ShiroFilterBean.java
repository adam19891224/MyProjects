package com.boss.web.base.configuration.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * shiro拦截的url中自带的jsessionid参数
 * ranmin-zhouyuhong
 * 2016/11/15
 */
class ShiroFilterBean extends ShiroFilterFactoryBean {

    //覆盖父类的AbstractShiroFilter对象，这里使用自定义的filter
    private static final class BossSpringShiroFilter extends AbstractShiroFilter {

        protected BossSpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
            super();
            if (webSecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager 不能为空");
            }
            setSecurityManager(webSecurityManager);
            if (resolver != null) {
                setFilterChainResolver(resolver);
            }
        }

        //覆盖父类的wrapServletResponse方法，这里使用自定义的http
        @Override
        protected ServletResponse wrapServletResponse(HttpServletResponse orig, ShiroHttpServletRequest request) {
            return new ShiroHttpResponse(orig, getServletContext(), request);
        }
    }

}
