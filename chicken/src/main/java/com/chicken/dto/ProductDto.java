package com.chicken.dto;

import com.chicken.entity.Product;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductDto {


    private Long productNo;                     // 제품 고유 번호
    
    private String productWriter;               // 제품 등록자

    @NotBlank(message = "제품명 필수 입력")
    private String productName;                 // 제품 이름

    @NotNull(message = "칼로리 필수 입력")
    private Long productCalories;             //  칼로리

    private Long productSodium;               // 나트륨

    private Long productCarbohydrate;         // 탄수화물

    private Long productSugar;                // 당

    private Long productCholesterol;          // 콜레스테롤

    private Long productFat;                  // 지방

    private Long productProtein;              // 단백질

    private String productFlag ;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductDto toEntity(Product product){
        return modelMapper.map(product, ProductDto.class);
    }
}
