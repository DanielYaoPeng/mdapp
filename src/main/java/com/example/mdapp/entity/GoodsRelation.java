package com.example.mdapp.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Data
@Document(collection = "goodsrelation")
public class GoodsRelation {

    @Id
    private String id;

    private String pid;

    private String versionId;

    private String goodsId;

    private Boolean isDistribute;

    private Mapping apkId;

    private List<Mapping> apkIds;

    private List<Mapping> workSheetIds;

    private List<Mapping> roleIds;

    private List<Mapping> workFlowIds;

    private List<Mapping> reportIds;

    private String caid;

    private String uaid;

    private Date ctime;

    private Date utime;


    public Map<String,String> listToMap(List<Mapping> mappings){
        Map<String,String> hashMap = new HashMap();
        if (CollectionUtils.isEmpty(mappings)) {
            return hashMap;
        }
        for (Mapping mapping : mappings){
            hashMap.put(mapping.getOriginalId(),mapping.getNewId());
        }
        return hashMap;

    }

    public List<Mapping> mapToList(Map<String,String> map){
        List<Mapping> mappings = new ArrayList<>();
        for (String string : map.keySet()){
            mappings.add(new Mapping(string,map.get(string)));
        }
        return mappings;

    }


}

