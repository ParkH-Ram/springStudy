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

    @Column(nullable = false)
    private String imageFileUrl;                 //원본 파일명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    @OneToOne
    @JoinColumn(name = "product_No")
    private Product product;


    public void updateUrl(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

}
