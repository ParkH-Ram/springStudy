package com.codehows.mobul.repository;

import com.codehows.mobul.entity.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Boards, Long> {

    Boards save(Boards boards);




}
