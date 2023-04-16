package com.codehows.mobul.repository;

import com.codehows.mobul.entity.Boards;
import com.codehows.mobul.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;


import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BoardsRepository extends JpaRepository<Boards, Long >, QuerydslPredicateExecutor<Boards> { //entity 이름, PK 타입

    List<Boards> findByBoardId(Long boardId);  // 게시판 번호 찾기

    List<Boards> findByBoardTitle(String boardTitle); // 제목으로 찾기

    List<Boards> findByBoardWriterOrBoardTitle(Users boardWriter, String boardTitle); // 작성자 Or 타이틀로 찾기

    List<Boards> findByBoardLikeLessThan(Integer boardLike);  // 보다 작은 수를 가진 것들 호출

    List<Boards> findByBoardLikeLessThanOrderByBoardLikeDesc(Integer boardWriter);

    @Query("select i from Boards i where i.boardTitle like %:boardLike% order by i.boardTitle desc" )
    List<Boards> findByBoardsLike(@Param("boardLike") String boardTitle);

    Page<Boards> findByBoardContentContaining(String searchContent, Pageable pageable);

    Page<Boards> findByBoardTitleContaining(String searchTitle, Pageable pageable);
}






















    //<S extends T> save(s entity)     저장 및 수정
    // void delete(T entity)            삭제
    //count()                           entity 총 개수 반환
    //Iterable<T> findAll()             모든 엔티티 조회
/*    Boards save(Boards boards);
    Optional<Boards> findById(Long boardId);
    Optional<Boards> findByTitle(String BoardTitle);
    List<Boards> findAll();*/





