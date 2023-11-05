package com.chicken.repository;

import com.chicken.entity.ImageFile;
import com.chicken.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {
    ImageFile findByProduct(Product product);
}
