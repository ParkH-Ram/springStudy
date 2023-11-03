package com.chicken.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증되지 않은 요청이 AJAX 일경우 403으로 응답 그외엔 파라메터 url로 전환
 * **/

public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public AjaxAwareAuthenticationEntryPoint(String loginUrl) {
        super(loginUrl);
    }

    //중간에서의 XMLHttpRequest의 문자열을 ajaxHeader와
    // 비교한 이유는 ajax는 XMLHttpRequest를 사용하기 때문.
    // 그래서 ajax인지 아닌지 isAjax라는 변수를 통해서 검사했다.
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {

        String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
        if (isAjax) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Ajax Request Denied (Session Expired)");
        } else {
            super.commence(request, response, authException);
        }
    }
}
