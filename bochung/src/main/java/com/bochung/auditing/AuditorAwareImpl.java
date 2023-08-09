package com.bochung.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


/** 23-8-9
 * Auditing에 구현한 return 에서 우리가 줄 갑을 입력하는 부분   **/
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = ""; // userId.equls(email)  //다른 걸로 검증할거면 이부분 수정?

        if(authentication != null){
            userId = authentication.getName();
        }
        return Optional.of(userId);
    }


}
