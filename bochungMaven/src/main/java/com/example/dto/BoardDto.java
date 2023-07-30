package com.example.dto;

import com.example.domain.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String writer;

    private static ModelMapper modelMapper = new ModelMapper();

    public static BoardDto of(Board board){
        return modelMapper.map(board, BoardDto.class);
    }



}
