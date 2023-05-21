package com.jpastudy.study.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
@Setter @Getter @ToString
public class Member {

    @Id
    private Long Id;

    private String Name;
}
