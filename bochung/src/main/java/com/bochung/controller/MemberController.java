package com.bochung.controller;

import com.bochung.dto.MemberFormDto;
import com.bochung.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "pages/member/loginForm";
    }

    @GetMapping("/new")
    public String createMember(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "pages/member/createForm";
    }

    /** 회원가입 폼을 받은 핸들러 **/
    @PostMapping("/new")
    public String saveMember(MemberFormDto memberFormDto, Model model ){

        try{
            memberService.saveMember(memberFormDto);  // 중복이 있으면 여기까지 떠넘겨 지면
        } catch(IllegalStateException e){  // e의 값은 이미 사용중인 값.
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "/pages/member/createForm";
        }
        return "redirect:/member/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model, @RequestParam String error, @RequestParam String exception){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "pages/member/loginForm";
    }
}
