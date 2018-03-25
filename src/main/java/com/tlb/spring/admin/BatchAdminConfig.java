package com.tlb.spring.admin;


import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

//@Configuration
//@ImportResource({
//    /*
//     * classpath:/org/springframework/batch/admin/web/resources/servlet-config.xml imports by default:
//     *  - spring-batch-admin-manager-1.3.0.RELEASE.jar!/META-INF/spring/batch/servlet/manager/controller-context.xml
//     *  - spring-batch-admin-manager-1.3.0.RELEASE.jar!/META-INF/spring/batch/servlet/manager/integration-context.xml
//     *  - spring-batch-admin-resources-1.3.0.RELEASE.jar!/META-INF/spring/batch/servlet/resources/resources-context.xml
//     *  - spring-batch-admin-manager-1.3.0.RELEASE.jar!/META-INF/spring/batch/servlet/manager/manager-context.xml
//     */
//        "classpath:/org/springframework/batch/admin/web/resources/servlet-config.xml",
//
//    /*
//     * classpath:/org/springframework/batch/admin/web/resources/webapp-config.xml imports by default:
//     *  - /META-INF/spring/batch/bootstrap/integration/configuration-context.xml",
//     *  - /META-INF/spring/batch/bootstrap/integration/file-context.xml",
//     *  - /META-INF/spring/batch/bootstrap/integration/jmx-context.xml",
//     *  - /META-INF/spring/batch/bootstrap/integration/launcher-context.xml",
//     *  - /META-INF/spring/batch/bootstrap/integration/restart-context.xml",
//     *  - /META-INF/spring/batch/bootstrap/manager/data-source-context.xml",
//     *  - spring-batch-admin-manager-1.3.0.RELEASE.jar!/META-INF/spring/batch/bootstrap/manager/env-context.xml", (this file contains the place holder)
//     *  - /META-INF/spring/batch/bootstrap/manager/execution-context.xml",
//     *  - /META-INF/spring/batch/bootstrap/resources/resources-context.xml",
//     *  - /META-INF/spring/batch/override/servlet/manager/integration-context.xml"
//     */
//
//        "classpath*:/META-INF/spring/batch/bootstrap/integration/configuration-context.xml", // spring.integration.default.properties
//        "classpath*:/META-INF/spring/batch/bootstrap/integration/file-context.xml",
//        //"classpath*:/META-INF/spring/batch/bootstrap/manager/jmx-context.xml",
//        "classpath*:/META-INF/spring/batch/bootstrap/integration/launcher-context.xml",
//        "classpath*:/META-INF/spring/batch/bootstrap/integration/restart-context.xml",
//        "classpath*:/META-INF/spring/batch/bootstrap/manager/data-source-context.xml",
//        //"classpath*:/META-INF/spring/batch/bootstrap/manager/env-context.xml",
//        "classpath*:/META-INF/spring/batch/bootstrap/manager/execution-context.xml",
//        "classpath*:/META-INF/spring/batch/bootstrap/resources/resources-context.xml",
//        "classpath*:/META-INF/spring/batch/override/servlet/manager/integration-context.xml"
//
//})
public class BatchAdminConfig {

    @Bean

    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {

        Resource[] resourceLst = {new ClassPathResource("batch-mysql.properties")};

        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setLocations(resourceLst);
        return configurer;

    }
}
