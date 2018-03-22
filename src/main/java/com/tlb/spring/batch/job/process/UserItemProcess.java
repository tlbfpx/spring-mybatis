package com.tlb.spring.batch.job.process;

import com.tlb.spring.model.User;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcess implements ItemProcessor<User,User> {
    @Override
    public User process(User user) throws Exception {
        return user;
    }
}
