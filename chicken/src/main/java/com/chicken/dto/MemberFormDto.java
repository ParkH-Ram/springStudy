package com.chicken.dto;

import com.chicken.entity.MemberInfo;
import lombok.*;


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

}
