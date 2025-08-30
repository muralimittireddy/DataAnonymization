package com.example.dataAnonymization.config;

import com.example.dataAnonymization.dto.Report_Dto;
import com.example.dataAnonymization.reader.AbcReader;
import com.example.dataAnonymization.writer.AbcWriter;
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
public class AbcJobConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private AbcReader abcReader;



    @Autowired
    private AbcWriter abcWriter;

    @Bean
    @StepScope
    public AbcReader abcReader(DataSource dataSource) throws Exception {
        return new AbcReader(dataSource);
    }

    @Bean(name="abcJob")
    public Job abcDataAnonymizationJob()
    {
        return new JobBuilder("abcJob",jobRepository)
                .start(abcDataAnonymizationStep())
                .build();
    }

    @Bean
    public Step abcDataAnonymizationStep()
    {
        return new StepBuilder("abcDataAnonymizationStep",jobRepository)
                .<Report_Dto,Report_Dto>chunk(100,transactionManager)
                .reader(abcReader)
                .writer(abcWriter)
                .build();
    }

}
