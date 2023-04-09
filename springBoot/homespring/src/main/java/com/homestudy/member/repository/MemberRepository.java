package com.homestudy.member.repository;

import com.homestudy.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public MemberDTO login(MemberDTO memberDTO) {
        // selectOne = 조회 결과가 하나
        // selectList = 조회 결과가 여러개
        // 로그인을 호출하고 DTO 객체를 넘겨 준다   // DTO 값 하나를 넘기겠다..?
        return sql.selectOne("Member.login", memberDTO);
        // 셀렉트 원 인데 중복이 있으면 500에러 발생 할 수 있다.
    }

    public List<MemberDTO> findAll() {  // 전체를 끌어 오는게 목적이기 때문에  매개변수가 없다.
        return sql.selectList("Member.findAll");    // mapper의 findAll이라는 아이디를 가진 객체를 호출 하겠다.
    }

    public MemberDTO findById(Long id) {
        return sql.selectOne("Member.findById", id);
    }

    public void delete(Long id) {
        sql.delete("Member.delete", id);
    }

    public MemberDTO findByMemberEmail(String loginEmail) {
        return sql.selectOne("Member.findByMemberEmail", loginEmail); // 매퍼에 정의 하겠다.
    }

    public int update(MemberDTO memberDTO) {
        // update 라는 메서드는 int 타입으로 리턴을 준다
        return sql.update("Member.update", memberDTO);
    }
}