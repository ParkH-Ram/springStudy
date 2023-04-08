package com.homestudy.member.service;

import com.homestudy.member.dto.MemberDTO;
import com.homestudy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public int save(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO);
    }

    public boolean login(MemberDTO memberDTO){
        //리파지토리로 부터 로그인을 위한 쿼리를 수행하고 결과를 DTO 객체로 받아온다
        MemberDTO loginMember = memberRepository.login(memberDTO);
        // null이 아니면 로그인을 성공 시키겠다.
        if(loginMember != null){
            return true;
        } else{
            return false;
        }
    }

    public List<MemberDTO> findAll() {
        return memberRepository.findAll();


    }

}