package com.file;

import com.file.util.FileUploadUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 *
 * spring boot 基础启动类，需要把该文件放在包的跟路径下面 注意，不是放在java的跟路径下面，而是项目的跟路径，
 * 即如果项目的包名是这样com/zyh/controller...com/zyh/service 则该文件就放在com/zyh下面
 *
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/12/1
 *
 * Configuration//配置控制
 * EnableAutoConfiguration//启用自动配置
 * ComponentScan//组件扫描
 *
 * 上面三个就是基础启动类的注解，解释都有了，但是我们可以用 SpringBootApplication这一个注解来代替上面三个
 *
 * 注意，上面的介绍仅仅只是在本地测试，接下来我们就介绍一下如何打包成war并成功启动，我们知道，打成war包并发布到服务器上能运行才是我们的最终目的
 *
 * 所以此时我们就把这个基础启动类继承一个父类 SpringBootServletInitializer
 * 同时覆盖父类方法configure，然后调用传入的参数的sources方法，把当前基础类的class类传入进去
 *
 * 同时，在maven的pom.xml中
 * <dependency>
 *      <groupId>org.springframework.boot</groupId>
 *      <artifactId>spring-boot-starter-tomcat</artifactId>
 *      <scope>provided</scope>
 * </dependency>
 *
 * 把starter的tomcat模块显式的隐藏掉(<scope>provided</scope> 这行配置就表示当maven在打包时，忽略这个配置所在的模块，即tomcat模块)
 * 因为我们会把war包放入真正的tomcat容器中，所以就不能受到spring的自带的tomcat的影响，所以就要过滤掉这个模块
 *
 * 最后点开idea的maven project窗口，打开Lifecycle， 右键clean，run，先清空掉现在的maven打包文件，然后右键package，run，就能生成war包了
 * 放入tomcat的webapps中，就能运行
 *
 */
@SpringBootApplication//启用自动配置
public class SpringBootStart extends SpringBootServletInitializer implements DisposableBean {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootStart.class);
    }

    public static void main(String[] args) {
        //spring boot 启动方式，调用核心类springapplication的run方法即可
        SpringApplication.run(SpringBootStart.class, args);
        System.out.println();
        System.out.println("项目启动完毕 请点击   http://localhost:10086/index.html   来访问");
        System.out.println();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10240KB");
        factory.setMaxRequestSize("12800KB");
        return factory.createMultipartConfig();
    }

    @Override
    public void destroy() throws Exception {
        logger.info("销毁线程池");
        FileUploadUtils.destory();
    }
}
