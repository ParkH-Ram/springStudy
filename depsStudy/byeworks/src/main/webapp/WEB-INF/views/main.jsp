<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Calendar cal = Calendar.getInstance();
	int month = cal.get(cal.MONTH) + 1;
%>
<!DOCTYPE html>
<html lang="en">
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
    <!-- alertifyJS 스크립트 -->
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
 	<title>Byeworks</title>
 	
 	<style>
 		table>tbody>tr:hover{
 			cursor:pointer;
 		}
 	</style>
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
        
	        <!-- 메뉴바 -->
	        <jsp:include page="common/menubar.jsp"/>

        	<div class="right_col" role="main">
          	
	          	<!-- top tiles -->
	          	<div class="well" style="overflow: auto">
	         
	    
	            	<button class="btn btn-diy" style="color:white" type="button" id="commuteWorkBtn" onclick="">출근하기</button>
	            
	       
	          	</div>
	          <script>
	          var checkResult = 0;
	          
	          	// 변수 담아주는 함수
	          	function test(checkCommute){
	          			var sysdate= new Date(); 
	          			
	          			var url='';
          				var type='get';
          				var data={
          						"sysdate" : sysdate,
          						"commuteMember" : ${loginUser.memberNo}
          				}
          				var str='';
          				
          				if(checkCommute==0){
          					
          					url="commuteWorkTime.do";
          					str="퇴근하기";
          					alertify.alert("출근 완료");
          				}else{
          				
          					url="commuteLeavedTime.do";
          					str="출근하기"
          					alertify.alert("퇴근 완료");
          				}
          	
          		// 출퇴근 result 값에 따른 에이작스 실행		
          				$.ajax({
								url:url,
								type:type,
								data: data,
								success:function(){
								
								    $("#commuteWorkBtn").html(str);
								
								    
								},
								error: function(){
									console.log("ajax 통신 실패");
								}

    					});
	          		}
	          	
	          	$(function(){
	          		
			  		// 존재여부 확인하는거 함수 실행
			  		checkTime();
			  		function checkTime(){
			  			$.ajax({
          					url:"wheterCommute.co",
          				   	async: false, // 동기화 방식으로 바꿔주는 것
          					success:function(result){
		          			
		          				
		          				checkResult = result;
		          				
		          				
		          				if(result == 1){
			          				$("#commuteWorkBtn").html("퇴근하기");
		          				}else if(result == 0){
		          					$("#commuteWorkBtn").html("출근하기");
		          				}
		          			
          					}
          					
          				}); 
			  			
			  		};
	          		// 버튼 클릭시 출근 여부 확인하는 값
          			$("#commuteWorkBtn").click(function(){
	          				checkTime();
	          				console.log("button : " + checkResult);
	          				test(checkResult); // test 함수에 result 값을 보내서 값 삽입
    				});
          			
			  		// 메인 확인시 출퇴근 보여지는거
			  	
          		
	          	});
	          			
	          		
	          
	          </script>
				
          		<div class="row" style="display: block;">
					<!-- 왼쪽 영역 -->
            		<div class="col-md-6 col-sm-6">
            			<div class="row" style="display:block;">
            				<div class="x_panel">
            					<!-- 공지사항 보여주는 부분 -->
                				<div class="x_title">
                  					<h2>공지사항</h2>
                  					<div class="clearfix"></div>
                				</div>
	                			<div class="x_content">
	                  				<table class="table" id="noticeTable">
	                    				<thead>
	                      					<tr>
	                        					<th></th>
	                        					<th>제목</th>
	                      					</tr>
	                    				</thead>
	                    				<tbody>
	                    					<c:forEach items="${ nList }" var="n" varStatus="status">
		                      				<tr>
					                        	<th scope="row">
					                        		<input type="hidden" value="${ n.noticeNo }">
					                        			${ status.count }
					                        		<form id="goNotice${ n.noticeNo }" action="detail.not" method="post">
					                        			<input type="hidden" value="${ n.noticeNo }" name="nno">
					                        		</form>
					                        	</th>
					                        	<td>${ n.noticeTitle }</td>
		                      				</tr>
		                      				</c:forEach>
		                    			</tbody>
		                  			</table>
		                		</div>
							</div>
							<div class="x_panel">
                				<div class="x_title" style="margin:0">
                  					<h2>진행 중인 프로젝트</h2>
                  					<div class="clearfix"></div>
                				</div>
	                			<div class="x_content">
                    				<div class="dashboard-widget-content">
                      					 <ul class="quick-list" style="width:60%">
					                        <li><i class="fa fa-bars"></i>프로젝트명: ${ projectMain.proTitle }</li>
					                        <li><i class="fa fa-calendar"></i>프로젝트 전체 기간 : ${ projectMain.proStart } ~ ${ projectMain.proEnd }</li>
					                        <li><i class="fa fa-desktop"></i>진행 중인 작업: ${ projectMain.taskTitle }</li>
					                        <li><i class="fa fa-line-chart"></i>작업 기간: ${ projectMain.taskStart } ~ ${ projectMain.taskEnd }</li>
                      					</ul>
										
                      					<%-- <div class="sidebar-widget" style="width:40%">
                        					<h4>진행률</h4>
                        					<canvas width="150" height="80" id="chart_gauge_01" class="" style="width: 160px; height: 100px;"></canvas>
                        					<div class="goal-wrapper">
					                          <span id="gauge-text" class="gauge-value gauge-chart pull-left" value="100">0</span>
					                          <span class="gauge-value pull-left">%</span>
					                          <span id="goal-text" class="goal-value pull-right">100%</span>
					                        </div>
                      					</div>  --%>
                    				</div>
		                		</div>
							</div>
            			</div>
					</div>
					
					<!-- 개인 일정 (오른쪽) -->
					<div class="col-md-6 col-sm-6">
						<div class="x_panel">
	                		<div class="x_title">
	                  			<h2>
	                  				<%=month %>월 일정 
	                  				<a href="my.sc" style="float:right"><small>일정 더 보러가기</small></a>
	                  			</h2>
	                  			<div class="clearfix"></div>
	                		</div>
	                		<div class="x_content">
                    		<!-- start accordion -->
                    			<div class="accordion" id="accordion" role="tablist" aria-multiselectable="true">
                    				<c:if test="${ ! empty sList }">
	                    				<c:forEach items="${ sList }" var="s">
	                      				<div class="panel">
	                        				<a class="panel-heading" style="background:${s.color}" role="tab" id="heading${ s.schNo }" data-toggle="collapse" data-parent="#accordion" href="#collapse${ s.schNo }" aria-expanded="true" aria-controls="collapse${ s.schNo }">
	                          					<h4 class="panel-title" style="color:#282c37">${ s.title }</h4>
	                        				</a>
	                        				<div id="collapse${ s.schNo }" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading${ s.schNo }">
	                        					<div style="margin-left:3%; margin-right:3%; margin-top:3%">
		                          					<div class="form-group row">
							                        	<label class="col-form-label col-md-2 col-sm-2">일자</label>
							                        	<div class="col-md-10 col-sm-10 ">
							                          		<input type="text" class="form-control" placeholder="일자 없음" value="${ s.beginDate } ~ ${ s.endDate }">
							                        	</div>
							                      	</div>
							                      	<div class="form-group row">
							                        	<label class="col-form-label col-md-2 col-sm-2 ">위치</label>
							                        	<div class="col-md-10 col-sm-10 ">
							                          		<input type="text" class="form-control" placeholder="위치 없음" value="${ s.location }">
							                        	</div>
							                      	</div>
							                      	<div class="form-group row">
							                        	<label class="col-form-label col-md-2 col-sm-2 ">메모</label>
							                        	<div class="col-md-10 col-sm-10 ">
							                          		<input type="text" class="form-control" placeholder="메모 없음" value="${ s.note }">
							                        	</div>
							                      	</div>
						                      	</div>
	                        				</div>
	                      				</div>
	                      				</c:forEach>
                      				</c:if>
                      				<c:if test="${ empty sList }">
                      					등록된 일정이 없습니다.
                      				</c:if>
                    			</div>
                    			<!-- end of accordion -->
                  			</div>
	              		</div>
					</div>
				</div>
			</div>
	
	        <!-- footer content -->
	        <jsp:include page="common/footer.jsp"/>
	        <!-- /footer content -->
		</div>
    </div>
    
    <script>
    	$(function(){
    		// 공지사항 상세보기
		    $("#noticeTable>tbody>tr").click(function() {
		    	var nno = $(this).children().eq(0).children().eq(0).val();
		    	
		    	var formName = "#goNotice" + nno;
		    	$(formName).submit();
		    });
 			
    	});
    	
    </script>
    
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
    
    <!-- gauge.js -->
    <script src="${pageContext.request.contextPath}/resources/js/gauge/gauge.min.js"></script>
    
  </body>
</html>
