package com.web.configure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/12/2
 */
@Configuration
public class DataSourceConfiguration {

    @Value("${spring.datasource.driverClassName}")
    private String driverName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String userPassword;

    /**
     * 数据源
     */
    @Bean(destroyMethod = "close", name = "dataSource")
    public HikariDataSource buildDataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverName);
        config.setJdbcUrl(url);
        config.setUsername(userName);
        config.setPassword(userPassword);
        return new HikariDataSource(config);
    }


    /**
     * 事务
     */
    @Bean(name = "transactionManager")
    @Autowired
    protected PlatformTransactionManager createTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
