package com.shopping.project.dto;


import com.shopping.project.constant.ItemSellStatus;
import com.shopping.project.entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
public class ItemFormDTO {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값이다")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값")
    private Integer price;

    @NotBlank(message = "이름은 필수 입력 값")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDTO> itemImgDTOList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item craeteItem(){
        return modelMapper.map(this, Item.class);
    }
    public static ItemFormDTO of(Item item){
        return modelMapper.map(item, ItemFormDTO.class);
    }


}
