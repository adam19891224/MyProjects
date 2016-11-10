package com.enjoylife.configure;

import com.enjoylife.interceptors.WebDAVInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Adam
 * 2016/7/28
 */
@Configuration
public class WebAppAdapterConfiguation extends WebMvcConfigurerAdapter {

    @Autowired
    private WebDAVInterceptor webDAVInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(webDAVInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/hope.html");
    }
}
