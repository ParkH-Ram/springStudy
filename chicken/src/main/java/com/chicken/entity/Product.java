package com.chicken.entity;

import com.chicken.auditing.BaseEntity;
import com.chicken.dto.MemberInfoDto;
import com.chicken.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.context.properties.bind.Name;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @OneToOne(mappedBy = "product" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ImageFile imageFile;

    @Builder
    public static Product createProduct(ProductDto productDto, MemberInfo memberInfo, ImageFile imageFile){
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
                .imageFile(imageFile)
                .build();
    }

    public void updateProduct(ProductDto productDto) {
        this.productWriter = productDto.getProductWriter();
        this.productName = productDto.getProductName();
        this.productCalories = productDto.getProductCalories();
        this.productSodium = productDto.getProductSodium();
        this.produ



    }
}
