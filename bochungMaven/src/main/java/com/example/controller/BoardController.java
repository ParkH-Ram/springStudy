package com.example.controller;

import com.example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor  // final 이나 @NotNull 붙은 필드의 생성자를 자동 생성
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/info")
    public String boardInfo(Model model){
        model.addAttribute("boards", boardService.getBoardList());
        return "/boards/boardInfo";
    }
}
