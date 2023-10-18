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

import javax.validation.Valid;

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

/*    @GetMapping("/join")
    public String createMember(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());

        return "login/login";
    }*/

    @PostMapping("/join")
    public ResponseEntity<?> newMember(@Valid @ModelAttribute MemberFormDto memberFormDto,
                                       BindingResult bindingResult) {
        
        log.info("들어온거 확인");

          boolean checkId = memberInfoService.saveMember(memberFormDto);

          if(checkId)
              return ResponseEntity.ok(MessageEnu.valueOf(MessageEnu.REGISTER_OK.name()).getTitle());
         else
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MessageEnu.valueOf(MessageEnu.REGISTER_FAIL.name()).getTitle());

    }

}
