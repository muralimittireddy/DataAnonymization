package com.example.dataAnonymization.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class JobExecutionService {
    @Autowired
    private  JobLauncher jobLauncher;

    @Autowired
    private  Job abcJob;
    @Autowired
    private  Job defJob;
    @Autowired
    private  Job xyzJob;


    public void runAllJobsInSequentially() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        long startTime = System.currentTimeMillis(); // record start time

        jobLauncher.run(xyzJob, new JobParametersBuilder()
                        .addLong("run.id", System.currentTimeMillis())
                        .toJobParameters());


        jobLauncher.run(abcJob, new JobParametersBuilder()
                        .addLong("run.id", System.currentTimeMillis() + 1)
                        .toJobParameters());

        jobLauncher.run(defJob, new JobParametersBuilder()
                        .addLong("run.id", System.currentTimeMillis() + 2)
                        .toJobParameters());

        // Wait for all jobs to complete
        long endTime = System.currentTimeMillis(); // record end time
        System.out.println("All jobs completed in " + (endTime - startTime) + " ms");
    }
}
