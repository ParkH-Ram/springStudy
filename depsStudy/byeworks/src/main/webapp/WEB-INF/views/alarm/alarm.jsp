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
    
    <!-- alertifyJs -->
	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
 
 	<title>Byeworks</title>
</head>
<body class="nav-md">
	<div class="container body">
    	<div class="main_container">
        
	        <!-- 메뉴바 -->
	        <jsp:include page="../common/menubar.jsp"/>
		
			<!-- page content -->
        	<div class="right_col" role="main">
          		<!-- top tiles -->
          		<div class="row" style="display: block;">
            		<div class="col-md-12 col-sm-12">
              			<div class="" role="tabpanel" data-example-id="togglable-tabs">
                			<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                  				<li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">쪽지</a></li>
                			</ul>
                
                			<!-- 쪽지 -->
                			<%-- 삭제한 쪽지는 보이면 안 된다 알지 --%>
                			<div id="myTabContent" class="tab-content">
                  				<div role="tabpanel" class="tab-pane active " id="tab_content1" aria-labelledby="home-tab">
                    				<div class="well" style="overflow: auto; padding:0.5%">
                    					<button style="float: right; margin:0" class="btn btn-danger" id="deleteAll">전체 삭제하기</button>
                    				</div>

                    				<!-- start recent activity -->
                    				<ul class="messages">
                    					<c:forEach items="${ aList }" var="a">
                      					<li>
                        					<img src="${pageContext.request.contextPath}/resources/profile_modify/${ a.profileModify }" class="avatar" alt="Avatar">
                        					<div class="message_date">
                          						<h3 class="date text-info">${ a.sendDate }</h3>
                          						<p class="month">${ a.sendMonth }</p>
                        					</div>
                        					
                        					<div class="message_wrapper">
                          						<h5 class="heading"><strong>${ a.sendName }</strong></h5>
                          						<blockquote class="message"><a href="detail.nt?noteNo=${ a.groupNo }">쪽지가 도착했습니다 : ${ a.noteTitle }</a></blockquote>
                          						<br />
                          						<p class="url" style="margin:0">
                            						<span class="fs1 text-info" aria-hidden="true" data-icon=""></span>
                            						<label style="cursor:pointer" onclick="deleteOne(${ a.alarmNo })">알람 지우기 </label>
                            						
                            						<form id="delete${ a.alarmNo }" action="deleteOne.al" method="post">
                            							<input type="hidden" name="alarmNo" value="${ a.alarmNo }"> 
                            						</form>
                          						</p>
                        					</div>
                      					</li>
                      					</c:forEach>
                      					<c:if test="${ empty aList }">
                      						<label style="text-align: center;">알람이 없습니다.</label>
                      					</c:if>
                    				</ul>
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
    	$(function() {
    		// 전체 삭제
    		$("#deleteAll").click(function() {
    			alertify.confirm("전체 삭제 시 복원되지 않습니다. 정말 삭제하시겠습니까?", function() {
    				location.href = "deleteAll.al";
    			});
    		});
    	});
    	
		function deleteOne(value) {
			alertify.confirm("삭제 시 복원되지 않습니다. 정말 삭제하시겠습니까?", function() {
				var formName = "#delete" + value;
				$(formName).submit();
			});
		}
    </script>
    
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
</body>
</html>