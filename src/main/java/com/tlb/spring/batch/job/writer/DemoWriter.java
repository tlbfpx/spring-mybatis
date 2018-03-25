package com.tlb.spring.batch.job.writer;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class DemoWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        System.out.println(list.size());
    }
}
