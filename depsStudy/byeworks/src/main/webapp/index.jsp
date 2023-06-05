<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Byeworks</title>
</head>
<body>
	
	<!-- 
	
	index.jsp 실행 시, main.do를 실행
	main.do 실행 전 MainInterceptor를 실행하여 
	세션에 loginUser가 담겨 있으면 main으로 넘어가고,
	loginUser가 담겨있지 않을 때 쿠키에 값이 저장되어 있다면 쿠키에서 값을 담아와 loginUser를 세션에 담아 main으로 넘어간다
	
	만약 둘 다 아닐 경우(=최초 실행 시, 로그인 자동 저장도 하지 않았을 때)
	login.do를 실행해 login 페이지로 이동한다
	
	 -->
	<jsp:forward page="/main.do"/>
	
</body>
</html>