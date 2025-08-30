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
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class DefJobConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DefReader defReader;


    @Autowired
    private DefWriter defWriter;

    @Bean
    @StepScope
    public DefReader defReader(DataSource dataSource) throws Exception {
        return new DefReader(dataSource);
    }

    @Bean(name="defJob")
    public Job abcDataAnonymizationJob()
    {
        return new JobBuilder("defJob",jobRepository)
                .start(defDataAnonymizationStep())
                .build();
    }

    @Bean
    public Step defDataAnonymizationStep()
    {
        return new StepBuilder("defDataAnonymizationStep",jobRepository)
                .<Report_Dto,Report_Dto>chunk(100,transactionManager)
                .reader(defReader)
                .writer(defWriter)
                .build();
    }
}
