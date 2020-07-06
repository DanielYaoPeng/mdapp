package com.example.mdapp.service;



import com.example.mdapp.entity.GoodsRelation;
import com.example.mdapp.entity.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class GoodsRelationService {

    @Autowired
    @Qualifier("mdMapMongoTemplate")
    private MongoTemplate mdMapMongoTemplate;

    /**
     * 根据relationId查询应用包复制的相关信息
     *
     * @param goodsRelationId
     * @return
     */
    public GoodsRelation getGoodsRelation(String goodsRelationId) {
        var list= mdMapMongoTemplate.findById(goodsRelationId, GoodsRelation.class);
        return list;
    }

    /**
     * 更新应用包复制中流程的复制对应关系
     *
     * @param relationId
     * @param workflowIds
     */
    public void updateGoodsRelationWorkflowIds(String relationId, List<Mapping> workflowIds) {

        Update update = new Update()
                .addToSet("workFlowIds").each(workflowIds)
                .set("utime", new Date());

        mdMapMongoTemplate.updateMulti(query(where("id").is(relationId)), update, GoodsRelation.class);
    }


    /**
     * 修改单个流程对应关系
     *
     * @param relationId
     * @param originalId
     * @param newId
     */
    public void updateGoodsRelationWorkflowId(String relationId, String originalId, String newId) {

        Criteria c = where("id").is(relationId).and("workFlowIds.originalId").is(originalId);
        Query query = query(c);
        Update update = Update.update("workFlowIds.$", new Mapping(originalId, newId));
        mdMapMongoTemplate.updateFirst(query, update, GoodsRelation.class);
    }
}

