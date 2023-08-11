package com.bochung.entity;

import com.bochung.auditing.BaseEntity;
import com.bochung.dto.BoardDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board extends BaseEntity {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    // A to B    A는 현재 entity  B는 객체로 만든 entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="members_email")
    private Members members;


    @Builder
    Board(String title, String writer, String content, Members members) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.members = members;
    }

    public static Board createBoard(BoardDto boardDto, Members members) {
        return Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .members(members)
                .build();
    }

    public void updateBoard(BoardDto boardDto){
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }
}
