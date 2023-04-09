package com.shopping.project.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shopping.project.constant.ItemSellStatus;
import com.shopping.project.dto.ItemSearchDTO;
import com.shopping.project.dto.MainItemDTO;
import com.shopping.project.dto.QMainItemDTO;
import com.shopping.project.entity.Item;
import com.shopping.project.entity.QItem;
import com.shopping.project.entity.QItemImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements  ItemRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus){
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }
    private BooleanExpression itemNmLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%" + searchQuery + "%");
    }
    private BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType ==null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }
        return QItem.item.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.equals("ItemNm", searchBy)) {
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createBy", searchBy)){
            return QItem.item.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable){
        QueryResults<Item> results = queryFactory.selectFrom(QItem.item)
            .where(regDtsAfter(itemSearchDTO.getSearchDateType()),
                searchSellStatusEq(itemSearchDTO.getSearchSellStatus()),
                searchByLike(itemSearchDTO.getSearchBy(),
                itemSearchDTO.getSearchQuery()))
                    .orderBy(QItem.item.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
        List<Item> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainItemDTO> getMainItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable){
        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        QueryResults<MainItemDTO> results = queryFactory
            .select(
                new QMainItemDTO(
                    item.id,
                    item.itemNm,
                    item.itemDetail,
                    itemImg.imgUrl,
                    item.price)
                )
            .from(itemImg)
            .join(itemImg.item, item)
            .where(itemImg.repimgYn.eq("Y"))
            .where(itemNmLike(itemSearchDTO.getSearchQuery()))
            .orderBy(item.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        List<MainItemDTO> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
