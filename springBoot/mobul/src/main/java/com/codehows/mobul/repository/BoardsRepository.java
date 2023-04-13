package com.codehows.mobul.repository;

import com.codehows.mobul.entity.Boards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardsRepository extends JpaRepository<Boards, Long> {

    Boards save(Boards boards);

    // 게시글 조회
//    Boards findByBoardTitle(String boardTitle);
//    Boards findByBoardId(Long boardId);
}
