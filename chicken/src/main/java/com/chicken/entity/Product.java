package com.chicken.entity;

import com.chicken.auditing.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "PRODUCT")
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    private Long productNo;

}
