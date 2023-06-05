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
 
 	<title>Byeworks</title>
</head>
<body class="nav-md">
	<div class="container body">
    	<div class="main_container">
        
        <!-- 메뉴바 -->
        <jsp:include page="../common/menubar.jsp"/>
			<div class="right_col" role="main">
          	<!-- top tiles -->
          		<div class="">
            		<div class="page-title">
              			<div class="title_left">
                			<h3>쪽지 보기 </h3>
              			</div>
            		</div>

            		<div class="clearfix"></div>
            		<br>

            		<!-- 본문 테이블 -->  
            		<div class="row" style="display: block;">
              			<div class="col-md-12 col-sm-12 ">
                			<div class="x_panel">                  
                  				<div class="x_content">
                    				<div class="row">
                      					<div class="col-sm-12">
                        					<form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                          						<div class="item form-group">
                            						<label class="col-form-label col-md-2 col-sm-2 label-align" for="first-name">제목</label>
                            						<div class="col-md-6 col-sm-6 ">
                              							<input type="text" id="first-name" required="required" class="form-control" readonly value="${ n.noteTitle }">
                            						</div>
                          						</div>
                          						<div class="item form-group">
                            						<label class="col-form-label col-md-2 col-sm-2 label-align" for="last-name">보낸 사람</label>
                            						<div class="col-md-6 col-sm-6">
                               							<input type="text" id="last-name" name="last-name" required="required" class="form-control" readonly value="${ n.sendName }">
                            						</div>
                          						</div>
                          						<div class="item form-group">
                          						
                            						<label class="col-form-label col-md-2 col-sm-2 label-align" for="last-name">받는 사람</label>
                            						<div class="col-md-6 col-sm-6">
                               							<input type="text" id="last-name" name="last-name" required="required" class="form-control" readonly value="${ n.receiveName }">
                            						</div>
                          						</div>

                          						<div class="x_panel">
                            						<div class="x_content">
                              
                              							<textarea style="width:100%;height:250px;resize:none;" readonly>${ n.noteContent }</textarea>

                              							<div class="ln_solid">
                                							<br>
                                							<div style="padding-left: 15px; padding-right: 15px;">
                                  								<div style="float:right;">
                                    								<button class="btn btn-secondary" type="button" onclick="location.href='selectList.nt'"><small>목록으로</small></button>
                                    								<c:if test="${ loginUser.memberNo ne n.sendNo }">
                                    									<button class="btn btn-diy" style="color:white" type="button" onclick="$('#replyForm').submit();"><small>답장 하기</small></button>
                                    								</c:if>
                                  								</div>
                                							</div>
                              							</div>
                            						</div>
                          						</div>  
                        					</form>
                        					<!-- 답장하기를 위한 폼 -->
                        					<form action="sendForm.nt" method="post" id="replyForm">
           										<input type="hidden" name="receiveNo" value="${ n.sendNo }">
           										<input type="hidden" name="receiveName" value="${ n.sendName }">
           										<input type="hidden" name="receiveTitle" value="${ n.noteTitle }">
           										<input type="hidden" name="receiveId" value="${ n.sendId }">
           									</form>
                      					</div>                      
                    				</div>                    
                  				</div>                  
                			</div>
              			</div>
            		</div>
          		</div>
        	</div>
        	<!-- footer content -->
        	<jsp:include page="../common/footer.jsp"/>
      	</div>
    </div>
    
    <script>
    </script>
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
</body>
</html>