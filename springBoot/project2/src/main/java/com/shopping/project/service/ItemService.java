package com.shopping.project.service;

import com.shopping.project.dto.ItemFormDTO;
import com.shopping.project.dto.ItemImgDTO;
import com.shopping.project.dto.ItemSearchDTO;
import com.shopping.project.dto.MainItemDTO;
import com.shopping.project.entity.Item;
import com.shopping.project.entity.ItemImg;
import com.shopping.project.repository.ItemImgRepository;
import com.shopping.project.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService{

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws Exception {

        //상품등록
        Item item = itemFormDTO.craeteItem();
        itemRepository.save(item);

        //이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if (i == 0) {
                itemImg.setRepimgYn("Y");
            } else
                itemImg.setRepimgYn("N");
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemFormDTO getItemDtl(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDTO> itemImgDTOList = new ArrayList<>();
        for(ItemImg itemImg : itemImgList){
            ItemImgDTO itemImgDTO = ItemImgDTO.of(itemImg);
            itemImgDTOList.add(itemImgDTO);
        }

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        ItemFormDTO itemFormDTO = ItemFormDTO.of(item);
        itemFormDTO.setItemImgDTOList(itemImgDTOList);
        return itemFormDTO;
    }

    public Long updateItem(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws Exception{
        //상품 수정
        Item item = itemRepository.findById(itemFormDTO.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDTO);

        List<Long> itemImgIds = itemFormDTO.getItemImgIds();

        //이미지 등록
        for(int i=0; i< itemImgFileList.size(); i++){
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }
        return item.getId();

    }
    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDTO, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainItemDTO> getMainItemPage(ItemSearchDTO itemSearchDTO, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDTO, pageable);
    }

}
