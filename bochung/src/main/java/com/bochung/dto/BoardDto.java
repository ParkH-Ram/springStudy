package com.bochung.dto;

import com.bochung.entity.Board;
import com.bochung.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;            // 게시판 고유번호
    private String title;       // 게시판 제목
    private String writer;      // 게시판 작성자
    private String content;     // 게시판 내용

    private static ModelMapper modelmapper = new ModelMapper();

    public static BoardDto of(Board board) {
        return modelmapper.map(board, BoardDto.class);
    }

}
