package com.chicken.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ImageUploadDto {

    private String productWriter;

    private String productName;

    private String product;

//    private List<MultipartFile> files;

    private MultipartFile files;

    private Long productCalories;             //  칼로리

    private Long productSodium;               // 나트륨

    private Long productCarbohydrate;         // 탄수화물

    private Long productSugar;                // 당

    private Long productCholesterol;          // 콜레스테롤

    private Long productFat;                  // 지방

    private Long productProtein;              // 단백질

    private String productFlag ;


}
