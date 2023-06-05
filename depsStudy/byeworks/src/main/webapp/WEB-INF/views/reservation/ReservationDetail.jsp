<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
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
					<div class="">
				    	<div class="page-title">
				        	<div class="title_left">
				            	<h3>내 예약 현황 <small></small></h3>
				           	</div>
						</div> <!-- page-title -->
					
				        <div class="clearfix"></div>
				
					        <div class="row">
					        	<div class="col-md-12 col-sm-12 ">
					        		<div class="x_panel">
					        		<br>	
				                 			
				                 				<ul class="nav navbar-right panel_toolbox">
				                 				<!-- 5월 6일 공지사항 작성하기 추가1번 -->

				  								</ul>
				  							<div class="clearfix"></div>
										
										<div class="x_content">
											<div class="row">
												<div class="col-sm-12">
													<div class="card-box table-responsive">
														<table id="tm" class="table table-striped table-bordered" style="width:100%" data-order="">
															<thead>
													  			<tr class="tableheader">
															        <th>회의제목</th>
															        <th>신청한 날짜</th>
															        <th>취소버튼</th>
													 		 	</tr>
																</thead>	
														    		<tbody>
														  				<c:forEach items="${ list }" var="r" varStatus="status">
														  						<tr class="br">
																				    <td>${ r.conferenceTitle }</td>
																				    <td>${ r.createDate }</td>
																				    <td>
																				  		<form id="postForm" action="delteRe.res" method="post">
																				    	<input type="hidden" name="reservationNo" value="${ r.reservationNo }">
																				    	<input type="hidden" name="mno" value="${ r.memberNo }">
																				    	 
																				    	<button style="color:white;" class="btn btn-diy">취소하기</button>
																				  		</form>  
																				    </td>		
														  						</tr>
																			  	<tr>
																			      <td id="pop" colspan="3" style="font-weight:bold; background-color: white;">사용시간 :  ${ r.startDate } ~ ${ r.endDate } </td>
																				</tr>
																				<tr>
																					<td id="pop" colspan="3" style="font-weight:bold; background-color: white;">
																				    	<div id="bor">회의 위치 : ${ r.roomNo } 회의실</div>
																				  	</td>
																				</tr>
																				<tr>
																		  		<td id="pop" colspan="3" style="font-weight:bold; background-color: white;">
																		        	<div id="bor">회의 내용 : </div>
																		            <br>
																		            ${ r.conferenceContent }
																		        </td>
														    				</tr>
														    			</c:forEach>
																	</tbody>
												    			</table>							
			
							              			</div> <!-- card-box table responsive -->
						              			</div> <!-- col-sm-12 -->
					          				</div> <!-- row -->
				        				</div> <!-- x_content -->
				            		</div>
				          		</div>
							</div>
					</div> <!-- blank -->
				</div> <!-- right col -->
        <!-- /page content -->

	
        <!-- footer content -->
        <jsp:include page="../common/footer.jsp"/>
        
      </div>
    </div>
    
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
	<script>
      $(document).ready(function(){
      
      $('tr.br').nextUntil('tr.br').hide();
      
          $('#tm').on('click', 'tr.br',function(){
              $('tr.br').nextUntil('tr.br').hide();
              $(this).nextUntil('tr.br').slideDown();
              $('#tm').on('dblclick', 'tr.br',function(){
              $(this).nextUntil('tr.br').slideUp();
              });
      });
      });
      
    </script>
</body>
</html>