package com.tlb.spring.batch.job.adapter;

import com.tlb.spring.model.User;
import com.tlb.spring.service.UserService;
import org.apache.ibatis.transaction.Transaction;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@StepScope
public class DemoAdapter {

    private static final Logger log = Logger.getLogger(DemoAdapter.class);

    @Autowired
    private UserService userService;


    private List<User> listUser;

    private long pageNum;

    private long pageSize;
    private String jobName;



    @PostConstruct
    public void init() {
        log.info("Initializing " + DemoAdapter.class.toString());
        listUser = userService.findAllUser((int) this.pageNum, (int) this.pageSize);
    }

    /**
     * read method delegate
     *
     * @return
     */
    public synchronized User next(Long index) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (listUser != null && listUser.size() > index) {
            User user =  listUser.get(index.intValue());
            System.out.println(Thread.currentThread().getName() + " reader : " + user.getUserId());
            user.setJobName(this.jobName);
            return user;
        } else {
            return null;
        }

    }


    public long getPageNum() {
        return pageNum;
    }

    @Value("#{jobParameters['pageNum']}")
    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    @Value("#{jobParameters['pageSize']}")
    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public String getJobName() {
        return jobName;
    }

    @Value("#{jobParameters['jobName']}")
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


}


