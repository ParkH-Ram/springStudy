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
	                		<h3>업체 주소록 <small style="color:lightgray">그룹 관리</small></h3>
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
						                                    <th style="width:30%">그룹명</th>
						                                    <th style="width:30%">작성자</th>
						                                    <th style="width:30%">작성자 사번</th>
						                                    <th style="width:10%">수정/삭제</th>
	                                  					</tr>
	                                				</thead>
	                                				<tbody>
	                                					<c:forEach items="${ list }" var="g">
	                                  					<tr	>
						                                    <td>${ g.groupName }</td>
						                                    <td>${ g.memberName }</td>
						                                    <td>${ g.empNo }</td>
						                                    <td>
						                                    	<c:if test="${ loginUser.memberName eq g.memberName }">
						                                    	<div class="btn-group btn-group-sm">
						                                    		<button name="modify" value="${ g.groupNo }" class="btn btn-xs btn-outline-danger"><small>수정</small></button>
						                                    		<button name="delete" value="${ g.groupNo }" class="btn btn-xs btn-outline-danger"><small>삭제</small></button>
						                                    	</div>
						                                    	</c:if>
						                                    </td>
	                                  					</tr>
	                                  					</c:forEach>
	                                				</tbody>
	                              				</table>
	    
	                              				<br>
	                              				
	                              				<!-- 버튼들 -->
	                              				<div style="padding-left: 15px; padding-right: 15px;">
	                                				<div style="float:right;">
	                                  					<button class="btn btn-diy" style="color:white;" id="groupInsert" style="float: left;" data-toggle="modal"><small>그룹 추가</small></button>
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
		</div>
	</div>
	<!-- /page content -->

	<!-- Modal 그룹 추가 -->
	<div class="modal fade" id="groupModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
                	<h4 class="modal-title" id="myModalLabel"></h4>
                	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
              	</div>
              	
              	<div class="modal-body">
                	<!-- 모달창 그룹 추가 본문 내용 -->
                	<form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                  		<div class="item form-group">
                    		<label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">
                      			<span class="required">그룹명</span>
                    		</label>
	                   	 	<div class="col-md-6 col-sm-6 ">
	                      		<input type="text" id="inputGroupName" required="required" class="form-control ">
	                    	</div>
                  		</div>
					</form>
				</div>
              	<div class="modal-footer">
                	<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                	<button type="button" id="modalSubmit" class="btn btn-diy" style="color: white;"></button>
              	</div>
			</div>
		</div>
	</div>
        
	<!-- footer content -->
	<jsp:include page="../common/footer.jsp"/>
	
	<script>
		var action = '';
		var url = '';
		var type = '';
		var gno = 0;
		
		$(function() {
			// 그룹 추가 버튼 클릭 시
			$("#groupInsert").click(function() {
				action = 'insert';
				type = 'POST';
				$("#myModalLabel").text("그룹 추가");
				$("#modalSubmit").text("추가");
				$("#inputGroupName").val("");
				$("#groupModal").modal();
			});
			
			// 수정 버튼 클릭 시
			$("button[name='modify']").click(function() {
				action = 'modify';
				type="POST";
				gno=this.value;
				
				var row = $(this).parent().parent().parent();
				var tr = row.children();
				
				var groupName = tr.eq(0).text();
				
				$("#inputGroupName").val(groupName);
				
				$("#myModalLabel").text("그룹명 수정");
				$("#modalSubmit").text("수정");
				$("#groupModal").modal();
			});
			
			// 삭제 버튼 클릭 시
			$("button[name='delete']").click(function() {
				gno = this.value;
				
				$.ajax({
					url:'delete.bzgr',
					type:'POST',
					data:{"gno":gno},
					success:function(result){
						location.reload();						
					},error:function(){
						console.log("ajax 통신 실패");
					}
				});
			});
			
			// modal창의 submit 버튼 클릭 시
			$("#modalSubmit").click(function() {
				// 그룹 생성이면
				if(action == 'insert') {
					gno = 0;
					url = 'insert.bzgr';
				} else if(action == 'modify') { // 수정이면
					url = 'update.bzgr';
				}
				
				var data = {
					"gno" : gno,
					"memberNo" : ${ loginUser.memberNo },
					"groupName" : $("#inputGroupName").val()
				}
				
				$.ajax({
					url: url,
					type: type,
					data: data,
					success:function(data) {
						$("#groupModal").modal('toggle');
					},
					complete:function(data) {
						location.reload();
					}
				});
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