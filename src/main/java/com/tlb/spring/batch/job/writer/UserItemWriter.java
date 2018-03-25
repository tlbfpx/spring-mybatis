package com.tlb.spring.batch.job.writer;

import com.tlb.spring.model.User;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public class UserItemWriter extends MyBatisBatchItemWriter<User> {



    @Override
    public void write(List<? extends User> items) {

        items.stream().forEach(e -> {
            e.setThread(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " writer : " + e.getUserId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        super.write(items);
    }
}