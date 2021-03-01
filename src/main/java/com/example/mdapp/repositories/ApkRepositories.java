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
    @Qualifier("mongoTemplate") //@Qualifier 如果接口或者抽象类有多个实现类，Qualifier表示显示指定某个一实现类来实现接口 ，此属性必须配合Autowired使用
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
