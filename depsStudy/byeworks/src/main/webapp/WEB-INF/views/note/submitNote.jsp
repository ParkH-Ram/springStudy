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
 	<style>
 		textarea:focus {
 			outline:none;
 		}
 		table>tbody>tr:hover{
 			cursor:pointer;
 		}
 	</style>
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
                			<h3>쪽지 보내기</h3>
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
                        					<form id="demo-form2" action="insert.al" method="post" data-parsley-validate class="form-horizontal form-label-left" onsubmit="return noteSubmit();">
												<input type="hidden" name="sendNo" value="${ loginUser.memberNo }">
												<input type="hidden" name="alarmGroupNo" value="1">
                          						<div class="item form-group">
                            						<label class="col-form-label col-md-2 col-sm-2 label-align" for="first-name">제목 <span class="required">*</span></label>
                            						<div class="col-md-6 col-sm-6 ">
                              							<input type="text" id="noteTitle" name="noteTitle" required="required" class="form-control" value="${ sessionScope.rTitle }">
                            						</div>
                          						</div>
                          						
                          						<div class="item form-group">
                            						<label class="col-form-label col-md-2 col-sm-2 label-align" for="last-name">받는 사람 <span class="required">*</span></label>
                            						<div class="col-md-6 col-sm-6">
                                						<input type="text" id="receiveName" name="receiveName" required="required" class="form-control" readonly value="${ sessionScope.rName }">
                                						<input type="hidden" id="receiveNo" name="receiveNo" value="${ sessionScope.rNo }">
                                						<input type="hidden" id="receiveId" name="receiveId" value="${ sessionScope.rId }">
                                						<c:remove var="rName"/>
                                						<c:remove var="rNo"/> 
                            						</div>
                            						<button type="button" class="btn btn-diy" style="float:left; color:white;" data-toggle="modal" data-target=".searchAddress"><small>찾아보기</small></button>
                          						</div>

                          						<div class="x_panel">
                            						<div class="x_title">
                              							<h2>내용 작성</h2>
                              							<div class="clearfix"></div>
                            						</div>

                            						<div class="x_content">
                               							<textarea style="resize: none; width: 100%; height: 250px;" name="noteContent" id="noteContent" placeholder="내용을 입력해주세요" required></textarea>
                               							<div class="ln_solid">
                               								<br>
                               								<div style="padding-left: 15px; padding-right: 15px;">
                                 								<div style="float:right;">
                                   									<button class="btn btn-secondary" onclick="goList();"><small>뒤로 가기</small></button>
                                   									<button class="btn btn-diy" style="color:white;" type="submit"><small>보내기</small></button>
                                 								</div>
                               								</div>
                               							</div>
                            						</div>
                          						</div>  
                        					</form>
                      					</div>                      
                    				</div>                    
                  				</div>                  
                			</div>
              			</div>
            		</div>
          		</div>
        	</div>
        	
        	<!-- 답장하기 혹은 주소록에서 넘어왔다면 세션에서 지워줄 데이터가 있음 -->
        	<c:remove var="rTitle"/>
        	<c:remove var="rName"/>
        	<c:remove var="rNo"/>
		
        	<!-- footer content -->
        	<jsp:include page="../common/footer.jsp"/>
        
        	<!-- Modal -->
        	<div class="modal fade searchAddress" tabindex="-1" role="dialog" aria-hidden="true">
      			<div class="modal-dialog modal-lg">
        			<div class="modal-content">

          				<div class="modal-header">
            				<h4 class="modal-title" id="myModalLabel">주소록 찾아보기</h4>
            				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
          				</div>
          				
          				<div class="modal-body">
            				<form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
              					<table id="addrTable" class="table table-striped table-bordered" style="width:100%" data-order="">
                					<thead>
                  						<tr>
						                    <th>이름</th>
						                    <th>부서</th>
						                    <th>직책</th>
						                    <th>이메일</th>
                  						</tr>
                					</thead>
                					<tbody>
                						<c:forEach items="${ list }" var="l">
                  						<tr>
						                    <td>
						                    	<input type="hidden" value="${ l.memberNo }">
						                    	<input type="hidden" value="${ l.memberName }">
						                    	<input type="hidden" value="${ l.memberId }">
						                    	${ l.memberName }
						                    </td>
						                    <td>${ l.department }</td>
						                    <td>${ l.position }</td>
						                    <td>${ l.email }</td>
                  						</tr>
                  						</c:forEach>
                					</tbody>
              					</table>
            				</form>
          				</div>
          				
          				<div class="modal-footer">
            				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
          				</div>
					</div>
      			</div>
    		</div>
		</div>
	</div>
    
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
	<script>
		$(function() {
			var table = $("#addrTable").DataTable({
				pageLength: 5
			});
			
			// 그 컬럼이 눌렸을 때 값이 들어가면서 없어지도록
			$("#addrTable tbody").on('click', 'tr', function() {
				// 받는 사람 번호
				var no = $(this).children().eq(0).children().eq(0).val();
				// 받는 사람 이름
				var name = $(this).children().eq(0).children().eq(1).val();
				// 받는 사람 아이디 (웹소켓)
				var mId = $(this).children().eq(0).children().eq(2).val();
				
				// 이름 설정해주기
				$("#receiveName").val(name);
				$("#receiveNo").val(no);
				$("#receiveId").val(mId);
				$(".searchAddress").modal('hide');
			});
			
		});
		
		function goList() {
			location.href = "selectList.nt";
		}
		
		function noteSubmit() {
			var title = $("#noteTitle").val();
			var content = $("#noteContent").val();
			var receiveNo = $("#receiveNo").val();
			
			// 보낸 사람 이름
			var caller = "${ loginUser.memberName }";
			// 받는 사람 아이디
			var receiverId = $("#receiveId").val();
			
			var returnValue = false;
			
			// 쪽지 보내기
			$.ajax({
				url:'send.nt',
				data:{title:title,
					  content:content,
					  receiveNo:receiveNo},
				type:'post',
				async:false,
				success:function() {
					//alertify.alert("발송되었습니다");
					if(ws) {
						// note,보낸사람이름,받는사람아이디
						let wsMsg = "note," + caller + "," + receiverId;
						console.log("msg: " + wsMsg);
						ws.send(wsMsg);
					}
				},error:function() {
					console.log("ajax 통신 실패");
				}
			});
			
			return true;
		}
		
	</script>
</body>
</html>