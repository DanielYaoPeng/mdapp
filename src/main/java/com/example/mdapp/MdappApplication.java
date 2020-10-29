package com.example.mdapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication()
public class MdappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdappApplication.class, args);
    }

}
