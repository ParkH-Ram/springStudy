package com.codehows.ajaxEx.controller;


import com.codehows.ajaxEx.dto.AjaxDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

    @GetMapping("/ex01")
    public String ex01(){
        System.out.println("함보장");
        return "/index";
    }

    @GetMapping("/ex05")
    public @ResponseBody AjaxDTO ex05(@ModelAttribute  AjaxDTO ajaxDTO){  //@ModelAttribute 가 DTO 값을 받아준다. 생략 가능하지만 붙여 놔야  모를때는 편함
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;

        //이거 잘 확인
    }

    @PostMapping("/ex06")
    public @ResponseBody AjaxDTO ex06(@ModelAttribute AjaxDTO ajaxDTO){
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }


}
