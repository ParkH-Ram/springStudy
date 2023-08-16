package com.bochung.repository;

import com.bochung.entity.Board;
import com.bochung.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByBoard(Board board);
}
