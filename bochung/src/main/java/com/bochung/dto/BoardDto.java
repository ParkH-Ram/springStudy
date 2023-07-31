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
    private Long id;
    private String title;
    private String writer;

    private static ModelMapper modelmapper = new ModelMapper();

    public static BoardDto of(Board board) {
        return modelmapper.map(board, BoardDto.class);
    }

}
