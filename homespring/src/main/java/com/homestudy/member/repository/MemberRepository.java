package com.homestudy.member.repository;

import com.homestudy.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor    //의존성 주입
public class MemberRepository {
    private final SqlSessionTemplate sql;
    public int save(MemberDTO memberDTO) {   // insert 문
        System.out.println("memberDTO = " + memberDTO);
        //mapper namespace="Member"이랑 일치여부 확인
        // Member -> mapper namespace="Member"를 가리키고
        // Member.save에서 save는  mapper namespace="Member" 의 id = 'save'를 가르킨다
        return sql.insert("Member.save", memberDTO);  //넘기는 객체
    }
}
