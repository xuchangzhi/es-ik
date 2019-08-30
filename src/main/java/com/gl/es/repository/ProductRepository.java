package com.gl.es.repository;

import com.gl.es.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends ElasticsearchRepository<Product,String> {
    List<Product> findByName(String name);

    Page<Product> findByName(String name, Pageable pageable);

}