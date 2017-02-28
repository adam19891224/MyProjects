package com.enjoylife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/13
 */
@SpringBootApplication//启用自动配置
public class Start implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        Date start = new Date();
        //spring boot 启动方式，调用核心类springapplication的run方法即可
        SpringApplication.run(Start.class, args);
        Date end = new Date();
        System.out.println("项目启动完毕! 总共耗时: " + (end.getTime() - start.getTime()) + " ms");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8888);
    }
}
