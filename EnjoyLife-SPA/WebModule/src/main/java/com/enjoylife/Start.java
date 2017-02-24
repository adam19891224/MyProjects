package com.enjoylife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/13
 */
@SpringBootApplication//启用自动配置
public class Start implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        //spring boot 启动方式，调用核心类springapplication的run方法即可
        SpringApplication.run(Start.class, args);
        System.out.println("项目启动完毕!");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8888);
    }
}
