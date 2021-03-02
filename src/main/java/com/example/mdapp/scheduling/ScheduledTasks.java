package com.example.mdapp.scheduling;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

    @Scheduled(cron = "0/2 * * * * ?")
    @Async
    public void first() throws InterruptedException {
        System.out.println("定时任务开始 : " + sdf.format(new Date()) + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
        Thread.sleep(1000 * 10); //模拟第一个任务执行的时间
    }

}
