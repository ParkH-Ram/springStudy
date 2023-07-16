package com.board.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage>{

    @Id
    private String uuid; // 첨부파일의 고유 uuid

    private String fileName;    // 파일 이름

    private int ord;  // 순번

    @ManyToOne
    private Board board; // 부모 객체의 bno를 받기 위해

    @Override
    public int compareTo(BoardImage other){
        return this.ord - other.ord;
    }

    public void changeBoard(Board board){
        this.board = board;
    }
}
