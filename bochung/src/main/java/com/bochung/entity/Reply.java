package com.bochung.entity;

import com.bochung.auditing.BaseEntity;
import com.bochung.dto.DupReplyDto;
import com.bochung.dto.ReplyDto;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


/**
 *  23-8-9
 * 댓글 엔티티 **/

@Entity
@Table(name = "reply")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Reply extends BaseEntity {

    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "members_email")
    private Members members;



    @Builder
    Reply(String content, String writer, Board board, Members members){
        this.content = content;
        this.writer = writer;
        this.board = board;
        this.members = members;
    }

    /** Dto 를 Entity로 바꿔주는 애들 **/
    public static Reply createReply(ReplyDto replyDto, Members members, Board board){
        return com.bochung.entity.Reply.builder()
            .content(replyDto.getContent())
            .writer(replyDto.getWriter())
            .members(members)
            .board(board)
            .build();
    }

    public void  updateReply(String content){
        this.content = content;
    }
}
