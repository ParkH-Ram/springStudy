package com.board.repository.search;

import com.board.domain.Board;
import com.board.dto.BoardListReplyCountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    // Querydsl을 이용하는  구조 /  댓글 수랑 같이 검색
    Page<BoardListReplyCountDto> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}


