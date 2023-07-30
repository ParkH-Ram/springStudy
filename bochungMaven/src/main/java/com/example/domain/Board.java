package com.example.domain;

import com.example.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @Column(name = "Board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    public static Board createBoard(BoardDto boardDto){
        return Board.builder()
            .title(boardDto.getTitle())
            .writer(boardDto.getWriter())
            .build();
    }

}
