package com.board.service;

import com.board.dto.PageRequestDto;
import com.board.dto.PageResponseDto;
import com.board.dto.ReplyDto;

public interface ReplyService {

    // 등록
    Long register(ReplyDto replyDto);

    // 조회
    ReplyDto read(Long rno);

    // 수정
    void modify(ReplyDto replyDto);

    //삭제
    void remove(Long rno);

    // 댓글 목록 페이징
    PageResponseDto<ReplyDto> getListOfBoard(Long bno, PageRequestDto pageRequestDto);

}
