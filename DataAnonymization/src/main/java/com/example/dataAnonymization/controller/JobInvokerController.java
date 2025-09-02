package com.example.dataAnonymization.controller;

import com.example.dataAnonymization.service.JobExecutionService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class JobInvokerController {
    private final JobExecutionService jobExecutionService;

    @Autowired
    public JobInvokerController(JobExecutionService jobExecutionService) {
        this.jobExecutionService = jobExecutionService;
    }

    @PostMapping("/test")
    public String testEndpoint()
    {
        return "returning from controller";
    }
    @PostMapping("/runJobs")
    public String runAnonymizationJobs() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobInstanceAlreadyCompleteException, ExecutionException, InterruptedException
        {
                jobExecutionService.runAllJobsInSequentially();
        return "Anonymization jobs launched successfully!";
    }
}
