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
	          	<div class="">
	            	<div class="page-title">
	              		<div class="title_left">
	                		<h3>업체 주소록 <small style="color:lightgray">휴지통</small></h3>
	              		</div>
	            	</div>
	
	            	<div class="clearfix"></div>
	            	<br>
	                
					<c:remove var="groupNo"/>
	
	                <!-- 본문 테이블 -->
	                <div class="row" style="display: block;">
	                  	<div class="col-md-12 col-sm-12 ">
	                    	<div class="x_panel">                  
	                      		<div class="x_content">
	                        		<div class="row">
	                          			<div class="col-sm-12">
	                            			<div class="card-box table-responsive">
	    
	                              				<table id="datatable-checkbox" class="table table-bordered" style="width:100%" data-order="">
	                                				<thead>
	                                  					<tr>
						                                    <th ></th>
						                                    <th >이름</th>
						                                    <th >업체명</th>
						                                    <th >직급</th>
						                                    <th >그룹명</th>
						                                    <th >이메일</th>
						                                    <th >번호</th>
	                                  					</tr>
	                                				</thead>
	                                				<tbody>
	                                					<c:forEach items="${ list }" var="l">
	                                  					<tr>
	                                  						<td style="text-align: center;" onclick="event.cancelBubble=true">
	                                    						<input type="hidden" value="${ l.memberNo }">
						                                    	<input type="hidden" value="${ l.groupNo }">
	                                      						<div class="checkbox">
	                                        						<label>
	                                          							<input type="checkbox" class="flat" name="rCheck" value="${ l.addressNo }">
	                                        						</label>
	                                      						</div>
	                                    					</td>
						                                    <td>${ l.name }</td>
						                                    <td>${ l.bizName }</td>
						                                    <td>${ l.position }</td>
						                                    <td>${ l.groupName }</td>
						                                    <td>${ l.email }</td>
						                                    <td>${ l.phone }</td>
	                                  					</tr>
	                                  					</c:forEach>
	                                				</tbody>
	                              				</table>
	                              				<br>
	                              				<!-- 버튼들 -->
	                              				<div style="padding-left: 15px; padding-right: 15px;">
	                                				<div style="float:right;">
	                                  					<button class="btn btn-diy" style="color:white;" id="restoreBtn"><small>주소 복원</small></button>
	                                  					<button class="btn btn-diy" style="color:white;" id="deleteBtn"><small>영구 삭제</small></button>
	                                				</div>
	                              				</div>
	                            			</div>
	                          			</div>                      
	                        		</div>                    
	                      		</div>                  
	                    	</div>
						</div>
					</div>
				</div>
				<!-- 일반 주소록 끝 -->
            </div>       
    		<!-- /page content -->
		</div>
		
        <!-- footer content -->
        <jsp:include page="../common/footer.jsp"/>
    </div>
    
    <script>
    	$(function() {
    		// 복원 처리
        	$("#restoreBtn").click(function() {
        		
    			var restoreArr = new Array();
    	    		
    	    	$('input:checkbox[name=rCheck]:checked').each(function() {
    	    		restoreArr.push(this.value);
    	    	});
    	    		
    	    	if(restoreArr.length >= 1) {
    	    		var restoreNo = restoreArr.join(",");

    				$.ajax({
    					url:"restore.bzad",
    					type:"post",
    					data:{"no":restoreNo},
    					success:function(data){
    						location.href = "wasteBasket.bzad";
    					},error:function(){
    						console.log("ajax 통신 에러");
    					}
    				});
    	    	} else {
    	    		alertify.alert("복원할 건을 선택해주세요");
    	    	}
        	});
    		
        	// 영구 삭제
        	$("#deleteBtn").click(function() {
        		
    			var deleteArr = new Array();
    	    		
    	    	$('input:checkbox[name=rCheck]:checked').each(function() {
    	    		deleteArr.push(this.value);
    	    	});
    	    		
    	    	if(deleteArr.length >= 1) {
    	    		alertify.confirm("영구 삭제 시 복원되지 않습니다. 정말 삭제하시겠습니까?", function() {
    	    			var deleteNo = deleteArr.join(",");

        				$.ajax({
        					url:"purge.bzad",
        					type:"post",
        					data:{"no":deleteNo},
        					success:function(data){
        						location.href = "wasteBasket.bzad";
        					},error:function(){
        						console.log("ajax 통신 에러");
        					}
        				});
    	    		});    	    		
    	    	} else {
    	    		alertify.alert("삭제할 건을 선택해주세요");
    	    	}
        	});
    	});
    </script>
    
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
</body>
</html>