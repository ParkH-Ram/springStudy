package com.codehows.ajaxEx.repository;

import com.codehows.ajaxEx.entity.Board;
import com.codehows.ajaxEx.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // select * from comment_table where board_id=? order by id desc;
    List<Comment> findAllByBoardOrderByIdDesc(Board board);
}