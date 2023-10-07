package com.chicken.config;

import com.chicken.auditing.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 23-10-7
 * 어디터 설정 추가
 * */

@Configuration
@EnableJpaAuditing
public class Auditing {

    //어디터 어웨이를 불러온다
    @Bean
    public AuditorAware<String> auditorProvider(){
        //리턴 new 부분이 내가 설정하는 부분
        return new AuditorAwareImpl();
    }
}
