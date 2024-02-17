package com.chicken.entity;

import com.chicken.auditing.BaseEntity;
import com.chicken.dto.MemberInfoDto;
import com.chicken.dto.ProductDto;
import com.chicken.dto.ProductResponseDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Product  extends BaseEntity {

    @Id
    @Column(name = "product_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productNo;

    private String productWriter;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long productCalories;             //  칼로리

    private Long productSodium;               // 나트륨

    private Long productCarbohydrate;         // 탄수화물

    private Long productSugar;                // 당

    private Long productCholesterol;          // 콜레스테롤

    private Long productFat;                  // 지방

    private Long productProtein;              // 단백질

    private String productFlag ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private MemberInfo memberInfo;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImageFile> imageFile = new ArrayList<>();

    @Column
    private int fileAttached;  // 1 or 0  있으면 1 없으면 0


    @Builder
    public static Product createProduct(ProductResponseDto productDto, MemberInfo memberInfo){
        return Product.builder()
                .productWriter(productDto.getProductWriter())
                .productName(productDto.getProductName())
                .productCalories(productDto.getProductCalories())
                .productSodium(productDto.getProductSodium())
                .productCarbohydrate(productDto.getProductCarbohydrate())
                .productSugar(productDto.getProductSugar())
                .productCholesterol(productDto.getProductCholesterol())
                .productFat(productDto.getProductFat())
                .productProtein(productDto.getProductProtein())
                .memberInfo(memberInfo)
                .productFlag(productDto.getProductFlag())
                .fileAttached(productDto.getFileAttached())
                .build();
    }

    public static Product toSaveFileEntity(ProductResponseDto productDto, MemberInfo memberInfo) {
        return Product.builder()
                .productWriter(productDto.getProductWriter())
                .productName(productDto.getProductName())
                .productCalories(productDto.getProductCalories())
                .productSodium(productDto.getProductSodium())
                .productCarbohydrate(productDto.getProductCarbohydrate())
                .productSugar(productDto.getProductSugar())
                .productCholesterol(productDto.getProductCholesterol())
                .productFat(productDto.getProductFat())
                .productProtein(productDto.getProductProtein())
                .memberInfo(memberInfo)
                .productFlag(productDto.getProductFlag())
                .fileAttached(productDto.getFileAttached())
                .build();
    }

    public void updateProduct(ProductResponseDto productDto){
        this.productWriter = productDto.getProductWriter();
        this.productName = productDto.getProductName();
        this.productCalories = productDto.getProductCalories();
        this.productSodium = productDto.getProductSodium();
        this.productCarbohydrate = productDto.getProductCarbohydrate();
        this.productSugar = productDto.getProductSugar();
        this.productCholesterol = productDto.getProductCholesterol();
        this.productFat = productDto.getProductFat();
        this.productProtein = productDto.getProductProtein();
        this.fileAttached = productDto.getFileAttached();
    }


}
