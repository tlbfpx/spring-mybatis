package com.tlb.spring.batch.job.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class UserChunkListener implements ChunkListener {
    @Override
    public void beforeChunk(ChunkContext chunkContext) {

        System.out.println(" begin chunk");

    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        System.out.println(" end chunk");
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        System.out.println("  chunk error ");
    }
}
