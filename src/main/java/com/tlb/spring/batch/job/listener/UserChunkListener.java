package com.tlb.spring.batch.job.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class UserChunkListener implements ChunkListener {
    @Override
    public void beforeChunk(ChunkContext chunkContext) {

        System.out.println(" begin chunk: "+ Thread.currentThread().getName());

    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" end chunk : " + Thread.currentThread().getName());
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        System.out.println("  chunk error ");
    }
}
