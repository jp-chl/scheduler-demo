package com.jadm.scheduler.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Esta configuracion sirve para crear un pool de threads
 * en vez de usar el por defecto.
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

    /**
     * Cantidad de threads que se ejecutan en paralelo
     */
    private final int POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("jadm-scheduler-pool-");
        threadPoolTaskScheduler.initialize();

        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    } // fin void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar)
} // fin class SchedulerConfig
