package com.chicken.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 23-10-7
 *  예상할 수 있는 모든  로그인 에러 처리
 * */

@Log4j2
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        log.info("login fail handler");

        //  에러 이유 메시지 삽입
        String errorMessage;
        if(e instanceof UsernameNotFoundException){   //  어떤 타입인지 확인해서 에러메시지 출력.
            errorMessage = "존재하지 않는 아이디 입니다.";
        } else if(e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException){
            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다.";
        }
        // 위에 명시한 에러
        else{
            errorMessage = "알 수 없는 이유로 로그인이 안되고 있습니다.\n 관리자에게 문의해주십시오.";
            errorMessage += e.getMessage();
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); //한글 인코딩 깨지는 문제 방지
        setDefaultFailureUrl("/chicken/login/error?error=true&exception=" + errorMessage);
        super.onAuthenticationFailure(request, response, e);
    }
}
