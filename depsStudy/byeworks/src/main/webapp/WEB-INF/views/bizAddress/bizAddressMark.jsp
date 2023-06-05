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
	                		<h3>업체 주소록 <small style="color:lightgray">즐겨찾기</small></h3>
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
	                                    					<th style="text-align: center; cursor: pointer;" name="heartBtn">
				                                    			<input type="hidden" value="${ l.addressNo }">
				                                    			<input type="hidden" value="${ l.bookmark }">
				                                    			<c:if test="${ l.bookmark eq 'Y'}">
				                                      			<i class="fa fa-heart" style="font-size:1.5em; color:#2b90d9;"></i>
				                                    			</c:if>
				                                    			<c:if test="${ l.bookmark eq 'N'}">
				                                    			<i class="fa fa-heart" style="font-size:1.5em; color:#282c37"></i>
				                                    			</c:if>
			                                    			</th>
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
		var tr;
		
		$(function() {
			var myNo;
	    	var addrNo;
	    	var bookmark;
	    	
	    	$("#datatable-checkbox>tbody>tr").click(function() {
    			tr = $(this);
    		});
	    	
			// 즐겨찾기 처리
	    	$("#datatable-checkbox>tbody>tr th").click(function(){
	    		// 누르면 누른 사람의 정보를 받아온다
	    		// 받아오는 정보: groupNo, bookmark
	    		myNo = ${ loginUser.memberNo };		// 내 정보
	    		addrNo = $(this).children().eq(0);	// 주소 담는 곳
	    		bookmark = $(this).children().eq(1);// 북마크 여부 담는 곳
	    		heart = $(this).children().eq(2);	// 하트모양 아이콘
	    		
	    		// 북마크가 되어있다면
				if(bookmark.val() == 'Y') {
					$.ajax({
						url:"removeBM.bzad",
						data:{addressNo:addrNo.val(),
							  memberNo:myNo},
	  					type:"post",
	  					success:function(result) {
							tr.css("display", "none");
						},error:function() {
							console.log("ajax 통신 실패");
						}
					});
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