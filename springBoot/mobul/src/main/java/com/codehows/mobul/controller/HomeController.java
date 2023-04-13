package com.codehows.mobul.controller;

import com.codehows.mobul.dto.BoardsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller

public class HomeController {

    // 기본 주소 요청 이 오면 띄워 주는 메서드
    @GetMapping("/")
    public String mainPage(Model model){

        List<BoardsDTO> boardsDTOList = new ArrayList<>();

        for (int i=1; i<=15; i++){
            BoardsDTO boardsDTO = new BoardsDTO();
            boardsDTO.setBoardId(i);
            boardsDTO.setBoardTitle("테스트 제목 "+i);
            boardsDTO.setBoardDate(LocalDateTime.now());

            boardsDTOList.add(boardsDTO);
        }


        model.addAttribute("boardsDTOList",boardsDTOList);

        return "main";
    }

}
