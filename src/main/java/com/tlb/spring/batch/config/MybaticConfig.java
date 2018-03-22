package com.tlb.spring.batch.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class MybaticConfig {

    @Autowired
    @Qualifier("batchDS")
    DataSource batchDS;

    @Autowired
    @Qualifier("masterDS")
    DataSource masterDS;

    @Bean("sqlSessionFactoryFrom")
    @Primary
    public SqlSessionFactory sqlSessionFactoryFrom() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(batchDS);
        // mapper location
        PathMatchingResourcePatternResolver pathResolver =new PathMatchingResourcePatternResolver();
        Resource[] resources = pathResolver.getResources("classpath*:mapping/*.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();

    }

    @Bean("sqlSessionFactoryTo")
    public SqlSessionFactory sqlSessionFactoryTo() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(masterDS);
        // mapper location
        PathMatchingResourcePatternResolver pathResolver =new PathMatchingResourcePatternResolver();
        Resource[] resources = pathResolver.getResources("classpath*:mapping/*.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();

    }
}
