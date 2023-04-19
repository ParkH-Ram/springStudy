package com.codehows.ajaxEx.entity;

import com.codehows.ajaxEx.dto.BoardDTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class Board{

    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String boardWriter;

    @Column // 크기 255, null 가능
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFile> boardFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentEntityList = new ArrayList<>();

    @Column
    @CreationTimestamp
    private LocalDateTime boardCreatedTime;

    public static Board toSaveEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardPass(boardDTO.getBoardPass());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardHits(0);
        board.setFileAttached(0); // 파일 없음.
        return board;
    }
    public static Board toUpdateEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setId(boardDTO.getId());
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardPass(boardDTO.getBoardPass());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardHits(boardDTO.getBoardHits());
        return board;
    }
    public static Board toSaveFileEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setBoardWriter(boardDTO.getBoardWriter());
        board.setBoardPass(boardDTO.getBoardPass());
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardHits(0);
        board.setFileAttached(1); // 파일 있음.
        return board;
    }
}