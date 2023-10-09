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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        // 로그인 설정
        httpSecurity.formLogin()
            .loginPage("/login") // 로그인 페이지

            .usernameParameter("memberId") // 아이디로 유니크 설정
            .failureHandler(customAuthenticationFailureHandler()) // 로그인 실패 시 처리
            .failureUrl("/login/error")  // 실패 했을 대 url
            .defaultSuccessUrl("/chicken/main") // 로그인  성공했을 때 실행할 url
            .and()

            // 로그아웃 설정
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 멤버의 로그아웃을 관리해줄 컨트롤 url
            .deleteCookies("JSESSIONID") // 로그아웃 했을 때 지울 쿠키
            .invalidateHttpSession(true) // 로그아웃 이후 세션 전체 삭제 여부
            .clearAuthentication(true)  // 권한 클리어
            .logoutSuccessUrl("/login") // 로그아웃을 정상적으로 완료 했을 때 보낼 페이지
        ;

        // 가장 넓은 부분이 가장 위  위에서 아래로  범위 지정
        httpSecurity.authorizeRequests()
            .mvcMatchers("/login").permitAll()
            .mvcMatchers("/chicken").permitAll() // 메인 페이지
            .mvcMatchers("/admin/**").hasAnyRole("ADMIN")
            .anyRequest().authenticated() // 위 명시한 조건 외 나머지 요청들은 허용
        ;

        // 로그인 안했을 때 무조건 로그인 화면으로 보내기 위한 시큐리티
        httpSecurity.exceptionHandling()
            .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

    }

    // static 디렉토리 하위 파일은 인증을 무시함
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/font/**", "/img/**", "/js/**","/editor/**", "/api/**");
    }

    // 암호화를 위한 인코더 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //로그인 실패 핸들러
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler(){
        return new LoginFailHandler();
    }

}
