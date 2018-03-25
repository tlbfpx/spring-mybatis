package com.tlb.spring.batch.job.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class UserJobExecutionListener implements JobExecutionListener {
    long start;
    long end;


    @Override
    public void beforeJob(JobExecution jobExecution) {
        start=System.currentTimeMillis();
        System.out.println(" before job  "  + Thread.currentThread().getName());
        System.out.println("jobId : " + jobExecution.getJobId());
        System.out.println("create time : " + jobExecution.getCreateTime());

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        end=System.currentTimeMillis();
        System.out.println(" after job " + Thread.currentThread().getName());
        System.out.println("end time : " + jobExecution.getEndTime());

        System.out.println("job cost : " + (end -start) + "ms");

    }
}
