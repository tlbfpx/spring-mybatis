package com.tlb.spring.batch.job.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class UserJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(" before job ");
        System.out.println("jobId : " + jobExecution.getJobId());
        System.out.println("create time : " + jobExecution.getCreateTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(" after job ");
        System.out.println("end time : " + jobExecution.getEndTime());
    }
}
