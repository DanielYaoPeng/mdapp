package com.example.mdapp;

import com.example.mdapp.kafka.CustomerListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
//@EnableKafka
public class MdappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdappApplication.class, args);
       // SpringApplication.run(CustomerListener.class,args);
    }

}
