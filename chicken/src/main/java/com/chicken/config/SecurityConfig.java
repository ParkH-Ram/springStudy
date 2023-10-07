package com.chicken.config;

import com.chicken.security.LoginFailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        //csrf 미사용 설정
        httpSecurity.csrf().disable();

        // 로그인 설정
        httpSecurity.formLogin()
            .loginPage("/chicken/login") // 로그인 페이지
            .defaultSuccessUrl("/") // 로그인  성공했을 때 실행할 url
            .usernameParameter("memberId") // 아이디로 유니크 설정
            .failureHandler(loginFailHandler()) // 로그인 실패 시 처리
            //                .failureUrl()  // 실패 했을 대 url
            .and()

            // 로그아웃 설정
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/chicken/logout")) // 멤버의 로그아웃을 관리해줄 컨트롤 url
            .deleteCookies("JSESSIONID") // 로그아웃 했을 때 지울 쿠키
            .invalidateHttpSession(true) // 로그아웃 이후 세션 전체 삭제 여부
            .clearAuthentication(true)  // 권한 클리어
            .logoutSuccessUrl("/chicken/login") // 로그아웃을 정상적으로 완료 했을 때 보낼 페이지
        ;

        // 가장 넓은 부분이 가장 위  위에서 아래로  범위 지정
        httpSecurity.authorizeRequests()
            .mvcMatchers("/").permitAll()
            .mvcMatchers("/chicken").permitAll() // 메인 페이지
            .mvcMatchers("/admin/**").hasAnyRole("ADMIN")
            .anyRequest().permitAll() // 위 명시한 조건 외 나머지 요청들은 허용
        ;

        return httpSecurity.build();
    }

    // 암호화를 위한 인코더 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFailHandler loginFailHandler () {
        return new LoginFailHandler();
    }
}
