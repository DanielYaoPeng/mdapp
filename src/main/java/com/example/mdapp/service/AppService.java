package com.example.mdapp.service;

import java.util.ArrayList;
import java.util.List;

import com.example.mdapp.entity.Apk;
import com.example.mdapp.entity.ApkStatusEnum;
import com.example.mdapp.repositories.AppRepository;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Query.query;

public class AppService {

    @Autowired
    private AppRepository _appRepository ;

    public List<Apk> Get(String id){
        List<String> ids=new ArrayList<String>();
        ids.add(id);
        Query query = query(where("status").is(ApkStatusEnum.Enable.getValue()).and("apkid").in(ids));
        var result= _appRepository.findAll();
        return result;
    }
    
}
