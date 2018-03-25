package com.tlb.spring.batch.job.listener;

import com.tlb.spring.model.User;
import org.springframework.batch.core.ItemProcessListener;

public class UserItemProcessListener implements ItemProcessListener<User,User> {


    @Override
    public void beforeProcess(User user) {
        System.out.println("before process  " + Thread.currentThread().getName());

    }

    @Override
    public void afterProcess(User user, User user2) {
        System.out.println("after process  " + Thread.currentThread().getName() );
    }

    @Override
    public void onProcessError(User user, Exception e) {
        System.out.println(" process exception" + e.getMessage());
    }
}
