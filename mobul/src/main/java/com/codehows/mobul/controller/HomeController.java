package com.codehows.mobul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    
    // 기본 주소 요청 이 오면 띄워 주는 메서드 
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
