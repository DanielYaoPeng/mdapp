package com.example.mdapp.repositories;

import com.example.mdapp.entity.Apk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class ApkRepositories {
    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    public Apk get(String apkId) {

        return mongoTemplate.findOne(query(where("apkid").is(apkId)), Apk.class);
    }

    public List<Apk> gets(List<String> ids) {
        Query query = Query.query(where("apkid").in(ids));
        List<Apk> result = mongoTemplate.find(query, Apk.class);
        return result;
    }

    public List<Apk> getAppEntityForAppSectionIds(List<String> appSectionIds) {
        Query query = Query.query(where("apps").in(appSectionIds));
        return mongoTemplate.find(query, Apk.class);
    }
}
