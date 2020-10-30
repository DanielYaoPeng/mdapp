package com.example.mdapp.repositories;

import com.example.mdapp.entity.Apk;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppRepository extends MongoRepository<Apk, Long> {

}