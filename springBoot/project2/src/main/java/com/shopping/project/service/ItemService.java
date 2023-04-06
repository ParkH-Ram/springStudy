package com.shopping.project.service;

import com.shopping.project.dto.ItemFormDTO;
import com.shopping.project.entity.Item;
import com.shopping.project.entity.ItemImg;
import com.shopping.project.repository.ItemImgRepository;
import com.shopping.project.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService{

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws Exception{

        //상품등록
        Item  item = itemFormDTO.craeteItem();
        itemRepository.save(item);

        //이미지 등록
        for(int i=0; i<itemImgFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if(i == 0){
                itemImg.setRepimgYn("Y");
            }else
                itemImg.setRepimgYn("Y");
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }

}
