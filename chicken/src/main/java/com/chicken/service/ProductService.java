package com.chicken.service;

import com.chicken.dto.ImageUploadDto;
import com.chicken.dto.MemberInfoDto;
import com.chicken.dto.ProductDto;
import com.chicken.dto.ProductResponseDto;
import com.chicken.entity.Board;
import com.chicken.entity.ImageFile;
import com.chicken.entity.MemberInfo;
import com.chicken.entity.Product;
import com.chicken.repository.ImageFileRepository;
import com.chicken.repository.MemberInfoRepository;
import com.chicken.repository.ProductRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Log4j2
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    private final MemberInfoRepository memberInfoRepository;

    private final ImageFileRepository imageFileRepository;

    @Value("{file.path}")
    private String uploadFolder;


    public Page<ProductResponseDto> getProductList(Pageable pageable){

        Page<Product> prodouctPage = productRepository.findAllByProductFlagEquals("0", pageable);
        List<ProductResponseDto> dtoList = prodouctPage.stream().map(ProductResponseDto::toProductImageDto)
            .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, prodouctPage.getTotalElements());
    }


    public void  saveProduct(ProductResponseDto productDto, String memberId) throws IOException{

        MemberInfo memberInfo = memberInfoRepository.findByMemberId(memberId);

        // 이미지 추가 // 23-11-21
        if (productDto.getProductImage().isEmpty()) {
            //첨부파일 없음

            log.info("확인 부분 3" + productDto);

            // 상품 작성자 및 플래그 설정
            productDto.setProductWriter(memberInfo.getMemberId());
            productDto.setProductFlag("0");
            productDto.setFileAttached(0);
            productRepository.save(Product.createProduct(productDto, memberInfo));

        } else {


            //첨부 파일 있음
            productDto.setFileAttached(1);
            productDto.setProductWriter(memberInfo.getMemberId());
            productDto.setProductFlag("0");
             /**
              *  1. Dto에 담긴 파일 꺼냄
              *  2. 파일의 이름을 가져옴
              *  3. 서버 저장용 이름을 만듦
              *  4. 저장 경로 설정;
              *  5. 해당 경로에 파일 저장
              *  6. 상품 테이블에 해당 데이터 save 처리
              *  7. 상품 사진 데이터 save 처리
              * */

            log.info("확인 부분  2      " + productDto);

            MultipartFile productImage = productDto.getProductImage();  // 1
            String originalFileName = productImage.getOriginalFilename(); // 2
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
//            String savePath = "C:/temp/" + storedFileName;
            String savePath = "D:/temp/" + storedFileName;  // 집
            productImage.transferTo(new File(savePath));     // 5   --- 파일 저장 까지 완료


            Product product = Product.toSaveFileEntity(productDto, memberInfo);
            Long saveNo = productRepository.save(product).getProductNo();      // 저장하고 기본키를 찾음
            Product productParent = productRepository.findById(saveNo).get();  // 부모를 다시 자식을 바탕으로 가져옴

            log.info("확인 부분 1        " + productDto);

            ImageFile imageFile = ImageFile.toProductImageEntity(productParent, originalFileName, storedFileName); // 이미지파일 객체로 변환하는 과정

            imageFileRepository.save(imageFile);
        }
    }


    /** 상품 조회 **/
    public ProductResponseDto showDetail(Long productNo) {
        Product product = productRepository.findById(productNo).orElseThrow(EntityExistsException::new);
        return ProductResponseDto.toProductImageDto(product);
    }

    /** 상품 업데이트 **/
    public void updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getProductNo())
                .orElseThrow(EntityExistsException::new);
        product.updateProduct(productDto);
    }



    /**상품 삭제
     * **/
    public void deleteProduct(Long productNo){
        productRepository.deleteById(productNo);
    }

    /**
     * 상품 플래그 변경 해서 db에 남기는 코드
     * **/
    /*
    public ProductResponseDto deleteProduct(Long productNo) {
        Product product = productRepository.findById(productNo).orElseThrow(EntityExistsException::new);

        product.setProductFlag("1");

        return ProductResponseDto.toProductImageDto(product);
    }*/
}
