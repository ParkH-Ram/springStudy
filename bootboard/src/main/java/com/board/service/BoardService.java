package com.board.service;

import com.board.dto.BoardDto;
import com.board.dto.BoardListReplyCountDto;
import com.board.dto.PageRequestDto;
import com.board.dto.PageResponseDto;

public interface BoardService {

    Long register(BoardDto boardDto);

    BoardDto readOne(Long bno);

    void modify(BoardDto boardDto);

    //삭제
    void remove(Long bno);

    PageResponseDto<BoardDto> list(PageRequestDto pageRequestDto);

    //댓글의 숫자까지 처리
    PageResponseDto<BoardListReplyCountDto> listWithReplyCount(PageRequestDto pageRequestDto);
}
