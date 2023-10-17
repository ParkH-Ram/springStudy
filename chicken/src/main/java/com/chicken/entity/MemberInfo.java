package com.chicken.entity;

import com.chicken.auditing.BaseEntity;
import com.chicken.constant.Role;
import com.chicken.dto.MemberFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@ToString
@Table(name = "member_info")
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo extends BaseEntity {

    @Id
    private String memberId;            // 로그인 멤버 아이디

    private String memberPassword;      // 로그인 멤버 비밀번호

    private String memberName;          // 멤버 이름

    private String memberEmail;         // 멤버 이메일

    private Long memberHeight;          // 멤버 키

    private Long memberWeight;          // 멤버 몸무게

    private String memberGender;        // 멤버 성별

    @Enumerated(EnumType.STRING)
    private Role memberRole;            // 멤버 권한



    /**
     * 23-10-8
     * 회원가입 폼
     */
    //dto -> entity
    public static MemberInfo createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        return MemberInfo.builder()
                .memberId(memberFormDto.getMemberId())
                .memberPassword(passwordEncoder.encode(memberFormDto.getMemberPassword()))
                .memberName(memberFormDto.getMemberName())
                .memberEmail(memberFormDto.getMemberEmail())
                .memberHeight(memberFormDto.getMemberHeight())
                .memberWeight(memberFormDto.getMemberWeight())
                .memberGender(memberFormDto.getMemberGender())
                .memberRole(Role.USER)
                .build();
    }

    // 체이닝에서 특정 값만 빌더하기 위해 빌더로 만듦
    @Builder
    public MemberInfo(String memberId, String memberPassword, String memberName, String memberEmail, Long memberHeight, Long memberWeight, Role memberRole, String memberGender) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberHeight = memberHeight;
        this.memberWeight = memberWeight;
        this.memberRole = memberRole;
        this.memberGender = memberGender;
    }

    // 스트링을 반환하는 메서드
    // 어떤 권한인지 반환 하는
    public String getRoleKey(){
        return this.memberRole.getKey();
    }

}
