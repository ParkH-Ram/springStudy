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
        
        <!-- page content -->
        <div class="right_col" role="main">
          	<!-- top tiles -->
          	<div class="">
            	<div class="page-title">
              		<div class="title_left">
                		<h3>사내 주소록 <small style="color:lightgray">즐겨찾기</small></h3>
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
			                        	<div class="card-box table-responsive">
					                    	<table id="datatable-checkbox" class="table table-bordered" style="width:100%" data-order="">
					                        	<thead>
					                            	<tr>
			                                    		<th style="width:5px"></th>
			                                    		<th >이름</th>
			                                    		<th >부서</th>
			                                    		<th >이메일</th>
			                                    		<th >내선번호</th>
			                                  		</tr>
			                                	</thead>
			                                	<tbody>
			                                		<c:forEach items="${ markList }" var="m">
			                                  		<tr>
			                                    		<th style="text-align: center; cursor: pointer;">
			                                    			<input type="hidden" value="${ m.memberNo }">
			                                    			<input type="hidden" value="${ m.bookmark }">
			                                    			<c:if test="${ m.bookmark eq 'Y'}">
			                                      			<i class="fa fa-heart" style="font-size:1.5em; color:#2b90d9;"></i>
			                                    			</c:if>
			                                    			<c:if test="${ m.bookmark eq 'N'}">
			                                    			<i class="fa fa-heart" style="font-size:1.5em; color:#282c37"></i>
			                                    			</c:if>
			                                    		</th>
		                                    			<td>
			                                      			<div class="dropdown">
			                                        			<a class="dropdown-toggle" id="dropdownNote" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="cursor: pointer;">
			                                          				${ m.memberName }
			                                        			</a>
			                                        			<div class="dropdown-menu" aria-labelledby="dropdownNote">
			                                          				<a class="dropdown-item" onclick="$('#noteForm${ m.memberNo }').submit();">쪽지 보내기</a>
			                                        			</div>
			                                        			
			                                        			<form id="noteForm${ m.memberNo }" action="sendForm.nt" method="post">
			                                        				<input type="hidden" name="receiveNo" value="${ m.memberNo }">
			                                        				<input type="hidden" name="receiveName" value="${ m.memberName }">
			                                        			</form>
			                                      			</div>
			                                    		</td>
			                                    		<td>${ m.department }</td>
			                                    		<td>${ m.email }</td>
			                                    		<td>${ m.extension }</td>
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
              	<!-- 즐겨찾기 끝 -->
           	</div>
            <!-- 탭 끝 -->
		</div>
        <!-- /page content -->

        <!-- footer content -->
        <jsp:include page="../common/footer.jsp"/>
        
      </div>
    </div>
    
    <script>
    	var myNo;
    	var memberNo;
    	var bookmark;
    	
    	var tr;
    	
    	$(function() {    		
    		$("#datatable-checkbox>tbody>tr").click(function() {
    			tr = $(this);
    		});
    		
    		// 즐겨찾기 테이블
    		$("#datatable-checkbox>tbody>tr>th").click(function() {
    			// 누르면 누른 사람의 정보, 누른 테이블의 행의 no 받아와서
    			// 에이작스 실행...
    			myNo = ${ loginUser.memberNo };			// 즐겨찾기 한 직원 번호
    			memberNo = $(this).children().eq(0);	// 즐겨찾기 된 직원 번호
    			bookmark = $(this).children().eq(1);	// 북마크 여부
    			heart = $(this).children().eq(2);
    			
    			// 북마크가 되어있다면
    			if(bookmark.val() == 'Y') {
    				$.ajax({
    					url:"removeBM.ad",
    					data:{favMemberNo:myNo,
  						  	  memberNo:memberNo.val()},
      					type:"post",
      					success:function(result) {
    						bookmark.val('N');
    						heart.css("color", "#282c37");
    						// 하트가 색이 바뀌는 게 아니라 해당 테이블이 지워져야 하는데?
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