package com.codehows.mobul.entity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity // JPA Entity로 설정
@Table(name = "boards") // "boards" 테이블과 연결
@Getter @Setter @ToString
public class Boards {

    @Id // 기본키(primary key)로 사용될 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성 전략을 IDENTITY로 설정
    @Column(name = "board_id") // board_id 컬럼과 연결
    private Long boardId; // 게시글 번호

    @Column(name = "board_title", nullable = false, length = 50) // board_title 컬럼과 연결
    private String boardTitle; // 게시글 제목

    @Column(name = "board_content", nullable = false, columnDefinition = "TEXT") // board_content 컬럼과 연결
    private String boardContent; // 게시글 내용

    @Column(name = "board_view", nullable = false)
    private Integer boardView = 0; // 게시글 조회수, 기본값 0



    @ManyToOne // 다대일(N:1) 관계 설정
    @JoinColumn(name = "board_writer", referencedColumnName = "user_id", insertable = false, updatable = false
                ,foreignKey = @ForeignKey(name = "fk_board_writer")) // 외래키 설정, users 테이블의 user_id와 연결
    private Users boardWriter; // 게시글 작성자 정보


    @Column(name = "board_like", nullable = false)
    private Integer boardLike = 0; // 게시글 좋아요 수, 기본값 0

    @Column(name = "board_tag", length = 30) // board_tag 컬럼과 연결
    private String boardTag; // 게시글 해시태그

    // 게시물 작성시간
    @CreationTimestamp
    @Column(columnDefinition = "timestamp")
    private LocalDateTime boardDate;

    @Column(name = "board_one") // board_one 컬럼과 연결
    private String boardOne; // 추가 필드 1

    @Column(name = "board_two") // board_two 컬럼과 연결
    private String boardTwo; // 추가 필드 2

    @Column(name = "board_three") // board_three 컬럼과 연결
    private Integer boardThree; // 추가 필드 3

    @Column(name = "board_four") // board_four 컬럼과 연결
    private Integer boardFour; // 추가 필드 4


}