package com.bochung.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 가입 할 때 쓰는 FormDto
 */

@Getter
@Setter
@ToString
public class MemberFormDto {

    private String name;

    private String email;

    private String password;

}
