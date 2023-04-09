package com.shopping.project.repository;

import com.shopping.project.dto.ItemSearchDTO;
import com.shopping.project.dto.MainItemDTO;
import com.shopping.project.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    // 상품 조회 조건을 담고 있는 itemSearchDTO 객체와 페이징 정보를 담고 있는 pageable 객체를
    // 파라미터로 받는 getAdminItemPage 메소드를 정의합니다. 반환 데이터로 Page<Item> 객체로 반환합니다.
    Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable);

    Page<MainItemDTO> getMainItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable);

}
