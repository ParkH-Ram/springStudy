package com.board.controller;

import com.board.dto.BoardDto;
import com.board.dto.BoardListReplyCountDto;
import com.board.dto.PageRequestDto;
import com.board.dto.PageResponseDto;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDto pageRequestDto, Model model){

//        PageResponseDto<BoardDto> responseDto = boardService.list(pageRequestDto);

        PageResponseDto<BoardListReplyCountDto> responseDto = 
                boardService.listWithReplyCount(pageRequestDto);
        
        log.info(responseDto + "리스폰스디티오");

        model.addAttribute("responseDto", responseDto);

    }

    @GetMapping("/register")
    public void registerGET(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDto boardDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info("board POST register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/board/register";
        }

        log.info(boardDto);

        Long bno  = boardService.register(boardDto);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }


    /*@GetMapping("/read")
    public void read(Long bno, PageRequestDto pageRequestDto, Model model){

        BoardDto boardDto = boardService.readOne(bno);

      log.info(boardDto);

        model.addAttribute("dto", boardDto);

    }*/


    // 읽기 및 수정
   @GetMapping({"/read", "/modify"})
    public void read(Long bno, PageRequestDto pageRequestDto, Model model){

        BoardDto boardDto = boardService.readOne(bno);

        log.info(boardDto);

        model.addAttribute("dto", boardDto);

    }

    // 수정 후 처리
    @PostMapping("/modify")
    public String modify( PageRequestDto pageRequestDto,
                          @Valid BoardDto boardDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        log.info("board modify post......." + boardDto);

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");

            String link = pageRequestDto.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );

            redirectAttributes.addAttribute("bno", boardDto.getBno());

            return "redirect:/board/modify?"+link;
        }

        boardService.modify(boardDto);

        redirectAttributes.addFlashAttribute("result", "modified");

        redirectAttributes.addAttribute("bno", boardDto.getBno());

        return "redirect:/board/read";
    }


    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes) {

        log.info("remove post.. " + bno);

        boardService.remove(bno);

        redirectAttributes.addFlashAttribute("result", "removed");

        return "redirect:/board/list";

    }
    



}
