package com.chicken.controller;

import com.chicken.dto.MemberFormDto;
import com.chicken.entity.MemberInfo;
import com.chicken.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class HomeController {

    private final MemberInfoService memberInfoService;

    @GetMapping("/login")
    public String loginPage(){
        return "login/login";
    }


    @GetMapping(value = "/login/error")
    public String loginError(@RequestParam(value = "error") boolean error, @RequestParam(value = "exception") String exception, Model model) {

        log.info(error);
        log.info(exception);

        if (error) {
            model.addAttribute("loginErrorMsg", exception);

        } else {
            model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        }

        return "login/login";
    }

    @GetMapping("/join")
    public String createMember(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());

        return "login/login";
    }

    @PostMapping("/join")
    public String newMember(MemberFormDto memberFormDto, Model model){

        try {
            memberInfoService.saveMember(memberFormDto);
        } catch(IllegalStateException e){ // 이미 사용중일 때 넘기기
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "login/login";
        }

        return "login/createMember";

    }


}
