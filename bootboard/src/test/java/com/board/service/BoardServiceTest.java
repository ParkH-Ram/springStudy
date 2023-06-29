package com.board.service;

import com.board.dto.BoardDto;
import com.board.dto.PageRequestDto;
import com.board.dto.PageResponseDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class BoardServiceTest {

    @Autowired
    private BoardService boardService;


    //insert test
    @Test
    public void register() {
        log.info(boardService.getClass().getName());

        BoardDto boardDto = BoardDto.builder().title("Sample Title....").content("SampleContent...").writer("user00").build();

        Long bno = boardService.register(boardDto);

        log.info("bno : " + bno);
    }

    // update test
    @Test
    public void testModify(){
        // 변경에 필요한 데이터만
        BoardDto boardDto = BoardDto.builder()
                .bno(101L)
                .title("update..........1111")
                .content("update content 11111111.")
                .build();
        boardService.modify(boardDto);
    }

    // 제목 혹은 내용 혹은 작성자가 1이라는 문자열을 가진 데이터를 검색하고 페이징 처리
    @Test
    public void testList(){
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();
        PageResponseDto<BoardDto> responseDto = boardService.list(pageRequestDto);

        log.info("이거슨?" +  responseDto);
    }


}