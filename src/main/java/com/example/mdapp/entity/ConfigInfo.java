package com.example.mdapp.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component("configInfo")
public class ConfigInfo {
    @Value("${md.MQConsumerThread.count}")
    private String threadCount;
}
