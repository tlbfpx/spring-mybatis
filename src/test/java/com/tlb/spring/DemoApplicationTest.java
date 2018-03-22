package com.tlb.spring;


import com.tlb.spring.mapper.UserMapper;
import com.tlb.spring.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = new User();
        user.setUserName("jack");
        user.setPassword("123456");
        user.setPhone("12345678910");
        userMapper.insert(user);
        User u = userMapper.selectByPrimaryKey(1);
        Assert.assertEquals("jack", u.getUserName());
    }

    @Test
    public void test2(){
        userMapper.insert2("winterchen", "123456", "123456789101");
        User u = userMapper.findUserByPhone("123456789101");
        Assert.assertEquals("winterchen", u.getUserName());
    }
}
