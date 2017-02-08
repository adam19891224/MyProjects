package com.enjoylife.base.configure;

import com.enjoylife.base.interceptors.WebCrsfInterceptor;
import com.enjoylife.base.interceptors.WebDAVInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Adam
 * 2016/7/28
 */
@Configuration
public class WebAppAdapterConfiguation extends WebMvcConfigurerAdapter {

    @Autowired
    private WebDAVInterceptor webDAVInterceptor;
    @Autowired
    private WebCrsfInterceptor webCrsfInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(webDAVInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/sorry.html");
        //添加crsf拦截器
        registry.addInterceptor(webCrsfInterceptor)
                .addPathPatterns("/blogs/*");
    }

    /**
     * 1、extends WebMvcConfigurationSupport
     * 2、重写下面方法;
     * setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认真即匹配；
     * setUseTrailingSlashMatch : 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false)
                .setUseTrailingSlashMatch(true);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST")
                .maxAge(3600);
    }
}
