package com.example.mdapp.repositories;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
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
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoTemplate mongoTemplate = this.getMongoTemplate(mongoClientURI);
        return mongoTemplate;
    }

    @Bean(name = "mdMapMongoTemplate")
    MongoTemplate mdMapMongoTemplate() throws Exception {
        MongoClientURI uri = new MongoClientURI(mdMapUri);
        MongoTemplate mdMongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(uri));
        return mdMongoTemplate;
    }

    /**
     * 删除"_class"
     *
     * @param mongoClientURI
     * @return
     * @throws Exception
     */
    private MongoTemplate getMongoTemplate(MongoClientURI mongoClientURI) throws Exception {
        MongoDbFactory factory = new SimpleMongoDbFactory(mongoClientURI);
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoTemplate mongoTemplate = new MongoTemplate(factory, converter);
        return mongoTemplate;
    }
}
