package com.chicken.dto;

import com.chicken.constant.Role;
import com.chicken.entity.MemberInfo;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * 회원 가입 폼
 *
 * **/

@ToString
@Setter
@Getter
public class MemberFormDto {

    private String memberId;

    private String memberPassword;

    private String memberName;

    private String memberEmail;

    private String memberBirth;

    private Long memberHeight;

    private Long memberWeight;

    private String memberGender;


}
