package com.boss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
@SpringBootApplication
public class Start extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Start.class);
    }

    public static void main(String[] args) {
        //spring boot 启动方式，调用核心类springapplication的run方法即可
        SpringApplication.run(Start.class, args);
        System.out.println();
        System.out.println("项目启动完毕 请点击   http://localhost:9999/index.html   来访问");
        System.out.println();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10240KB");
        factory.setMaxRequestSize("12800KB");
        return factory.createMultipartConfig();
    }

}
