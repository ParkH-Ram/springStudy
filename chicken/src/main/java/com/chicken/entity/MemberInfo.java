package com.chicken.entity;

import com.chicken.constant.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "member_info")
@NoArgsConstructor
public class MemberInfo {

    @Id
    private String memberNo;

    private String memberId;

    private String memberPassWord;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 스트링을 반환하는 메서드
    // 어떤 권한인지 반환 하는
    public String getRoleKey(){
        return this.role.getKey();
    }

}
