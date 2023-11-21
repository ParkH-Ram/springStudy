package com.chicken.service;

import com.chicken.dto.ImageResponseDto;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageFileService {

    private final ImageFileRepository imageFileRepository;
    private final ProductRepository productRepository;

    @Value("{file.path}")
    private String uploadFolder;

    //사진 찾기
    public ImageResponseDto findImage(Long productNo){
        Optional<Product> product = productRepository.findById(productNo);

        ImageFile imageFile = imageFileRepository.findByProduct(product.get());

        // 디폴트 이미지 삽입
        String defaultImageUrl = "/img/chickenBreast.png";

        if(imageFile == null){
            return ImageResponseDto.builder()
                    .imageFileUrl(defaultImageUrl)
                    .build();
        } else{
            return ImageResponseDto.builder()
                    .imageFileUrl(imageFile.getStoredFileName())
                    .build();
        }
    }
}
