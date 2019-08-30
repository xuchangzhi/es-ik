package com.gl.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "book",type = "book" , shards = 1, replicas = 0,  refreshInterval = "-1",createIndex = true,indexStoreType = "niofs")
public class Product {
    @Id
    private String id;

    @Field(searchAnalyzer="ik_smart",store=true, type= FieldType.Keyword)
    private String name;

    private Long length;

    private Integer year;


    @Field(searchAnalyzer="ik_smart",analyzer="ik_max_word",store=true, type= FieldType.Text)
    private String content;



}