package com.chicken.dto;

import com.chicken.entity.ImageFile;
import com.chicken.entity.Product;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  상품 이미지 용
 * */

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
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

    // 시간
    private LocalDateTime regTime; // 등록시간
    private LocalDateTime updateTime; // 수정시간


    public static ProductResponseDto toProductImageDto(Product product){
        // 파일이 없는 경우
        if(product.getFileAttached() == 0){
            return ProductResponseDto.builder()
                .productNo(product.getProductNo())
                .productWriter(product.getProductWriter())
                .productName(product.getProductName())
                .productCalories(product.getProductCalories())
                .productSodium(product.getProductSodium())
                .productCarbohydrate(product.getProductCarbohydrate())
                .productSugar(product.getProductSugar())
                .productCholesterol(product.getProductCholesterol())
                .productFat(product.getProductFat())
                .productProtein(product.getProductProtein())
                .productFlag(product.getProductFlag())
                .fileAttached(product.getFileAttached())
                .regTime(product.getRegTime())
                .updateTime(product.getUpdateTime())
                .build();
        } else {
            // 파일 이름을 가져가야 함
            // originalFileName, storedFileName  : image_file 엔티티
            return ProductResponseDto.builder()
                .productNo(product.getProductNo())
                .productWriter(product.getProductWriter())
                .productName(product.getProductName())
                .productCalories(product.getProductCalories())
                .productSodium(product.getProductSodium())
                .productCarbohydrate(product.getProductCarbohydrate())
                .productSugar(product.getProductSugar())
                .productCholesterol(product.getProductCholesterol())
                .productFat(product.getProductFat())
                .productProtein(product.getProductProtein())
                .productFlag(product.getProductFlag())
                .fileAttached(product.getFileAttached())
                .originalFileName(product.getImageFile().get(0).getOriginalFileName())  // 이미지와 사용자가 올렸을 때 이름을 가져올 때 사용.  실제 사용은 안함
                .storedFileName(product.getImageFile().get(0).getStoredFileName())   //파일을 여러개 돌리면 for문으로 돌려야 하지만 이미지를 하나만 출력하므로 0번 인덱스 호출
                .regTime(product.getRegTime())
                .updateTime(product.getUpdateTime())
                .build();
        }
    }
}


