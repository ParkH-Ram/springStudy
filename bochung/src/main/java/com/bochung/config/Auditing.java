package com.bochung.config;


import com.bochung.auditing.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/** 23-8-9
 * 어디트 설정 추가 **/

@Configuration
@EnableJpaAuditing
public class Auditing {
    // 어디터 어웨어를 불러 온다
    @Bean                   // 이부분은 약속
    public AuditorAware<String> auditorProvider(){
                    // 리턴 new 부분은 약속이 아닌 내가 설정하는 부분
        return new AuditorAwareImpl();
    }

}