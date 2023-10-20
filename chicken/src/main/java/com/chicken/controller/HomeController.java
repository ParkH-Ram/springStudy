package com.chicken.controller;

import com.chicken.constant.MessageEnu;
import com.chicken.dto.MemberFormDto;
import com.chicken.entity.MemberInfo;
import com.chicken.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class HomeController {

    private final MemberInfoService memberInfoService;

    @GetMapping("/login")
    public String loginPage() {
        log.info("왜?");
        return "login/login";
    }


    /**
     * 회원 가입 페이지 이동
     **/
    @GetMapping("/new")
    public String createMember(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());

        return "login/createMember";
    }

    /**
     * 값 저장
     **/
    @PostMapping("/new")
    public String saveMember(MemberFormDto memberFormDto, Model model) {

        try {
            memberInfoService.saveMember(memberFormDto);

        } catch (IllegalStateException e) { // 사용중인 아이디면
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "login/createMember";
        }

        // 성공시
        return "redirect:/member/login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(@RequestParam String error, @RequestParam String exception, Model model) {
        log.info(error);
        log.info(exception);
        model.addAttribute("error", error); // 에러
        model.addAttribute("exception", exception); // 예외

        return "login/login";
    }
}
