package com.chicken.service;

import com.chicken.dto.MemberFormDto;
import com.chicken.entity.MemberInfo;
import com.chicken.repository.MemberInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
@Log4j2
public class MemberInfoService {

    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 23-10-12
     * 회원 가입
     * **/
    public boolean saveMember(MemberFormDto memberFormDto) throws IllegalStateException{
       try {
           log.info("여기  서비스");
           validateMember(memberFormDto); // 중복인지 확인하고
           MemberInfo memberInfo = memberFormDto.createMember(passwordEncoder);
           memberInfoRepository.save(memberInfo);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }
    }

    // 검증
    public boolean validateMember(MemberFormDto memberFormDto) throws IllegalStateException{
        Optional<MemberInfo> memberInfo = memberInfoRepository.findByMemberId(memberFormDto.getMemberId());

        // 비어있는지 아닌지 리턴
        return memberInfo.isPresent();
    }
}
