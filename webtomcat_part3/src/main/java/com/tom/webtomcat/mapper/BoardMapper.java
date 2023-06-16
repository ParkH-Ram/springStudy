package com.tom.webtomcat.mapper;

import com.tom.webtomcat.domain.BoardVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardMapper {

//    @Select("select * from tbl_board where bno > 0 ")
    public List<BoardVO> getList();

    public void insert(BoardVO board);  // PK 값을 알 필요가 없는 경우

    public void insertSelectKey(BoardVO board);  // PK 값을 알아야 하는 경우

    public BoardVO read(Long bno);  // Read

    public int delete(Long bno);

    public int update(BoardVO board);



}
