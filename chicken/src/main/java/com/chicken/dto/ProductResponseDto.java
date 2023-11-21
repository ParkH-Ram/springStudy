package com.chicken.dto;

import com.chicken.entity.ImageFile;
import com.chicken.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  상품 이미지 용
 * */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductResponseDto {

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

    @NotNull(message = "단백질 필수 입력")
    private Long productProtein;              // 단백질

    private String productFlag ;

    private MultipartFile productImage; // html -> controller 넘어갈 때 파일을 담는 용도
    private String originalFileName;    // 원본 파일
    private String storedFileName;      // 서버 저장용 파일
    private int fileAttached;           // 파일 첨부 여부 (첨부1, 미첨부0)


    public static ProductResponseDto toProductImageDto(Product product){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductNo(product.getProductNo());
        productResponseDto.setProductWriter(product.getProductWriter());
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setProductCalories(product.getProductCalories());
        productResponseDto.setProductSodium(product.getProductSodium());
        productResponseDto.setProductCarbohydrate(product.getProductCarbohydrate());
        productResponseDto.setProductSugar(product.getProductSugar());
        productResponseDto.setProductCholesterol(product.getProductCholesterol());
        productResponseDto.setProductFat(product.getProductFat());
        productResponseDto.setProductProtein(product.getProductProtein());
        productResponseDto.setProductFlag(product.getProductFlag());

        // 파일이 없는 경우
        if(product.getFileAttached() == 0){
            productResponseDto.setFileAttached(product.getFileAttached());
        } else {
            productResponseDto.setFileAttached(product.getFileAttached());
            // 파일 이름을 가져가야 함
            // originalFileName, storedFileName  : image_file  (entity -> imageFile)
            productResponseDto.setOriginalFileName(product.getImageFile().get(0).getOriginalFileName());
            productResponseDto.setStoredFileName(product.getImageFile().get(0).getStoredFileName());

        }

        return productResponseDto;
    }

}
