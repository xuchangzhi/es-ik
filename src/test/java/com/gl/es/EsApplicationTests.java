package com.gl.es;

import com.gl.es.model.Product;
import com.gl.es.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {
    @Autowired
    private ProductService productService;
    @Test
    public void contextLoads() {

        Product product = new Product();
        product.setYear(2001);
        product.setLength(10000l);
        product.setName("桂林山水");
        product.setContent("桂林山水");
        productService.save(product);

        System.out.println(product.getId());
    }

}
