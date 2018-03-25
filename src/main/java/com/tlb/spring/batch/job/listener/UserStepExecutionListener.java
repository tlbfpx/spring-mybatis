package com.tlb.spring.batch.job.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class UserStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println(" before step ");
        System.out.println(" partition param min:" + stepExecution.getExecutionContext().getInt("_minRecord"));
        System.out.println(" partition param max:" + stepExecution.getExecutionContext().getInt("_maxRecord"));

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println(" after step " + Thread.currentThread().getName());
        System.out.println("read count:" + stepExecution.getReadCount());
        System.out.println("write count:" + stepExecution.getWriteCount());
        System.out.println("skip count:" + stepExecution.getSkipCount());

        return stepExecution.getExitStatus();
    }
}
