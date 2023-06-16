package com.tom.webtomcat.mapper;

import com.tom.webtomcat.domain.BoardVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.tom.webtomcat.config.RootConfig.class} )
@Log4j
public class BoardMapperTest {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board));
        // 일반적인 형태
        // BoardMapper에 지정된 메서드 호출
        // List<BoardVO> boardList = mapper. getList();
        // for (BoardVO board : boardList) { log info(board);
    }

    @Test
    public void testInsert(){
        BoardVO board = new BoardVO();
        board.setTitle("새글");
        board.setContent("새 내용");
        board.setWriter("새 작성자");

        mapper.insert(board);
    }

    @Test
    public void testInsertSelectKey(){
        BoardVO board = new BoardVO();
        board.setTitle("셀렉 키 ");
        board.setContent("셀렉 내용");
        board.setWriter("셀랙 작성자");
        mapper.insertSelectKey(board);
    }

    @Test
    public void testRead(){
        //존재하는 번호로 테스트
        BoardVO board = mapper.read(21L);
    }

    @Test
    public void  testDelete(){
        int s = mapper.delete(22L);

        if(s==0){
            System.out.println( "값이 없습니다");
        } else{
            System.out.println("정상적으로 삭제 됨 ");
        }

    }

    @Test
    public void testUpdate(){
        BoardVO board = new BoardVO();
        List<BoardVO> boardList = new ArrayList<>();
        // 실행전 존재하는 번호 확인


        board.setBno(2L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("수정된 유저");



        int count = mapper.update(board);

    }


}
