package com.example.mdapp.scheduling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ScheduleConfig {

    @Value("${md.MQConsumerThread.count}")
    private Integer threadCount ;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        System.out.println("定时job启动"+threadCount+"个线程");
        scheduler.setPoolSize(threadCount);//定义线程池数量
        return scheduler;
    }
}
