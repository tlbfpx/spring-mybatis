package com.tlb.spring.batch.job;


import com.tlb.spring.batch.job.adapter.DemoAdapter;
import com.tlb.spring.batch.job.adapter.MyItemReaderAdapter;
import com.tlb.spring.batch.job.listener.*;
import com.tlb.spring.batch.job.process.UserItemProcess;
import com.tlb.spring.batch.job.reader.UserItemReader;
import com.tlb.spring.batch.job.writer.UserItemWriter;
import com.tlb.spring.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
//@EnableBatchProcessing
public class UserBatchJob {

    private final String USER_QUERY_ALL = "selectAllUser";
    private final String USER_INSERT = "insertSelective";
    private final String USER_PARTITION = "selectPartitionUser";

    @Autowired
    @Qualifier("sqlSessionFactoryFrom")
    SqlSessionFactory sqlSessionFactoryFrom;

    @Autowired
    @Qualifier("sqlSessionFactoryTo")
    SqlSessionFactory sqlSessionFactoryTo;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    @Autowired
    DemoAdapter demoAdapter;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

//    @Bean
//    public ItemReader<User> userItemReader() {
//        UserItemReader reader = new UserItemReader();
//        reader.setSqlSessionFactory(sqlSessionFactoryFrom);
//        reader.setQueryId(USER_QUERY_ALL);
//        return reader;
//    }


    @Bean
    @StepScope
    public MyItemReaderAdapter<User> myItemReaderAdapter() {
        MyItemReaderAdapter<User> adapter = new MyItemReaderAdapter<User>();
        adapter.setTargetObject(demoAdapter);
        adapter.setTargetMethod("next");
        return adapter;
    }



    @Bean
    @StepScope
    public ItemProcessor userItemProcess() {
        return new UserItemProcess();
    }

    @Bean
    public ItemWriter<User> userItemWriter() {
        UserItemWriter writer = new UserItemWriter();
        writer.setSqlSessionFactory(sqlSessionFactoryTo);
        writer.setStatementId(USER_INSERT);
        return writer;
    }

    @Bean
    public ChunkListener userChunkListener() {
        return new UserChunkListener();
    }

    @Bean
    public ItemReadListener userItemReadListener() {
        return new UserItemReadListener();
    }

    @Bean
    public ItemProcessListener userItemProcessListener() {
        return new UserItemProcessListener();
    }

    @Bean
    public ItemWriteListener userItemWriteListener() {
        return new UserItemWriteListener();
    }

    @Bean
    public JobExecutionListener userJobExecutionListener() {
        return new UserJobExecutionListener();
    }

    @Bean
    public StepExecutionListener userStepExecutionListener() {
        return new UserStepExecutionListener();
    }


    @Bean

    public ThreadPoolTaskExecutor poolTaskExecutor(){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(10);
        poolTaskExecutor.setMaxPoolSize(30);
        return poolTaskExecutor;
    }

    @Bean
    public Job importJob(Step userStep) {
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                .start(userStep)
                .listener(userJobExecutionListener())
                .build();

    }

    @Bean
    public Step userStep(PlatformTransactionManager transactionManager) {
        return stepBuilderFactory.get("userStep")
                //.listener(userStepExecutionListener())
                .<User, User>chunk(2)
                //.reader(userItemReader())
                .reader(myItemReaderAdapter())
                .processor(userItemProcess())
                .writer(userItemWriter())
                //.listener(userItemWriteListener())
                //.listener(userItemProcessListener())
                //.listener(userItemReadListener())
                //.listener(userChunkListener())
                .taskExecutor(poolTaskExecutor())
                .throttleLimit(5)
                //.transactionManager(transactionManager)
                .build();
    }
}
