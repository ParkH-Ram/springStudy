package com.bochung.controller;

import com.bochung.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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

    @ResponseBody
    @PostMapping("/modal/{replyId}")
    public String modalReply(@PathVariable Long replyId){
        return replyService.getContent(replyId);
    }
}
