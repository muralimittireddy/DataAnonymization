package com.example.dataAnonymization.config;

import com.example.dataAnonymization.dto.Report_Dto;
import com.example.dataAnonymization.reader.AbcReader;
import com.example.dataAnonymization.reader.XyzReader;
import com.example.dataAnonymization.writer.XyzWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class XyzJobConfig {


    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private XyzReader xyzReader;



    @Autowired
    private XyzWriter xyzWriter;

    @Autowired
    private TaskExecutor taskExecutor;

    @Bean(name="xyzJob")
    public Job xyzDataAnonymizationJob(Step xyzDataAnonymizationStep)
    {
        return new JobBuilder("xyzJob",jobRepository)
                .start(xyzDataAnonymizationStep)
                .build();
    }

    @Bean
    public Step xyzDataAnonymizationStep(XyzReader xyzReader, XyzWriter xyzWriter)
    {
        return new StepBuilder("xyzDataAnonymizationStep",jobRepository)
                .<Report_Dto,Report_Dto>chunk(100,transactionManager)
                .reader(xyzReader)
                .writer(xyzWriter)
                .taskExecutor(taskExecutor)   // 🔑 add TaskExecutor here
                .throttleLimit(5)             // max concurrent threads
                .build();
    }

}
