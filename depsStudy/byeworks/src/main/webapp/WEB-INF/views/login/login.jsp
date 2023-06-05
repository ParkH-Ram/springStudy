<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/LogoExample.png" type="image/png" />
    <title>ByeWorks</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/basic/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/resources/css/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="${pageContext.request.contextPath}/resources/css/animate/animate.css/animate.min.css" rel="stylesheet">
    
    <!-- 체크박스 -->
    <link href="${pageContext.request.contextPath}/resources/css/iCheck/blue.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/resources/css/custom.min.css" rel="stylesheet">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
    <!-- alertifyJs -->
	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
</head>

	<body class="login">
  
		<c:if test="${ !empty sessionScope.msg }">
			<script>
				alertify.alert("${sessionScope.msg}"); // alert("회원가입성공");
			</script>
			<c:remove var="msg" scope="session"/>
		</c:if>
	
    <div>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form action="login.me" method="post">
              <h1>로그인</h1>
              <div>
                <input type="text" id="memberId" name="memberId" class="form-control" placeholder="아이디" required="" />
              </div>
              <div>
                <input type="password" id="memberPwd" name="memberPwd" class="form-control" placeholder="비밀번호" required="" />
              </div>
              <div>
                <button class="btn btn-diy" style="width:100%;color:white;font-size:small;" type="submit">로그인</button>
                <div class="checkbox" style="margin-top:5%;">
                  <label data-toggle="tooltip" data-placement="bottom" title="개인PC에서만 사용하세요">
                    <input type="checkbox" class="flat" id="useCookie" name="useCookie">&nbsp;&nbsp;&nbsp;로그인 상태 유지
                  </label>
                </div>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <a href="goFind.me" style="margin:0;">아이디/비밀번호 찾기</a>

                <div class="clearfix"></div>
                <br />

                <jsp:include page="loginFooter.jsp"/>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>

    <script>
      $(function () {
        $('[data-toggle="tooltip"]').tooltip()
      })
    </script>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/resources/js/iCheck/icheck.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
  </body>
</html>
