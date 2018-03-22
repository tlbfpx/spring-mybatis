package com.tlb.spring.batch.job.listener;

import com.tlb.spring.model.User;
import org.springframework.batch.core.ItemReadListener;

public class UserItemReadListener implements ItemReadListener<User> {
    @Override
    public void beforeRead() {
        System.out.println(" begin read object");
    }

    @Override
    public void afterRead(User user) {
        System.out.println(" read object is :" + user.toString());
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println(" read exception" + e.getMessage());
    }
}
