package com.chicken.controller;

import com.chicken.dto.ImageUploadDto;
import com.chicken.dto.ProductDto;
import com.chicken.service.ImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageFileService imageFileService;

    /**
     * 프로필 사진 등록
     * @param imageUploadDTO 사진 정보
     * @param authentication 유저 정보
     * @return 프로필 페이지
     */

    @PostMapping("/upload")
    public String upload(@ModelAttribute ImageUploadDto imageUploadDTO, ProductDto productDto){
        imageFileService.upload(imageUploadDTO, productDto.getProductName());
        return "product/chickenInfo";
    }

}
