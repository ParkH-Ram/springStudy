package com.bochung.controller;

import com.bochung.dto.BoardDto;
import com.bochung.service.BoardService;
import com.bochung.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping(value = "/info")
    public String boardInfo(Model model, @RequestParam(required = false, defaultValue = "0") String boardPage) {
        Pageable pageable = PageRequest.of(Integer.parseInt(boardPage),5); // 어디서 부터 시작하고 몇 개까지 출력할 건지
        model.addAttribute("boardPage", boardService.getBoardList(pageable));
        return "/pages/boards/boardInfo";  // 바꿀 부분 1
    }

    // 23 - 8 - 16
    @PostMapping("/info/{page}")
    public String boardPages(@PathVariable Integer page, Model model){
        Pageable pageable = PageRequest.of(page, 15);
        model.addAttribute("boardPage", boardService.getBoardList(pageable));
        return "/pages/boards/pageCard";
    }


    // 게시판 작성을 누르면  그 화면에 값들을 받아서 포스트로 넘겨준다
    @GetMapping(value = "/form")
    public String boardForm(Model model){

        model.addAttribute("boardDto", new BoardDto()); // 입력 받으로 갈 때 입력 받을 변수를 가지고
        //BoardDto boardDto = new BoardDto();     이거랑 똑같음 / 위에 방식은 객체를 따로 생성하지 않아도 되는 문법

        return "/pages/boards/boardForm";

    }

    // 겟에서 넘겨준 파일을 가지고 화면에 출력 <<
    // 서비스의 saveBoard() 메서드 실행
    // saveBoar에 Dto를 entity로 날려주는 메서드 제공
    // boardRepository.save() << 안에   Board 클래스에 선언한 Board createDto (board entity 에 dto를 빌드 할 수 있는 메소드) 를 실행한다
    @PostMapping(value = "/form")
//    public String boardSave(BoardDto boardDto){
// 23-8-2 수정
// @Valid 를 붙이면 클래스를 검증하겠다. 라는 뜻
    public String boardSave(@Valid BoardDto boardDto, BindingResult bindingResult,
                            Authentication authentication,
                            Model model){
        String email = authentication.getName();
        if(bindingResult.hasErrors()){
            return "/pages/boards/boardForm";
        }
        boardService.saveBoard(boardDto,email);
        System.out.println("in33");
        return "redirect:/board/info";
    }
    //    23-8-2
    @GetMapping(value = "/boardDetail/{boardId}")  // @PathVariable 매핑시 주소를 변수로 받기 위한 어노테이션
    public String boardDetail(@PathVariable Long boardId, Model model, Authentication authentication ){

        // 23-8-9 추가 authentication,  boardDto model 에서 꺼냄
        String userEmail = authentication.getName();
        BoardDto boardDetail = boardService.showDetail(boardId);

       model.addAttribute("userEmail", userEmail);
        model.addAttribute("boardDetail", boardDetail);
        model.addAttribute("replies", replyService.getReplyList(boardId)); // boardId를 받아서 안에있는 댓글을 찾아서 건내주는 dto 리스트

        return "/pages/boards/boardDetail";
    }

    // 8-3 일날 했는데 업데이트 못함  // 8-11 업데이트

    //
    @DeleteMapping(value = "delete/{boardId}")
    public String boardDelete(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);
        return "redirect:/board/info";
    }


    @PatchMapping(value = "/update")
    public ResponseEntity<Object> boardUpdate(@RequestBody BoardDto boardDto){
        boardService.updateBoard(boardDto);
        return new ResponseEntity<Object>(boardDto.getId(), HttpStatus.OK);
    }

}
