package com.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDto {  // 목록화면에 특정한 게시물에 속한 댓글의 숫자를 같이 출력해줘야 함

    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;

    private Long replyCount;  // 댓글 수

}
