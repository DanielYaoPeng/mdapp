package com.example.mdapp.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * MongoDb配置（@Bean同XML中Bean）
 */
@Configuration
 class MongoDbConfig {
    @Value("${spring.data.mongodb.mdapp-uri}")
    private String uri;

    @Value("${spring.data.mongodb.mdmap-uri}")
    private String mdMapUri;

    @Bean(name = "mongoTemplate")
    MongoTemplate mongoTemplate() throws Exception {

      // MongoTemplate mongoTemplate = new MongoTemplate(new SimpleMongoClientDatabaseFactory(uri));
        MongoTemplate mongoTemplate = this.getMongoTemplate(uri);
        return mongoTemplate;
    }

    @Bean(name = "mdMapMongoTemplate")  //Bean   与@Configuration、@Component配合使用(配置类，组件类)， 指示组件里得方法或者属性，可自定义名称，自动装配
    MongoTemplate mdMapMongoTemplate() throws Exception {
        MongoTemplate mdMongoTemplate =  new MongoTemplate(new SimpleMongoClientDatabaseFactory(mdMapUri));
        return mdMongoTemplate;
    }

    /**
     * 删除"_class"
     *
     * @param uri
     * @return
     * @throws Exception
     */
    private MongoTemplate getMongoTemplate(String uri) throws Exception {
        SimpleMongoClientDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(uri);
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoTemplate mongoTemplate = new MongoTemplate(factory, converter);
        return mongoTemplate;
    }

//    /**
//     * 删除"_class"
//     *
//     * @param mongoClientURI
//     * @return
//     * @throws Exception
//     */
//
//    private MongoTemplate getMongoTemplate(MongoClientURI mongoClientURI) throws Exception {
//        MongoDbFactory factory = new SimpleMongoDbFactory(mongoClientURI);
//        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
//        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//        MongoTemplate mongoTemplate = new MongoTemplate(factory, converter);
//        return mongoTemplate;
//    }
}
