package com.example.mdapp.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerListener {

    /**
     * 消费处需要指定  containerFactory ，这个在kafkaConfig里配置的，可以有多个 。groupId也是必须参数
     * @param message
     */
    @KafkaListener(topics="topic.testRecord",groupId = "kafka-group-ryj",containerFactory = "containerFactory")
    public void onMessage(String message){
        System.out.println("kafka接收到消息"+message);
        System.out.println("收到了");
    }
}
