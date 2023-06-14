package com.tom.webtomcat.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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


}
