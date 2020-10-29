package com.example.mdapp.service;

import com.example.mdapp.entity.Apk;
import com.example.mdapp.entity.ApkStatusEnum;
import com.example.mdapp.repositories.ApkRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ApkService {

    @Autowired
    private ApkRepositories apkRepositories;

    private  final ApkCacheService cache;

    public ApkService(ApkCacheService cache){
        this.cache=cache;
    }

    public Apk getById(String apkId){

       // var result=cache.get(apkId);
        //if(result!=null)
            //return result;


      var result=apkRepositories.get(apkId);
       if(result.getStatus()== ApkStatusEnum.IsDeleted.getValue())
           return null;
       // cache.set(apkId,result);
       return result;
    }
}
