package com.bochung.service;


import com.bochung.dto.MemberFormDto;
import com.bochung.entity.Members;
import com.bochung.repository.MemberRepository;
import com.bochung.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveMember(MemberFormDto memberFormDto) throws IllegalStateException{   // 한번 건너서 컨트롤러까지 예외처리
        validateMember(memberFormDto);
        Members members = Members.createMember(memberFormDto, passwordEncoder);
        memberRepository.save(members);
    }
    // throw 는 내가 에러를 생성하는 것
    // throws 는 위로  전가하는 예약어
    private void validateMember(MemberFormDto memberFormDto) throws IllegalStateException{
        Members members = memberRepository.findByEmail(memberFormDto.getEmail());
        if(members != null) {
            throw new IllegalStateException("이미 사용 중인 이메일 이다.");
        }
    }

    //23-8-8
    // UserDetails 의 오버라이딩은 하나만 한다.
    // 시큐리티에서 관리할 유저가 어떤 유저인지
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // 여기서 throws를 하면 프레임워크가 예외처리 해준다

        Members members = memberRepository.findByEmail(email);
        if (members == null) {
            throw new UsernameNotFoundException(email);
        }

        return new CustomUserDetails(members);
    }
}
