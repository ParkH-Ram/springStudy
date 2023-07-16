package com.board.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


/*    //board 클래스에 @OneToMany 적용
    @OneToMany
    @Builder.Default
    private Set<BoardImage> imageSet = new HashSet<>();*/

    // mappedBy 사용
    @OneToMany(mappedBy = "board",             // BoardImage의 board 변수
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    @Builder.Default
    private Set<BoardImage> imageSet = new HashSet<>();

    // 이미지 추가
    public void addImage(String uuid, String fileName){

        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(boardImage);
    }

    // 삭제?
    public void clearImages(){
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));

        this.imageSet.clear();
    }
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

}
