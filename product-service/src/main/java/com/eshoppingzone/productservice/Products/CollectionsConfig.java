package com.eshoppingzone.productservice.Products;

import com.eshoppingzone.productservice.Products.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.index.TextIndexDefinition;

import javax.annotation.PostConstruct;

@Configuration
@DependsOn("mongoTemplate")
public class CollectionsConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndexes() {
        mongoTemplate.indexOps(Product.class) // collection name string or .class
                .ensureIndex(
                        new TextIndexDefinition.TextIndexDefinitionBuilder()
                                .onField("title", (float) 3).build()
                );
    }
}
