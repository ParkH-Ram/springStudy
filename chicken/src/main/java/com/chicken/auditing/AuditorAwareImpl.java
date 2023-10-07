package com.chicken.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = ""; // memberId.equls(memberId)

        if(authentication != null) {
            memberId = authentication.getName();
        }

        return Optional.of(memberId);
    }
}
