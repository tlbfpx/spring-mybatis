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
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;


@SpringBootApplication
//@EnableScheduling
@MapperScan("com.tlb.spring.mapper")
public class BootApplication extends SpringBootServletInitializer {

    @Autowired
    Job importJob;

    @Autowired
    JobLauncher jobLauncher;


    //@Scheduled(cron = "0/60 * * * * ? ")
    public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addLong("pageNum",2L)
                .addLong("pageSize",3L)
                .toJobParameters();
        JobExecution result = jobLauncher.run(importJob, jobParameters);
        result.getExecutionContext();
        System.out.println(result.toString());
    }


    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }


}
