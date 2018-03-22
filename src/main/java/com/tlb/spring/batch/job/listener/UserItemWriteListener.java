package com.tlb.spring.batch.job.listener;

import com.tlb.spring.model.User;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class UserItemWriteListener implements ItemWriteListener<User> {

    @Override
    public void beforeWrite(List<? extends User> list) {
        System.out.println("before write count is : " + list.size());
    }

    @Override
    public void afterWrite(List<? extends User> list) {
        System.out.println("after write count is : " + list.size());
    }

    @Override
    public void onWriteError(Exception e, List<? extends User> list) {
        System.out.println(" write error count is : " + list.size());
        System.out.println(" exception : " + e.getMessage());
    }


}
