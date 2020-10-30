package com.example.mdapp.service;

import java.util.ArrayList;
import java.util.List;

import com.example.mdapp.entity.Apk;

import com.example.mdapp.repositories.AppRepository;

import org.springframework.beans.factory.annotation.Autowired;


public class AppService {

    @Autowired
    private AppRepository _appRepository ;

    public List<Apk> Get(String id){
        List<String> ids=new ArrayList<String>();
        ids.add(id);
       
        var result= _appRepository.findAll();
        return result;
    }
    
}
