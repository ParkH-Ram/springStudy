package com.chicken.service;

import com.chicken.dto.MemberFormDto;
import com.chicken.entity.MemberInfo;
import com.chicken.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberInfoService {

    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 23-10-12
     * 회원 가입
     * **/
    public boolean saveMember(MemberFormDto memberFormDto) throws IllegalStateException{
        validateMember(memberFormDto);
        MemberInfo memberInfo = memberFormDto.createMember(passwordEncoder);
        memberInfoRepository.save(memberInfo);
        return true;
    }

    // 검증
    private void validateMember(MemberFormDto memberFormDto) throws IllegalStateException{
        MemberInfo memberInfo = memberInfoRepository.findByMemberId(memberFormDto.getMemberId());

        if(memberInfo != null){
            throw new IllegalStateException("이미 사용중인 아이디 입니다.");
        }
    }
}
