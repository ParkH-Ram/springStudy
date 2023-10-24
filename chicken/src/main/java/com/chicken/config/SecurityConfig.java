package com.chicken.config;

import com.chicken.security.LoginFailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        //로그인 설정
        httpSecurity.formLogin()
            .loginPage("/member/login")
                .defaultSuccessUrl("/")  // 로그인 성공했을 때 실행할 url
                .usernameParameter("memberId")
                .failureHandler(loginFailHandler())
//                .failureUrl()   // 실패 했을 때 url
            .and()


    //로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
            .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 제거
                .invalidateHttpSession(true) // 로그아웃 이후 세션 전체 삭제 여부
                .clearAuthentication(true)          // 권한 클리어
                .logoutSuccessUrl("/member/login");

    // 가장 넓은 부분이 제일 위
        httpSecurity.authorizeRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/member").permitAll()
                .mvcMatchers("/board/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().permitAll()  // 위 명시한 조건 외 나머지 요청들은 허용
    ;
        return httpSecurity.build();
}




    // 암호화를 위한 인코더 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //로그인 실패 핸들러
    @Bean
    public LoginFailHandler loginFailHandler () {
        return new LoginFailHandler();
    }
    
    /*//로그인 실패 핸들러
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler(){
        return new LoginFailHandler();
    }*/

}
