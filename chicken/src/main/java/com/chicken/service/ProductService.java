package com.chicken.service;

import com.chicken.dto.MemberInfoDto;
import com.chicken.dto.ProductDto;
import com.chicken.entity.MemberInfo;
import com.chicken.entity.Product;
import com.chicken.repository.MemberInfoRepository;
import com.chicken.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;


@RequiredArgsConstructor
@Transactional
@Service
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;

    private final MemberInfoRepository memberInfoRepository;

    public Page<Product> getProductList(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    //작성자도 같이 저장
    public Product  saveProduct(ProductDto productDto, String memberId) {
        MemberInfo memberInfo = memberInfoRepository.findByMemberId(memberId);
        productDto.setProductWriter(memberInfo.getMemberId());
        productDto.setProductFlag("0");
        return productRepository.save(Product.createProduct(productDto, memberInfo));
    }

    public ProductDto showDetail(Long productNo) {
        Product product = productRepository.findById(productNo).orElseThrow(EntityExistsException::new);
        return ProductDto.toEntity(product);
    }
}
