<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
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

				<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
            		<li class="nav-item">
              			<a class="nav-link active" id="findId-tab" data-toggle="tab" href="#findId" role="tab" aria-controls="findId" aria-selected="true">아이디</a>
            		</li>
            		<li class="nav-item">
              			<a class="nav-link" id="findPwd-tab" data-toggle="tab" href="#findPwd" role="tab" aria-controls="findPwd" aria-selected="false">비밀번호</a>
            		</li>
          		</ul>

          		<div class="tab-content" id="myTabContent">
          			<!-- 아이디 찾는 탭 -->
            		<div class="tab-pane fade show active" id="findId" role="tabpanel" aria-labelledby="findId-tab">

              			<section class="login_content" style="padding-top: 1%;">
                			<form action="findId.me" method="post">
                  				<h1>아이디 찾기</h1>
                  				<div>
                    				<input type="text" id="memberName" name="memberName" class="form-control" placeholder="이름" required="" />
                  				</div>
			                  	<div>
			                    	<input type="email" id="email" name="email" class="form-control" placeholder="이메일" required="" />
			                  	</div>
                  				<div>
				                    <button class="btn btn-diy" style="width:100%;color:white;font-size:small;" type="submit">아이디 찾기</button>
                  				</div>
    
                  				<div class="clearfix"></div>
    
                  				<div class="separator">
                  					입력하신 이메일로 아이디가 전달 됩니다.    
	                    			<div class="clearfix"></div>
	                    			<br />
	    
	                    			<jsp:include page="loginFooter.jsp"/>
                  				</div>
                			</form>
              			</section>
					</div>
            		<!-- 아이디 탭 끝 -->
            		<!-- 비밀번호 찾는 탭 -->
            		<div class="tab-pane fade" id="findPwd" role="tabpanel" aria-labelledby="findPwd-tab">              
              
              			<section class="login_content" style="padding-top: 1%;">
                			<form action="resetForm.me" method="post" onsubmit="return check();">
                  				<h1>비밀번호 찾기</h1>
                  				<div>
                    				<input type="text" id="userId" name="userId" class="form-control" placeholder="아이디" required="" />
                  				</div>
                  				<div>
                    				<input type="email" id="userEmail" name="userEmail" class="form-control" placeholder="이메일" required="" />
                  				</div>
                  
                  				<div>
                    				<input type="text" id="code" class="form-control" placeholder="인증번호를 입력하세요" style="float:left; width:63%;" />
                    				<button class="btn btn-warning" id="codeBtn" style="color:white;font-size:small;float:right;" type="button">인증번호 받기</button>
                  				</div>

                  				<div>
                    				<button class="btn btn-diy" style="width:100%;color:white;font-size:small;" type="submit">인증하기</button>
                  				</div>

                  				<div class="separator">
                    				인증 후 비밀번호를 재설정 합니다
                    				<div class=""></div>
    
                    				<div class="clearfix"></div>
                    				<br />
    
                    				<jsp:include page="loginFooter.jsp"/>
                  				</div>
                			</form>
              			</section>
            		</div>
            		<!-- 비밀번호 탭 끝 -->
          		</div>
        	</div>
      	</div>
	</div>

	<script>
    	var ran; // 인증번호 글로벌로
		$(function () {
			$('[data-toggle="tooltip"]').tooltip();
			
			$("#codeBtn").click(function() {
        		if($("#userId").val().trim().length != 0) {
        			if($("#userEmail").val().trim().length != 0) {
        				// 여기서 인증번호를 생성해서 넘겨주기
        				ran = Math.floor(Math.random() * 1000000);
        			
	        			$.ajax({
	        				url:"sendSecret.me",
	        				data:{userId:$("#userId").val(),
	        					  userEmail:$("#userEmail").val(),
	        					  ranCode:ran},
	        				type:"post",
	        				success:function(result) {
		        				if(result == "1") {
			        				alertify.alert("인증번호를 발송했습니다. 메일을 확인해주세요.");        					
		        				} else if(result == "2") {
		        					alertify.alert("일치하는 계정이 없습니다.");      
		        				}
		        			}, error:function() {
		        				alertify.alert("통신에 실패했습니다. 다시 요청해주세요.");
		        			}
	        			});
        			} else {
        				alertify.alert("이메일을 입력해주세요.");
        			}
        		} else {
        			alertify.alert("아이디를 입력해주세요.");
        		}
        	});
		});
      
		function check() {
		  	if($("#code").val().trim().length != 0) {    		  
			  	if($("#code").val().trim() == ran) {
				  	return true;
			  	} else {
				  	alertify.alert("인증코드가 일치하지 않습니다. 다시 입력해주세요.");   
				  	return false;
			  	}
		  	} else {
			  	alertify.alert("아이디를 입력해주세요.");
			  	return false;
		  	}
		  	return false;
		}
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