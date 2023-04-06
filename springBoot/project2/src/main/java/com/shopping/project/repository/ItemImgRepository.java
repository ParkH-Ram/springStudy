package com.shopping.project.repository;

import com.shopping.project.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    //매개변수로 넘겨준 상품 아이디를 가지며, 상품 이미지 아이디의 오름 차순으로 가져오는 쿼리 메소드
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

}
