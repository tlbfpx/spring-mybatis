package com.tlb.spring.batch.job.adapter;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.adapter.AbstractMethodInvokingDelegator;

public class MyItemReaderAdapter<T> extends AbstractMethodInvokingDelegator<T> implements ItemReader<T>, ItemStream {

    private static final Logger log = Logger.getLogger(MyItemReaderAdapter.class);

    private long currentCount = 0;

    private final String CONTEXT_COUNT_KEY = "ReglementAdapter.count";

    /**
     * @return return value of the target method.
     */
    public synchronized T read() throws Exception {

        super.setArguments(new Long[]{currentCount++});
        log.info("Reader  current count : " + currentCount);
        return invokeDelegateMethod();
    }

    @Override
    public  void open(ExecutionContext executionContext)
            throws ItemStreamException {
        currentCount = executionContext.getLong(CONTEXT_COUNT_KEY, 0);
        log.info("Open Stream current count : " + currentCount);

    }


    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        executionContext.putLong(CONTEXT_COUNT_KEY, currentCount);
        log.info("Update Stream current count : " + currentCount);
    }


    @Override
    public void close() throws ItemStreamException {
        // TODO Auto-generated method stub

    }

}