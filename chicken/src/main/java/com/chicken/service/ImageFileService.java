package com.chicken.service;

import com.chicken.dto.ImageUploadDto;
import com.chicken.entity.ImageFile;
import com.chicken.entity.Product;
import com.chicken.repository.ImageFileRepository;
import com.chicken.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageFileService {

    private final ImageFileRepository imageFileRepository;
    private final ProductRepository productRepository;

    @Value("{file.path}")
    private String uploadFolder;

    public void upload(ImageUploadDto imageUploadDTO, String productName) {
        Product product = productRepository.findByProductName(productName).orElseThrow(()->new UsernameNotFoundException("상품을 찾을 수 없습니다."));
        MultipartFile file = imageUploadDTO.getFiles();

        UUID uuid = UUID.randomUUID();
        String imageFileName =uuid + "_" + file.getOriginalFilename();

        File destinationFile =  new File(uploadFolder + imageFileName);   //서버에 저장될 파일 경로

        try {
            file.transferTo(destinationFile);

            ImageFile imageFile = imageFileRepository.findByProduct(product);

            if(imageFile != null){
                // 이미지가 이미 존재하면 url 업데이트3
                imageFile.updateUrl("/img/" + imageFileName );
            } else{
                // 이미지 없으면 객체 생성 후 업데이트
                imageFile = ImageFile.builder()
                    .product(product)
                    .imageFileUrl("/img/" + imageFileName )
                    .build();
            }
            imageFileRepository.save(imageFile);

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
