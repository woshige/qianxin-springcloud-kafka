package com.qianxin.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    static final String MAPPER_LOCATION = "classpath:com/qianxin/mapper/*.xml";
    @Value("${pgsql.datasource.url}")
    private String url;

    @Value("${pgsql.datasource.username}")
    private String user;

    @Value("${pgsql.datasource.password}")
    private String password;

    @Value("${pgsql.datasource.driver-class-name}")
    private String driverClass;


    /**
     * druid参数
     */
    @Value("${pgsql.datasource.maxActive}")
    private String maxActive;
    @Value("${pgsql.datasource.initialSize}")
    private String initialSize;
    @Value("${pgsql.datasource.maxWait}")
    private String maxWait;
    @Value("${pgsql.datasource.minIdle}")
    private String minIdle;
    @Value("${pgsql.datasource.timeBetweenEvictionRunsMillis}")
    private String timeBetweenEvictionRunsMillis;
    @Value("${pgsql.datasource.minEvictableIdleTimeMillis}")
    private String minEvictableIdleTimeMillis;
    @Value("${pgsql.datasource.validationQuery}")
    private String validationQuery;
    @Value("${pgsql.datasource.testWhileIdle}")
    private String testWhileIdle;
    @Value("${pgsql.datasource.testOnBorrow}")
    private String testOnBorrow;
    @Value("${pgsql.datasource.testOnReturn}")
    private String testOnReturn;
    @Value("${pgsql.datasource.poolPreparedStatements}")
    private String poolPreparedStatements;
    @Value("${pgsql.datasource.maxOpenPreparedStatements}")
    private String maxOpenPreparedStatements;

    @Bean(name = "pgDataSource")
    public DataSource pgDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(Integer.parseInt(maxActive));
        druidDataSource.setInitialSize(Integer.parseInt(initialSize));
        druidDataSource.setMaxWait(Integer.parseInt(maxWait));
        druidDataSource.setMinIdle(Integer.parseInt(minIdle));
        druidDataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis));
        druidDataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis));
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(testWhileIdle));
        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
        druidDataSource.setTestOnReturn(Boolean.parseBoolean(testOnReturn));
        druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(poolPreparedStatements));
        druidDataSource.setMaxOpenPreparedStatements(Integer.parseInt(maxOpenPreparedStatements));
        return druidDataSource;
    }

    @Bean(name = "pgTransactionManager")
    public DataSourceTransactionManager pgTransactionManager() {
        return new DataSourceTransactionManager(pgDataSource());
    }

    @Bean(name = "pgSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("pgDataSource") DataSource pgDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(pgDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfiguration.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
