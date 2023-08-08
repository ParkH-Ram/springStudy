package com.bochung.config;

import com.bochung.security.LoginFailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/** 스프링 시큐리티 23-8-4 **/

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // 스프링 시큐리티 필터
    // 기본적으로 체이닝 형태

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        //Cross-Site Request Forgery)'의 약자로 '사이트 간 요청 위조
        // csrf 는 사용자가 의도치 않는 요청을 수행하게 하는 취약점
        // default = enable
        // csrf 를 사용하지 않겠다.
        httpSecurity.csrf().disable();

        //로그인 설정
        httpSecurity.formLogin()
                .loginPage("/member/login") // 로그인 페이지
                .defaultSuccessUrl("/")  // 로그인 성공했을 때 실행할 url
                .usernameParameter("email")     // 각각의 유저를 유니크하게 구별할 id
                .failureHandler(loginFailHandler())// 로그인 실패 시 핸들러, 인터페이스 및 각종 설정 필요 , // 파라미터를 넘길  땐 이걸 넘긴다.
//                .failureUrl()  // 실패 했을 대 url
                .and()

                //로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))// 멤버의 로그아웃을 관리해줄 컨트롤의 url
                .deleteCookies("JSESSIONID") // 로그아웃 했을 때 지울 쿠기
                .invalidateHttpSession(true)  // // 로그아웃 이후 세션 전체 삭제 여부
                .clearAuthentication(true)    // 권한을 클리어 한다
                .logoutSuccessUrl("/member/login") // 로그아웃을 정상적으로 완료 했을 때 보낼 페이지
                ;

       /* // 권한 부분  // 가장 넓은 부분부터 좁은 부부 설정
        httpSecurity.authorizeRequests()  //메서드로 특정한 경로에 특정한 권한을 가진 사용자만 접근할 수 있도록 설정할 수 있다.
                .mvcMatchers("/board/info").hasAnyRole("USER","ADMIN") // 누구까지 이경로에 들어오게 할거냐
                                                                     // permitAll() 누구나, hasAnyRole() 로그인 한 누구나
                .anyRequest().permitAll() // 명시한 요청 외 나머지 요청들은 가능
                ;
*/
//        23-8-8 수정    // 가장 넓은 부분이 제일 위
        httpSecurity.authorizeRequests()
            .mvcMatchers("/").permitAll()
            .mvcMatchers("/member").permitAll()
            .mvcMatchers("/board/**").hasAnyRole("ADMIN", "USER")
            .anyRequest().permitAll()  // 위 명시한 조건 외 나머지 요청들은 허용
        ;
        return httpSecurity.build();
    }

    //23-8-8
    // 암호화를 위한 PasswordEncoder  빈등록
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFailHandler loginFailHandler () {
        return new LoginFailHandler();
    }
}
