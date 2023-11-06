package com.chicken.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNo;

    @Column(length = 500, nullable = false)
    private String imageFileUrl;                 //

    @OneToOne
    @JoinColumn(name = "product_No")
    private Product product;


    public void updateUrl(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

}
