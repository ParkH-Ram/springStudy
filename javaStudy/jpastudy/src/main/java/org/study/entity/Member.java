package org.study.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "member")
@Getter @Setter @ToString
public class Member {
    @Id
    private String memberId;

    private String memberName;
}
