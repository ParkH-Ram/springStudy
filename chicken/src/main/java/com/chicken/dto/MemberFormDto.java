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
 * **/

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDto {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9_]{1,10}$", message = "아이디는 영어 소문자와 숫자, 언더바(_)만 사용하여 10자리여야 합니다.")
    private String memberId;

    private String memberPassword;

    private String memberName;

    private String memberEmail;

    private String memberBirth;

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
                .memberBirth(getDate(this.memberBirth))
                .memberHeight(this.memberHeight)
                .memberWeight(this.memberWeight)
                .memberGender(this.memberGender)
                .memberRole(Role.USER)
                .build();
    }

    public LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date == null || "".equals(date) ? null : LocalDate.parse(date, formatter);
    }

}
