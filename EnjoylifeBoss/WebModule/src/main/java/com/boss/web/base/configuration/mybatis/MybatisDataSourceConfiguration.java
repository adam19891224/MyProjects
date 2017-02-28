package com.boss.web.base.configuration.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 数据源配置
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/12/2
 */
@SpringBootConfiguration
public class MybatisDataSourceConfiguration {

    @Value("${spring.datasource.hikari.driver-class-name}")
    private String driverName;

    @Value("${spring.datasource.hikari.jdbc-url}")
    private String url;

    @Value("${spring.datasource.hikari.username}")
    private String userName;

    @Value("${spring.datasource.hikari.password}")
    private String userPassword;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private Integer minIdle;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private Integer maxPoolSize;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private Long connectionTimeOut;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private Long idleTimeOut;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private Long maxLifeTime;

    /**
     * 数据源
     */
    @Bean(destroyMethod = "close", name = "dataSource")
    public HikariDataSource buildDataSource(){
        HikariConfig config = new HikariConfig();
        //设置驱动
        config.setDriverClassName(driverName);
        //设置jdbc连接路径
        config.setJdbcUrl(url);
        //设置用户名
        config.setUsername(userName);
        //设置密码
        config.setPassword(userPassword);
        //设置最小闲置连接数
        config.setMinimumIdle(minIdle);
        //设置最大缓存连接的大小
        config.setMaximumPoolSize(maxPoolSize);
        //设置连接超时
        config.setConnectionTimeout(connectionTimeOut);
        //设置闲置连接超时释放时间
        config.setIdleTimeout(idleTimeOut);
        //设置连接的最大生命周期
        config.setMaxLifetime(maxLifeTime);
        return new HikariDataSource(config);
    }


    /**
     * 事务
     */
    @Bean(name = "transactionManager")
    @Resource
    protected PlatformTransactionManager createTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
