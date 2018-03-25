package com.tlb.spring.service;

import com.tlb.spring.model.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    List<User> findPartitionUser(User user, int pageNum, int pageSize);
}
