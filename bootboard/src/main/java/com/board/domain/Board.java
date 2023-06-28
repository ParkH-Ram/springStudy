package com.board.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;           // 게시글 번호 오토인크리먼트

    @Column(length = 500, nullable = false) // 칼럼의 길이와 null 허용여부
    private String title;       // 제목

    @Column(length = 2000, nullable = false)
    private String content;     // 내용

    @Column(length = 50, nullable = false)
    private String writer;       //작성자


    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

}
