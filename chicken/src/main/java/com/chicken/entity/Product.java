package com.chicken.entity;

import com.chicken.auditing.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product  extends BaseEntity {

    @Id
    private Long productNo;

    private String productName;

    private String productCalories;             // 100g당 칼로리

    private String productSodium;               // 나트륨

    private String productCarbohydrate;         // 탄수화물

    private String productSugar;                // 당

    private String productCholesterol;          // 콜레스테롤

    private String productFat;                  // 지방

    private String productProtein;              // 단백질


}
