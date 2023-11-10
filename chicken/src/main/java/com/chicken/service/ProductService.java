package com.chicken.service;

import com.chicken.dto.MemberInfoDto;
import com.chicken.dto.ProductDto;
import com.chicken.entity.Board;
import com.chicken.entity.ImageFile;
import com.chicken.entity.MemberInfo;
import com.chicken.entity.Product;
import com.chicken.repository.ImageFileRepository;
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

    private final ImageFileRepository imageFileRepository;

    public Page<Product> getProductList(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    //작성자도 같이 저장
    public Product  saveProduct(ProductDto productDto, String memberId) {
        MemberInfo memberInfo = memberInfoRepository.findByMemberId(memberId);
        productDto.setProductWriter(memberInfo.getMemberId());
        productDto.setProductFlag("0");

        ImageFile imageFile = ImageFile.builder()
                .imageFileUrl("/img/chickenBreast.png")
                .build();
        //상품 사진 닭가슴살 사진으로 저장
        imageFileRepository.save(imageFile);

        return productRepository.save(Product.createProduct(productDto, memberInfo, imageFile));
    }

    public ProductDto showDetail(Long productNo) {
        Product product = productRepository.findById(productNo).orElseThrow(EntityExistsException::new);
        return ProductDto.toDto(product);
    }

    public void updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getProductNo())
                .orElseThrow(EntityExistsException::new);
        product.updateProduct(productDto);
    }
}
