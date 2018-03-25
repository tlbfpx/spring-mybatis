package com.tlb.spring.batch.job.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class DemoReader implements ItemReader<String> {
    @Override
    public synchronized String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return "hello";
    }
}
