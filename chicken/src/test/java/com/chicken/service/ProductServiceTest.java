package com.chicken.service;

import com.chicken.dto.ProductResponseDto;
import com.chicken.entity.MemberInfo;
import com.chicken.entity.Product;
import com.chicken.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    void 제품생성() {


        for(int i=1; i<=100; i++){
            Product product = new Product();
            product.setProductCalories(10L+i);
            product.setProductFlag("0");
            product.setProductName("제품이름" + i);
            product.setProductProtein(15L+i);
            product.setRegTime(LocalDateTime.now());
            product.setProductWriter("151515");
            product.setMemberInfo(product.getMemberInfo());

            productRepository.save(product);
        }
    }
}