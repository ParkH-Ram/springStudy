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

	<!-- menubar 위에 있던 파일의 경우에 css가 적용되지 않아 두 개의 css 따로 적용  -->
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/basic/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/resources/css/custom.min.css" rel="stylesheet">
 	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
 	<title>Byeworks</title>
 	
 	<style>
    	.form-kdh{
        border: 1px solid white;   
        font-family :'FontAwesome';
        font-size: inherit; 
        padding-top:2.5%;
       background:#e9ecef;
        }
        .form-kdh[readonly]{
        background:white;
        }
    
    </style>
</head>
<body class="nav-md">
	 <c:if test="${ !empty sessionScope.myMsg }">
			<script>
			alertify.alert("${sessionScope.myMsg}");	
			</script>
			<c:remove var="myMsg" scope="session"/>
		</c:if>

	<div class="container body">
    	<div class="main_container">
        
        <!-- 메뉴바 -->
        <jsp:include page="../common/menubar.jsp"/>
		
		  <!-- page content -->
        <div class="right_col" role="main">
          <!-- top tiles -->
          
        <div class="page-title">
	   		<div class="title_left">
	     		<h3>인사 관리 <small style="color:lightgray">내 정보 관리</small></h3>
	   		</div>
        </div>  
        <div class="clearfix"></div>
         <br>
       	<div class="row" style="display: inline-block;" >
            <div class="col-md-12 ">
              <div class="x_panel">
        
           	<br>
                <div class="x_content">
                  <br>
                  <form class="form-horizontal form-label-left" action="myUpdate.me" method="post">
                  	<input type="hidden" value="${loginUser.memberId }" name="memberId">
                  
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">프로필사진</label>
                      <div class="col-md-9 col-sm-9 ">
                           <span class="image"><img src="${pageContext.request.contextPath}/resources/profile_modify/${loginUser.profileModify } " style="width:30%; margin-bottom:3%;" /></span>
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">이름</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh" readonly="readonly" value="${ loginUser.memberName }">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">소속</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh" readonly="readonly" value="${loginUser.department }">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">직위</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh" readonly="readonly" value="${loginUser.position }">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">사번</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh" readonly="readonly" value="${loginUser.empNo }">
                      </div>
                    </div>
                    
                    <div class="form-group row ">
                      <label class="control-label col-md-3 col-sm-3 ">사내 전화</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh" readonly="readonly" value="${loginUser.extension }">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">휴대전화</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh" value="${loginUser.phone }" id="phone" name="phone">
                      </div>
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">개인이메일</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh" value="${loginUser.email }" id="email" name="email">
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">입사일</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh" readonly="readonly" value="${loginUser.enrollday }">
                      </div>
                    </div>
                    <div class="form-group row ">
                      <label class="control-label col-md-3 col-sm-3 ">생년월일</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh"  readonly="readonly" value="${loginUser.birth }">
                      </div>
                    </div>
                    <div class="form-group row ">
                      <label class="control-label col-md-3 col-sm-3 ">주소</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-control form-kdh"  value="${loginUser.address }" id="address" name="address">
                      </div>
                    </div>
                   
                    
                    <div class="ln_solid"></div>
                    <div class="form-group">
                      <div class="col-md-9 col-sm-9  offset-md-3">
                        <button type="submit" class="btn btn-diy" style="color:white;">수정</button>
                        <button type="button" onclick="location.href='resetForm.me'" class="btn btn-diy" style="color:white;">비밀번호  재설정</button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>

        <!-- /page content -->
        <!-- footer content -->
        <jsp:include page="../common/footer.jsp"/>
        
      </div>
    </div>
      </div>
    </div>
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
</body>
</html>