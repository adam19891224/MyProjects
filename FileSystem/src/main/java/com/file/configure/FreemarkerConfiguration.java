package com.file.configure;

import com.file.util.ConUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.autoconfigure.template.TemplateLocation;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;
import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/14
 */
@Configuration
@ConditionalOnClass({ freemarker.template.Configuration.class,
        FreeMarkerConfigurationFactory.class })
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(FreeMarkerProperties.class)
public class FreemarkerConfiguration {

    private static final Log logger = LogFactory
            .getLog(FreeMarkerAutoConfiguration.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FreeMarkerProperties properties;

    @PostConstruct
    public void checkTemplateLocationExists() {
        if (this.properties.isCheckTemplateLocation()) {
            TemplateLocation templatePathLocation = null;
            List<TemplateLocation> locations = new ArrayList<TemplateLocation>();
            for (String templateLoaderPath : this.properties.getTemplateLoaderPath()) {
                TemplateLocation location = new TemplateLocation(templateLoaderPath);
                locations.add(location);
                if (location.exists(this.applicationContext)) {
                    templatePathLocation = location;
                    break;
                }
            }
            if (templatePathLocation == null) {
                logger.warn("Cannot find template location(s): " + locations
                        + " (please add some templates, "
                        + "check your FreeMarker configuration, or set "
                        + "spring.freemarker.checkTemplateLocation=false)");
            }
        }
    }

    protected static class FreeMarkerConfiguration {

        @Autowired
        protected FreeMarkerProperties properties;

        @Autowired
        private FreemarkerTime freemarkerTime;

        void applyProperties(FreeMarkerConfigurationFactory factory) {
            factory.setTemplateLoaderPaths(this.properties.getTemplateLoaderPath());
            factory.setPreferFileSystemAccess(this.properties.isPreferFileSystemAccess());
            factory.setDefaultEncoding(this.properties.getCharsetName());
            //添加自定义函数
            factory.setFreemarkerVariables(this.getFreemarkerVariables());
            Properties settings = new Properties();
            settings.putAll(this.properties.getSettings());
            factory.setFreemarkerSettings(settings);
        }

        private Map<String, Object> getFreemarkerVariables(){
            Map<String, Object> variables = ConUtils.hashmap();
            variables.put("time", freemarkerTime);
            return variables;
        }

    }

    @Configuration
    @ConditionalOnNotWebApplication
    public static class FreeMarkerNonWebConfiguration extends FreeMarkerConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public FreeMarkerConfigurationFactoryBean freeMarkerConfiguration() {
            FreeMarkerConfigurationFactoryBean freeMarkerFactoryBean = new FreeMarkerConfigurationFactoryBean();
            applyProperties(freeMarkerFactoryBean);
            return freeMarkerFactoryBean;
        }

    }

    @Configuration
    @ConditionalOnClass(Servlet.class)
    @ConditionalOnWebApplication
    public static class FreeMarkerWebConfiguration extends FreeMarkerConfiguration {

        @Bean
        @ConditionalOnMissingBean(FreeMarkerConfig.class)
        public FreeMarkerConfigurer freeMarkerConfigurer() {
            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            applyProperties(configurer);
            return configurer;
        }

        @Bean
        public freemarker.template.Configuration freeMarkerConfiguration(
                FreeMarkerConfig configurer) {
            return configurer.getConfiguration();
        }

        @Bean
        @ConditionalOnMissingBean(name = "freeMarkerViewResolver")
        @ConditionalOnProperty(name = "spring.freemarker.enabled", matchIfMissing = true)
        public FreeMarkerViewResolver freeMarkerViewResolver() {
            FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
            this.properties.applyToViewResolver(resolver);
            return resolver;
        }

    }

}
