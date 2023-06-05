package com.dadada.byeworks.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.dadada.byeworks.member.model.service.MemberService;
import com.dadada.byeworks.member.model.vo.Member;

public class Interceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private MemberService mService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		// 로그인 된 세션이 없는 경우
		if(session.getAttribute("loginUser") == null) {
			
			// 쿠키에 저장된 데이터가 있는지 조회
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			// 만약 쿠키가 존재하는 경우
			if(loginCookie != null) {
				// loginCookie에서 저장된 sessionId 값을 가져온다
				String sessionId = loginCookie.getValue();
				
				// sessionId를 전달해 member 값을 가져온다
				Member m = mService.checkMemberWithSessionKey(sessionId);
				
				// 만약 sessionId와 일치하는 사용자가 있다면
				if(m != null) {
					// 세션 생성
					session.setAttribute("loginUser", m);
					return true;
				}
			}
			
			// 쿠키도 없고 로그인 정보가 세션에 담겨있지도 않은 경우
			session.setAttribute("msg", "로그인 시 사용 가능합니다.");
			response.sendRedirect(request.getContextPath());
			
			return false;
		} else { 
			return true;
		}
	}
}
