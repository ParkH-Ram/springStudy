package com.board.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")  // 인덱스 지정
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    // Board 타입의 객체 참조를 board라는 변수를 이용해 참조
    // 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;

    private String replier;
}
