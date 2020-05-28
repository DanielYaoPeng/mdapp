package com.example.mdapp.controller;

import com.example.mdapp.entity.GoodsRelation;
import com.example.mdapp.service.GoodsRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodsController {


    @Autowired
    private GoodsRelationService goodsRelationService;

    @RequestMapping("goods/getRelation")
    @ResponseBody
    public GoodsRelation getRelation(String id){
        if(id.isEmpty()){
           return null;
        }
    return goodsRelationService.getGoodsRelation((id));
    }
}
