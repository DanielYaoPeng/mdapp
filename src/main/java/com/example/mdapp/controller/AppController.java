package com.example.mdapp.controller;

import com.example.mdapp.entity.Apk;
import com.example.mdapp.entity.GoodsRelation;
import com.example.mdapp.service.ApkService;
import com.example.mdapp.service.GoodsRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    @Autowired
    private ApkService apkService;

    @RequestMapping("app/get")
    @ResponseBody
    public Apk getById(String id){
        if(id.isEmpty()){
            return null;
        }
        return apkService.getById((id));
    }
}
