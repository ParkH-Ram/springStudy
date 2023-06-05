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
			alertify.alert("${sessionScope.msg}"); 
		</script>
		<c:remove var="msg" scope="session"/>
	</c:if>
	
	<div>
		<div class="login_wrapper">
        	<div class="animate form login_form">

          		<section class="login_content" style="padding-top: 1%;">
            		<form id="checkPwdForm" action="resetPwd.me" method="post">
              			<h1>비밀번호 재설정</h1>
						
              			<div>
                			<input type="password" id="pwd" name="memberPwd" class="form-control" placeholder="비밀번호" required="" style="margin-bottom:0" />
                			<div id="checkPwd" style="display:none; font-size:0.8em; float:left; margin-bottom:3%"></div><br>
              			</div>
              			<div>
                			<input type="password" id="pwd2" name="pwd2" class="form-control" placeholder="비밀번호 확인" required="" style="margin-bottom:0"/>
                			<div id="checkPwdAgain" style="display:none; font-size:0.8em; float:left; margin-bottom:3%"></div><br>
              			</div>

              			<div>
                			<button class="btn btn-diy" id="resetPwd" style="width:100%;color:white;font-size:small;" type="submit" disabled>비밀번호 재설정 </button>
              			</div>

              			<div class="clearfix"></div>

              			<div class="separator">
							비밀번호는 8자 이상으로, <br>숫자/대문자/소문자/특수문자를 포함해야 합니다.
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
		var checkValidate = 0; // 유효성 통과하면 1 아니면 0
		var checkPwdSame = 0; // 비밀번호 동일하면 1 아니면 0
		
    	$(function () {
        	$('[data-toggle="tooltip"]').tooltip()
        
        	var $pwdInput = $("#checkPwdForm input[name=memberPwd]"); 	// 비밀번호
        	var $chkInput = $("#checkPwdForm input[name=pwd2]");		// 비밀번호 체크
        
        	// 비밀번호 유효성 검사
        	$pwdInput.keyup(function() {
        		console.log($pwdInput.val());
            	// 8글자 이상일 때
            	if($pwdInput.val().length >= 8) {
            		if(chkPwd($pwdInput.val())) { // 비밀번호 유효성 검사 통과
            			// 비밀번호 체크와 동일하면 버튼 활성화
            			pwdCheckValidate(2);
            		} else {
            			pwdCheckValidate(1);
            		}
            	} else {
            		pwdCheckValidate(0);
            	}
            });
        	
        	// 비밀번호 동일 검사
        	$chkInput.keyup(function() {
        		if($chkInput.val().length >= 1) { // 한 글자만 입력해도
        			if($chkInput.val() == $pwdInput.val()) { // 입력된 비번이 동일하면
	        			pwdCheckValidate(5);
        			} else {
        				pwdCheckValidate(4);
        			}
        		} else {
        			pwdCheckValidate(3);
        		}
        	});
        
        	// 검사 후 UI 
        	function pwdCheckValidate(num) {
        		if(num == 0) { // 체크할 비번조차 아니면
        			$("#checkPwd").hide();
        			checkValidate = 0;
        		} else if(num == 1) { // 8자리 이상일 때 조건에 부합하지 않으면
	        		$("#checkPwd").css("color", "red").text("비밀번호 수정 조건을 확인해주세요");
	        		$("#checkPwd").show();
        			checkValidate = 0;
	        	} else if(num == 2) { // 8자리 이상이고 조건과 부합할 때
	        		$("#checkPwd").css("color", "blue").text("비밀번호 조건을 만족합니다");
	        		$("#checkPwd").show();
        			checkValidate = 1;
	        	} else if(num == 3) { // 비밀번호 확인란에 입력된 글자가 0일 때
	        		$("#checkPwdAgain").hide();
	        		checkPwdSame = 0;
	        	} else if(num == 4) { // 비밀번호가 동일하지 않으면
	        		$("#checkPwdAgain").css("color", "red").text("비밀번호가 동일하지 않습니다");
	        		$("#checkPwdAgain").show();
	        		checkPwdSame = 0;
	        	} else if(num == 5) {
	        		$("#checkPwdAgain").css("color", "blue").text("비밀번호가 동일합니다");
	        		$("#checkPwdAgain").show();
	        		checkPwdSame = 1;
	        	}
        		attrDisabled();
	        }
        
	        // 유효성 검사하는 함수
	        function chkPwd(inputPwd) {
	      	  var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	      	  
	      	  if(reg.test(inputPwd) === false) {
	      		  return false;
	      	  } else {
	      		  return true;
	      	  }
	        }        
	        
	        // 버튼 disabled 처리 함수
	        function attrDisabled() {
	        	if(checkValidate == 1 && checkPwdSame == 1) { // 유효성 O, 동일 O
	        		$("#resetPwd").removeAttr("disabled");	        		
	        	} else { // 나머지
        			$("#resetPwd").attr("disabled", true);	        		
	        	}
	        }
      	});
      
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
