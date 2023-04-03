package com.homestudy.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
    //회원과 관련된 정보 인스턴스 변수 생성
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private int memberAge;
    private String memberMobile;
}