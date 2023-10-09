package com.chicken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chicken")
public class ChickenController {

    @GetMapping("/main")
    public String mainChicken(){
        return "chicken/breast";
    }

}
