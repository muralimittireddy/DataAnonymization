package com.example.dataAnonymization.config;

import com.example.dataAnonymization.dto.Report_Dto;
import com.example.dataAnonymization.reader.AbcReader;
import com.example.dataAnonymization.reader.DefReader;
import com.example.dataAnonymization.writer.DefWriter;
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
import org.springframework.core.task.TaskExecutor;
import javax.sql.DataSource;

@Configuration
public class DefJobConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JobRepository jobRepository;


    @Autowired
    private TaskExecutor taskExecutor;

    @Bean(name="defJob")
    public Job defDataAnonymizationJob(Step defDataAnonymizationStep)
    {
        return new JobBuilder("defJob",jobRepository)
                .start(defDataAnonymizationStep)
                .build();
    }

    @Bean
    public Step defDataAnonymizationStep(DefReader defReader, DefWriter defWriter)
    {
        return new StepBuilder("defDataAnonymizationStep",jobRepository)
                .<Report_Dto,Report_Dto>chunk(100,transactionManager)
                .reader(defReader)
                .writer(defWriter)
                .taskExecutor(taskExecutor)   // 🔑 add TaskExecutor here
                .throttleLimit(5)             // max concurrent threads
                .build();
    }
}
