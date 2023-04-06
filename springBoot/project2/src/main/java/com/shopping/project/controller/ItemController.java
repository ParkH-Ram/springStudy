package com.shopping.project.controller;


import com.shopping.project.dto.ItemFormDTO;
import com.shopping.project.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ItemController {

    private ItemService itemService;

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
                return "item/itemForm";
        }
        return "redirect:/";

    }

}
