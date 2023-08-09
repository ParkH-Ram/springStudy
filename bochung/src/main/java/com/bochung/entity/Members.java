package com.bochung.entity;

import com.bochung.constant.Role;
import com.bochung.dto.MemberFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Members {

    private String name;

    @Id
    private String email;   // 유니크로 활용

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    // dto를 entity 로 만드는 메서드
    // 멤버를 만드는 메서드 // 회원가입 정보를 받아오는 dto
    // 패스워드를 암호화
    // 엔티티로 만들땐 builder를 쓰고 dto로 만들 땐 modelmapper를 쓴다
    public static Members createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        return Members.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .role(Role.USER)   // 보통 create 할때는 가장 낮은 권한을 주고, 권한 관리에서 권한 수정
                .build();
    }

    // 체이닝에서 특정 값만 빌더하기 위해 빌더로 만듦
    @Builder
    public Members(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // 스트링을 반환하는 메서드
    // 어떤 권한인지 반환 하는
    public String getRoleKey(){
        return this.role.getKey();
    }



}