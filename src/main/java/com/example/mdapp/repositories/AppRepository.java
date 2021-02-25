package com.example.mdapp.repositories;

import com.example.mdapp.entity.Apk;

import com.example.mdapp.entity.AppEntity;
import com.example.mdapp.entity.AppType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class AppRepository {
  @Autowired
  @Qualifier("mongoTemplate")
  private MongoTemplate mongoTemplate;

  public AppEntity get(String id) {

    return mongoTemplate.findOne(query(where("id").is(id)), AppEntity.class);
  }

  public List<AppEntity> GetBySheetId(List<String> sheetIds) {
    List<AppEntity> result = new ArrayList<>();
    var criteria = Criteria.where("cfgs").elemMatch(Criteria.where("wsid").in(sheetIds)).and("atype")
        .is(AppType.Worksheet.getValue());
    Query query = query(criteria);
    result = mongoTemplate.find(query, AppEntity.class);
    // Criteria.where("products").elemMatch(Criteria.where("status").is(1).and("productId").is(channelId))
    // db.tableName.find({"products":{"$elemMatch":{"productId":"4fa7367089cc56e4083cb7a8","status":1}}})
    return result;
  }
}