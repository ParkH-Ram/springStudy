package com.chicken.entity;

import com.chicken.auditing.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class ImageFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNo;

    @Column
    private String originalFileName;                 //원본 파일명

    @Column
    private String storedFileName;                   // 내 컴퓨터에 저장된 파일명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    private Product product;


    public static ImageFile toProductImageEntity(Product product, String originalFileName, String storedFileName) {

        ImageFile productImage = new ImageFile();
        productImage.setOriginalFileName(originalFileName);
        productImage.setStoredFileName(storedFileName);
        productImage.setProduct(product);

        return productImage;
    }
}
