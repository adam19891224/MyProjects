package com.web.configure;

import com.web.interceptors.WebDAVInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Adam
 * 2016/7/28
 */
@Configuration
public class WebAppAdapterConfiguation extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new WebDAVInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
