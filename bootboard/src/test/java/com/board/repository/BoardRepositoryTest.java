package com.board.repository;

import com.board.domain.Board;
import com.board.dto.BoardListReplyCountDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // insert 기능
    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title......" + i)
                    .content("content 으으" + i)
                    .writer("작성자는 ------" + (i%10))
                    .build();

            Board result  = boardRepository.save(board);
            log.info("BNO : " + result.getBno());
        });
    }

    // select 기능
    @Test
    public void testSelect(){
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        log.info(board);
    }

    // update
    @Test
    public void testUpdate(){
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        board.change("update..title 100", "update content 100");
        boardRepository.save(board);
    }

  /*  //delete
    @Test
    public void testDelete(){
        Long bno = 1L;

        boardRepository.deleteById(bno);
    }*/

    @Test
    public void testPaging(){
        //1 page order by bno desc
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count : " + result.getTotalElements());
        log.info("total pages : " + result.getTotalPages());
        log.info("page number : " +  result.getNumber());
        log.info("page size : " + result.getSize());

        List<Board> todoList = result.getContent();

        todoList.forEach(board -> log.info(board));

    }

    @Test
    public void testSearch1(){
        //2 page order by bno desc
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }

    @Test
    public void testSearchAll(){
        String[] types = {"t", "c", "w"};

        String keyword = "2";

        // 제일 처음 보이는 페이지에 10개를 출력해라
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());


        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        //total pages
        log.info(result.getTotalPages() + "왜 이게 이거여?");

        // page size
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + " : " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void testSearchReplyCount() {

        String[] types = {"t","c","w"};

        String keyword = "100";

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<BoardListReplyCountDto> result = boardRepository.searchWithReplyCount(types, keyword, pageable );


        //total pages
        log.info(result.getTotalPages() + "토탈페이지");

        //page size
        log.info(result.getSize() + " 페이지 사이즈");

        // pageNumber
        log.info(result.getNumber() + " 페이지 넘버 ");

        //prev next
        log.info(result.hasPrevious() + " : " + result.hasNext() + "프리브넥스트");

        result.getContent().forEach(board -> log.info(board));
    }
}