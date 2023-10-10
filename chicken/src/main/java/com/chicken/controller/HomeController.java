package com.chicken.controller;

import com.chicken.dto.MemberFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class HomeController {

    @GetMapping("/login")
    public String loginPage(){
        return "login/login";
    }


    @GetMapping("/login/error")
    public String loginError(Model model, @RequestParam String error, @RequestParam String exception){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login/login";
    }

    @GetMapping("/join")
    public String createMember(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());

        return "login/login";
    }


}