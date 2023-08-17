package com.bochung.entity;


import com.bochung.auditing.BaseEntity;
import com.bochung.dto.DupReplyDto;
import com.bochung.entity.Members;
import com.bochung.entity.Reply;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "dup_reply")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DupReply extends BaseEntity {
    @Id
    @Column(name = "dup_reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Reply reply;

    @ManyToOne
    @JoinColumn(name = "member_email")
    private Members members;

    @Builder
    DupReply(String content, String writer, Reply reply, Members members) {
        this.content = content;
        this.writer = writer;
        this.reply = reply;
        this.members = members;
    }

    public static DupReply createDupReply(DupReplyDto dupreplyDto, Members members, Reply reply) {
        return DupReply.builder()
            .content(dupreplyDto.getContent())
            .writer(dupreplyDto.getWriter())
            .members(members)
            .reply(reply)
            .build();
    }

    public void updateDupReply(String content) {
        this.content = content;
    }
}
