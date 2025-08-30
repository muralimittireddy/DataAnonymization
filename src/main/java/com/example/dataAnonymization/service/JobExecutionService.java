package com.example.dataAnonymization.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class JobExecutionService {
    @Autowired
    private  JobLauncher jobLauncher;
    @Autowired
    private  ExecutorService executorService;
    @Autowired
    private  Job abcJob;
    @Autowired
    private  Job defJob;
    @Autowired
    private  Job xyzJob;


    public void runAllJobsInParallel() {
        // Use CompletableFuture to run each job on a separate thread from the executor service
        CompletableFuture<JobExecution> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                // Add a unique timestamp to job parameters to ensure each run is a new instance
                return jobLauncher.run(xyzJob, new JobParametersBuilder()
                        .addLong("run.id", System.currentTimeMillis())
                        .toJobParameters());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executorService);

        CompletableFuture<JobExecution> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                return jobLauncher.run(abcJob, new JobParametersBuilder()
                        .addLong("run.id", System.currentTimeMillis() + 1)
                        .toJobParameters());
            } catch (Exception e) {
//                log.error("Job 2 failed to launch.", e);
                throw new RuntimeException(e);
            }
        }, executorService);

        CompletableFuture<JobExecution> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                return jobLauncher.run(defJob, new JobParametersBuilder()
                        .addLong("run.id", System.currentTimeMillis() + 2)
                        .toJobParameters());
            } catch (Exception e) {
//                log.error("Job 3 failed to launch.", e);
                throw new RuntimeException(e);
            }
        }, executorService);

        // Wait for all jobs to complete (optional, for monitoring purposes)
        CompletableFuture.allOf(future1, future2, future3).join();
    }
}
