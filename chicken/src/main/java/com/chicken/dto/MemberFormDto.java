package com.chicken.dto;

import com.chicken.constant.Role;
import com.chicken.entity.MemberInfo;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 회원 가입 폼
 * **/

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDto {
    private String memberId;

    private String memberPassword;

    private String memberName;

    private String memberEmail;

    private Long memberHeight;

    private Long memberWeight;

    private String memberGender;

    // 회원가입 틀에서 받아온 값 db에 넣기
    public MemberInfo createMember(PasswordEncoder passwordEncoder) {

        return MemberInfo.builder()
            .memberId(this.memberId)
            .memberPassword(passwordEncoder.encode(this.getMemberPassword()))
            .memberName(this.memberName)
            .memberEmail(this.memberEmail)
            .memberHeight(this.memberHeight)
            .memberWeight(this.memberWeight)
            .memberGender(this.memberGender)
            .memberRole(Role.USER)
            .build();
    }

}
