package com.shopping.project.controller;


import com.shopping.project.dto.MemberFormDTO;
import com.shopping.project.entity.Member;
import com.shopping.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value ="/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberForm(MemberFormDTO memberFormDTO){

        Member member = Member.createMember(memberFormDTO, passwordEncoder);
        memberService.saveMember(member);

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인");
        return "/member/memberLoginForm";
    }
}
