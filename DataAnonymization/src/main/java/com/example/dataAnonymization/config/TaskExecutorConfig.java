package com.example.dataAnonymization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class TaskExecutorConfig {

    /**
     * Creates a new ExecutorService bean for parallel job execution.
     * The core pool size is 3 to match the number of jobs run in parallel.
     * It uses a queue with a size of 10 for incoming tasks.
     *
     * @return The configured ExecutorService bean.
     */
    @Bean
    public ExecutorService executorService() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3); // Number of parallel jobs
        executor.setMaxPoolSize(5); // Maximum number of threads
        executor.setQueueCapacity(10); // Queue for tasks awaiting execution
        executor.setThreadNamePrefix("job-executor-thread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor.getThreadPoolExecutor();
    }

    /**
     * TaskExecutor for multi-threading inside Spring Batch steps.
     * Can be injected into steps using .taskExecutor(taskExecutor()).
     */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  // threads used inside a step
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("step-thread-");
        executor.initialize();
        return executor;
    }
}
