package com.example.mdapp.repositories;

import com.example.mdapp.entity.Apk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class ApkRepositories {
    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    public Apk get(String  apkId){

        return mongoTemplate.findOne(query(where("apkid").is(apkId)),Apk.class);
    }
}
