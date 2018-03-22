package com.tlb.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;


@SpringBootApplication
@EnableScheduling
@MapperScan("com.tlb.spring.mapper")
public class DemoApplication {

    @Autowired
    Job importJob;

    @Autowired
    JobLauncher jobLauncher;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        while (true) {
            //System.out.println(" *** job executing");
        }

    }

    @Configuration
    @ImportResource("classpath:/org/springframework/batch/admin/web/resources/servlet-config.xml")
    public class ServletConfiguration {
    }


    @Configuration
    @ImportResource("classpath:/org/springframework/batch/admin/web/resources/webapp-config.xml")
    public class WebappConfiguration {

    }


    @Scheduled(cron = "0/60 * * * * ? ")
    public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .toJobParameters();
        JobExecution result =jobLauncher.run(importJob, jobParameters);
        result.getExecutionContext();
        System.out.println(result.toString());
    }
}
