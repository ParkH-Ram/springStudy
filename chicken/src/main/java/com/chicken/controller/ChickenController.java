package com.chicken.controller;

import com.chicken.dto.MemberInfoDto;
import com.chicken.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chicken")
@RequiredArgsConstructor
public class ChickenController {

    private final MemberInfoService memberInfoService;

    @GetMapping("/info")
    public String chicken(Model model){

        List<MemberInfoDto> memberInfoDtoList = memberInfoService.getAllMemberList();

        model.addAttribute("memberInfoDtoList", memberInfoDtoList);
        return "chicken/chickenInfo";
    }

}
