package com.tlb.spring.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Configuration
//@EnableAutoConfiguration(exclude = {BatchAutoConfiguration.class, DataSourceAutoConfiguration.class,
//        WebMvcAutoConfiguration.class})
//
//@Import(BatchAdminConfig.class)
public class SpringBatchAdmin extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchAdmin.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBatchAdmin.class);
    }

}
