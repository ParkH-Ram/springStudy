package com.chicken.service;

import com.chicken.dto.MemberFormDto;
import com.chicken.dto.MemberInfoDto;
import com.chicken.entity.MemberInfo;
import com.chicken.repository.MemberInfoRepository;
import com.chicken.security.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
@Log4j2
public class MemberInfoService implements UserDetailsService {

    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 23-10-12
     * 회원 가입
     * 23-10-20 회원가입 수정 --
     * **/
    public void saveMember(MemberFormDto memberFormDto) throws IllegalStateException  { // 한번 건너서 컨트롤러까지 예외처리
        validateMember(memberFormDto); // 중복  아니면 회원가입 진행
        MemberInfo memberInfo = MemberInfo.createMember(memberFormDto, passwordEncoder);
        memberInfoRepository.save(memberInfo);
    }



    // throw 는 내가 에러를 생성하는 것
    // throws 는 위로  전가하는 예약어
    // 중복 확인
    private void validateMember(MemberFormDto memberFormDto) throws  IllegalStateException {
        MemberInfo memberInfo = memberInfoRepository.findByMemberId(memberFormDto.getMemberId());

        if(memberInfo != null ){
            throw new IllegalStateException("이미 사용중인 아이디 입니다.");
        }
    }

    //23-8-8
    // UserDetails 의 오버라이딩은 하나만 한다.
    // 시큐리티에서 관리할 유저가 어떤 유저인지
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException{ // 여기서 throws를 하면 프레임워크가 예외 처리

        MemberInfo memberInfo = memberInfoRepository.findByMemberId(memberId);
        if(memberInfo == null ){
            throw new UsernameNotFoundException(memberId);
        }

        return new CustomMemberDetails(memberInfo);
    }
    // 탈퇴 회원인지 확인 여기서
/*
    public boolean validateRetiredMember(String memberId) {

        MemberInfo memberInfo = memberInfoRepository.findByMemberId(memberId).get();

        if (memberInfo.getMemberFlag().equals("퇴사직원")) {

            return true;
        }

        return false;
    }
*/


    /** 테스트 **/

    /** 전체 맴버 entity 리스트를 dto 리스트로 변환 **/
    public List<MemberInfoDto> getAllMemberList() {

        List<MemberInfoDto> allMemberList = memberInfoRepository.findAll().stream().map(MemberInfoDto::toEntity).collect(Collectors.toList());
        return allMemberList;

    }


}
