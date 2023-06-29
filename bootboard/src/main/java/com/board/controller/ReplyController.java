package com.board.controller;


import com.board.dto.ReplyDto;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("replies")
@Log4j2
public class ReplyController {
    @ApiOperation(value = "Replies POST", notes = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Map<String, Long>> register (@RequestBody ReplyDto replyDto){
     public Map<String, Long> register (@Valid @RequestBody ReplyDto replyDto,
                                        BindingResult bindingResult) throws BindException{

        log.info(replyDto + "리플라이 디티오");

        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = Map.of("rno" , 111L);
        return resultMap;
    }
}

