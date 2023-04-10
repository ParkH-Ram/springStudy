package com.shopping.project.controller;


import com.shopping.project.dto.ItemFormDTO;
import com.shopping.project.dto.ItemSearchDTO;
import com.shopping.project.entity.Item;
import com.shopping.project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller 
@RequiredArgsConstructor   // final이랑 뭐하나 더 있는데 그거만 알아서 매핑 해주는 어노테이션
public class ItemController {

    private final ItemService itemService;

/*
    @GetMapping(value = "/admin/item/new")
    public String itemForm(){
        return "/item/itemForm";
    }
*/


    // 6장에서  메서드 변경
    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDTO", new ItemFormDTO());
        return "item/itemForm";
    }
    @PostMapping(value = "/admin/item/new")
    public String itemNew(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList){
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDTO.getId()==null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필 수 입력!!");
            return "item/itemForm";
        }
        try{

            itemService.saveItem(itemFormDTO, itemImgFileList);
        }catch(Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러 발생 ㅠㅠ");
            e.printStackTrace();
                return "item/itemForm";
        }
        return "redirect:/";
    }
    // 상품 수정 페이지 진입
    @GetMapping(value = "/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){

        try{
            ItemFormDTO itemFormDTO = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDTO", itemFormDTO);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품");
            return "item/itemForm";
        }
        return "item/itemForm";
     }
    @PostMapping(value = "/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult, @RequestParam("itemImgFile)") List<MultipartFile> itemImgFileList, Model model){
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDTO.getId()==null){
            model.addAttribute("errorMessage", "첫 번째 상품 이미지는 필수 입력 값입니다.");
            return "item/itemForm";
        }
        try{    // 상품 수정 로직 호출
            itemService.updateItem(itemFormDTO, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage", "상품 수정중 에러 발생");
            return "item/itemForm";
        }
        return "redirect:/";
    }
    @GetMapping(value = {"/admin/items", "/admin/items/{page}"})
    public String itemManage(ItemSearchDTO itemSearchDTO, @PathVariable("page")Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        Page<Item> items = itemService.getAdminItemPage(itemSearchDTO, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDTO", itemSearchDTO);
        model.addAttribute("maxPage", 5);
        return "item/itemMng";
    }

    @GetMapping(value = "/item/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemFormDTO itemFormDTO = itemService.getItemDtl(itemId);
        model.addAttribute("item", itemFormDTO );
        return "item/itemDtl";
    }
}
