package com.board.service;

import com.board.dto.ReplyDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    // 댓글 등록 테스트
    @Test
    public void testRegister(){

        ReplyDto replyDto = ReplyDto.builder()
                .replyText("ReplyDto Text")
                .replier("replier")
                .bno(100L)
                .build();


                // 댓글 수 출력
                log.info(replyService.register(replyDto) + "댓글 등록");

    }


}