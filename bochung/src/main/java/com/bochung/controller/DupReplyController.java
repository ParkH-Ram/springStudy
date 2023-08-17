package com.bochung.controller;


import com.bochung.service.DupReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dupReply")
public class DupReplyController {

    private final DupReplyService dupReplyService;

    // 23 - 8 -17
    @PostMapping("/new")
    public String createDupReply(@RequestParam Long replyId,
                                 @RequestParam String dupReply,
                                 Authentication authentication){

        Long boardId = dupReplyService.saveDupReply(replyId, dupReply, authentication.getName());
        return "redirect:/board/boardDetail/" + boardId;
    }

    @DeleteMapping("/delete/{dupReplyId}")
    public ResponseEntity<Long> deleteDupReply(@PathVariable Long dupReplyId){
        dupReplyService.deleteDupReply(dupReplyId);
        return new ResponseEntity<Long>(dupReplyId, HttpStatus.OK);
    }

    @PostMapping("/update")
    public String updateDupReply(@RequestParam Long dupReplyId,
                                 @RequestParam String dupReply){
        Long boardId = dupReplyService.updateDupReply(dupReplyId, dupReply);

        return "redirect:/board/detail/" + boardId;
    }

}
