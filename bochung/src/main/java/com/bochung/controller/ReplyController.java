package com.bochung.controller;

import com.bochung.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/new")
    public String replyNew(@RequestParam("reply") String content,
                           @RequestParam Long boardId,
                           Authentication authentication){

        String userEmail = authentication.getName();

        replyService.saveReply(boardId, content, userEmail);

        return "redirect:/board/boardDetail/" + boardId;
    }

    @DeleteMapping("/delete/{replyId}")
    public ResponseEntity<?> deleteReply(@PathVariable Long replyId){
        replyService.deleteReply(replyId);
        return new ResponseEntity<>(replyId, HttpStatus.OK);
    }


    // 23 - 8 - 17
    @ResponseBody
    @PostMapping("/modal/{replyId}")
    public String modalReply(@PathVariable Long replyId){
        return replyService.getContent(replyId);
    }

    @PatchMapping("/update/{replyId}")
    public String updateReply(@PathVariable Long replyId,
                              @RequestBody String content, // ajax 넘겨준 데이타를 받아오는 부분
                              Model model, Authentication authentication){
        Long boardId = replyService.updateReply(replyId, content);


        model.addAttribute("replies", replyService.getReplyList(boardId)); // 모델을 가지고 가서 랜더링이 끝난 카드를 리턴
        model.addAttribute("userEmail", authentication.getName());

        return "pages/boards/replyCard";
    }


}
