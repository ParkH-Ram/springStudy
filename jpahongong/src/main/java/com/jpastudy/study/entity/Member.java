package com.jpastudy.study.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
@Setter @Getter
public class Member {

    @Id
    private Long memberId;

    private String memberName;
}
