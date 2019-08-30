package com.gl.es.service;

import com.gl.es.model.Product;
import com.gl.es.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ProductRepository productRepository;


    public Product save(Product product){
        productRepository.save(product);
        return product;
    }

}
