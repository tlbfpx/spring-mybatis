package com.tlb.spring.controller;

import com.tlb.spring.model.User;
import com.tlb.spring.service.UserService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class JobController {

//    @Autowired
//    @Qualifier("importJob")
//    Job importJob;

    @Qualifier("partitionJob")
    @Autowired
    Job partitionJob;

    @Autowired
    JobLauncher jobLauncher;

//
//    @RequestMapping(value = "/job/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
//    public void findAllUser(@PathVariable("pageNum") long pageNum, @PathVariable("pageSize") long pageSize)
//            throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
//            JobRestartException, JobInstanceAlreadyCompleteException {
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addDate("date", new Date())
//                .addLong("pageNum", pageNum)
//                .addLong("pageSize", pageSize)
//                .addString("jobName", importJob.getName() + "_" + pageNum + "_" + pageSize)
//                .toJobParameters();
//        JobExecution result = jobLauncher.run(importJob, jobParameters);
//        result.getExecutionContext();
//        System.out.println(result.toString());
//    }


    @RequestMapping(value = "/partition/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public void partitionJob(@PathVariable("pageNum") long pageNum, @PathVariable("pageSize") long pageSize)
            throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addLong("pageNum", pageNum)
                .addLong("pageSize", pageSize)
                .addString("jobName", partitionJob.getName() + "_" + pageNum + "_" + pageSize)
                .toJobParameters();
        JobExecution result = jobLauncher.run(partitionJob, jobParameters);
        result.getExecutionContext();
        System.out.println(result.toString());
    }

}