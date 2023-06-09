package com.jpastudy.study.entity;

import com.jpastudy.study.MemberType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Date;

@Entity
@Table(name = "users")
@Setter @Getter @ToString
public class Member {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "USERNAME")
    private String Name;


    @Column(nullable = true)
    private int age;

    //
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    // 이넘 타입은 무조건 스트링. 현업에서?
    @Enumerated(EnumType.STRING)
    private MemberType memberType;


}
