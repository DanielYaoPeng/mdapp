package com.example.mdapp.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "appentity")
public class AppEntity {

    public String _t;
    public String id ;

    public String apName ;

    public String shortDescription ;

    public String description ;

    public String icon ;

    public String iconc ;

    public Boolean enabled ;

    public Boolean isDel ;

    public Integer atype ;

    public List<BaseAppConfigItem> cfgs ;

    public String caid ;

    public String uaid ;

    public Date ctime;

    public Date utime ;
}

