package com.homestudy.member.controller;

import com.homestudy.member.dto.MemberDTO;
import com.homestudy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //    @GetMapping("/member/save") // /member/member/save
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        int saveResult = memberService.save(memberDTO);
        if (saveResult > 0) {
            return "login";
        } else {
            return "save";
        }
    }
    //로그인 페이지를 띄우는 메서드
    @GetMapping("/login")
    public String LoginForm() {
        return "login";
    }

    //로그인 처리를 하는 메서드
    @PostMapping("/login")
    //MemberDTO로 받아도 상관 없다. 값이 없어도 DTO로 받아도 상관없다. 있는 것만 받아 주니까
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        boolean loginResult = memberService.login(memberDTO);
        if(loginResult){
            //로그인을 하게 되면 내 정보가 계속 따라 다녀야 하니까
            // 세션을 활용.. 매개 변수에 세션도 같이 값을 받는다
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "main";  // main.jsp로 값이 넘어 가도록 설정

        } else { 
            return "login";  // 실패하면 로그인 페이지가 계속 떠 있도록 설정
        }


        }
    @GetMapping("/")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "list";

    }
    //member?id=1 이런 주소 요청  member? 이런 주소 형태이기 때문에 @RequestParam을 활용한다
    //id를 서버로 보내고 id를  화면에 보여주자
    //조회기능

    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);    // id 값을 넘겨서 DTO 객체로 받고
        model.addAttribute("member", memberDTO);  // 받아온 값을
        return "detail";                                          //detail.jsp로 보낸다
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){
        memberService.delete(id);
        return "redirect:/member/";  // redirect 뒤에는 주소값이 와야 한다.
    }

    //수정화면 요청
    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model){
        //세션에 저장된 나의 이메일 가져오기
        // 오브젝트를 String으로 형변환 해줘야 한다.
        String loginEmail = (String )session.getAttribute("loginEmail"); // 로그인 이메일 파라미터를 가져온다
        MemberDTO memberDTO =  memberService.findByMemberEmaill(loginEmail);
        model.addAttribute("member", memberDTO);
        return "update";

    }
}