package com.board.repository;

import com.board.domain.Board;
import com.board.domain.BoardImage;
import com.board.dto.BoardListReplyCountDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

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
        log.info("page  : " +  result.getNumber());
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

    // 이미지 insert 테스트
    @Test
    public void testInsertWithImage(){
        Board board = Board.builder()
            .title("이미지 테스트")
            .content("첨부파일 테스트")
            .writer("테스터")
            .build();

        // 첨부파일 3개 추가 하는 경우를 가정
        for (int i=0; i < 3; i++){
            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        } // end for
        boardRepository.save(board);
    } // end insert test

    //Lazy 로딩과 @EntityGraph
    // 이미지 읽기
//    @Transactional
    @Test
    public void testReadWithImages(){
//        Optional<Board> result = boardRepository.findById(1L);
        Optional<Board> result = boardRepository.findByIdWithImages(1L);
        Board board = result.orElseThrow();
        System.out.println(board + "여기다!!!!!!!!!");

        log.info(board);
        log.info("000000000000000000000000");
        for(BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);
        }
    }

    //첨부파일 수정
    @Test
    @Commit
    @Transactional
    public void testModifyImages(){

        Optional<Board> result = boardRepository.findByIdWithImages(1L);

        Board board = result.orElseThrow();

        //기존의 첨부파일 삭제
        board.clearImages();

        //새로운 첨부파일
        for (int i = 0; i<3; i++){
            board.addImage(UUID.randomUUID().toString(), "updateFile" + i + ".jpg");
        } // end for

        boardRepository.save(board);
    }

    // 게시물 댓글 같이 삭제 테스트
    @Test
    @Transactional
    @Commit
    public void testRemoveAll(){

        Long bno = 1L;

        replyRepository.deleteByBoard_Bno(bno);

        boardRepository.deleteById(bno);



    }

    @Test
    public void testInsertAll(){
        for(int i = 1; i <= 100; i ++){

            Board board = Board.builder()
                .title("Title....." + i)
                .content("냉ㅇ..." + i)
                .writer("작성자"  + i)
                .build();

            for (int j = 0; j <3; j ++ ){
                if (i % 5 == 0){
                    continue;
                }  // if end
                board.addImage(UUID.randomUUID().toString(), i + "file" + j +".jpg");
            } //for end
            boardRepository.save(board);

        } // 최종 for end
    }




}