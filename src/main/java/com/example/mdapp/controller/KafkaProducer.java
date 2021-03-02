package com.example.mdapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;
@RestController
public class KafkaProducer {
    @Autowired
    private KafkaTemplate template;

    @RequestMapping("/sendMsg")
    public String sendMsg(String message){
        template.send("topic.testRecord",message);
        return "success";
    }
}
