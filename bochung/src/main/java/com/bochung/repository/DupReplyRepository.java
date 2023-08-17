package com.bochung.repository;

import com.bochung.entity.DupReply;
import com.bochung.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DupReplyRepository extends JpaRepository<DupReply, Long> {

    List<DupReply> findByReply(Reply reply);

}
