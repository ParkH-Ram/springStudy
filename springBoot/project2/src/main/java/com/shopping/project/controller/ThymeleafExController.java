package com.shopping.project.controller;


import com.shopping.project.dto.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/thymeleaf") //localhost9091/thymeleaf
public class ThymeleafExController {

    @GetMapping(value = "/ex01")        //localhost9091/thymeleaf/ex01
    public String thymeleafExample01(Model model){
        model.addAttribute("data",  "타임리프 글아라라라니다.");  // 모델을 이용한 데이터 전달
        return "/thymeleafEx/thymeleafEx01";         // 뷰
    }

    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemDetail("상품 상세 설명");
        itemDTO.setItemNm("테스트 상품1");
        itemDTO.setPrice(10000);
        itemDTO.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("regTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd a HH:mm:ss")));
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value ="/ex03")
    public String thymeleafExample03(Model model){

        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (int i=1; i<=10; i++){
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemDetail("상품 상세 " + i);
            itemDTO.setItemNm("테스트 상품" + i);
            itemDTO.setPrice(1000*i);
            itemDTO.setRegTime(LocalDateTime.now());

            itemDTOList.add(itemDTO);
        }

        model.addAttribute("itemDTOList", itemDTOList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value ="/ex04")
    public String thymeleafExample04(Model model){

        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (int i=1; i<=10; i++){
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemDetail("상품 상세 " + i);
            itemDTO.setItemNm("테스트 상품" + i);
            itemDTO.setPrice(1000*i);
            itemDTO.setRegTime(LocalDateTime.now());

            itemDTOList.add(itemDTO);
        }

        model.addAttribute("itemDTOList", itemDTOList);
        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping(value="/ex05")
    public String thymeleafeExample05(){
        return "thymeleafEx/thymeleafEx05";
    }

    @GetMapping(value = "/ex06")
    public String thymeleafExample06(String param1, String param2, Model model){
        model.addAttribute("param1",param1);
        model.addAttribute("param2",param2);
        return "thymeleafEx/thymeleafEx06";
    }

    @GetMapping(value = "/ex07")
    public String thymeleafExample07(){
        return "thymeleafEx/thymeleafEx07";
    }


}
