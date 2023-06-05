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
 		table>tbody>tr:hover{
 			cursor:pointer;
 		}
 	</style>
</head>
<body class="nav-md">
	<div class="container body">
    	<div class="main_container">
        
			<c:if test="${ !empty sessionScope.sendMsg }">
			<script>
				alertify.alert("${sessionScope.sendMsg}"); 
			</script>
			<c:remove var="sendMsg" scope="session"/>
			</c:if>
        
	        <!-- 메뉴바 -->
	        <jsp:include page="../common/menubar.jsp"/>
			
			<div class="right_col" role="main">
	          	<!-- top tiles -->
	          	<div class="">
	            	<div class="page-title">
	              		<div class="title_left">
	                	<h3>쪽지함</h3>
	              	</div>
	            </div>
	
	            <div class="clearfix"></div>
	            <br>
	
	            <ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
	              	<li class="nav-item">
	                	<a class="nav-link active" id="recNoteTab" data-toggle="tab" href="#recNote" role="tab" aria-controls="recNote" aria-selected="true">받은 쪽지함</a>
	              	</li>
	              	<li class="nav-item">
	                	<a class="nav-link" id="sendNoteTab" data-toggle="tab" href="#sendNote" role="tab" aria-controls="sendNote" aria-selected="false">보낸 쪽지함</a>
	              	</li>
	            </ul>
	  
	            <div class="tab-content" id="myTabContent">
	              	<div class="tab-pane fade show active" id="recNote" role="tabpanel" aria-labelledby="recNoteTab">
						<!-- 본문 테이블 -->
	                	<div class="row" style="display: block;">
	                  		<div class="col-md-12 col-sm-12 ">
	                    		<div class="x_panel">                  
	                      			<div class="x_content">
	                        			<div class="row">
	                          				<div class="col-sm-12">
	                            				<div class="card-box table-responsive">
	                              					<table id="rTable" class="table table-bordered" style="width:100%" data-order="">
	                                					<thead>
	                                  						<tr>
							                                    <th style="width:5%"></th>
							                                    <th style="width:15%" >보낸 사람</th>
							                                    <th >제목</th>
							                                    <th style="width:20%">보낸 시간</th>
	                                  						</tr>
	                                					</thead>
	                                					<tbody>
	                                						<c:forEach items="${ receiveList }" var="r">
	                                  						<tr
	                                  						<c:if test="${ r.readCheck eq 0 }">
	                                  						style="background:#F2F2F2;"
	                                  						</c:if>
	                                  						>
	                                    						<th style="text-align: center;">
	                                    							<input type="hidden" value="${ r.noteNo }">
	                                      							<div class="checkbox">
	                                       	 							<label>
	                                          								<input type="checkbox" class="flat" name="checkBtn" value="${ r.noteNo }">
	                                        							</label>
	                                      							</div>
	                                      							<!-- 디테일 보여주기 위한 폼 -->
																	<form id="detailForm${ r.noteNo }" action="detail.nt" method="post">
																		<input type="hidden" name="noteNo" value="${ r.noteNo }">
																	</form>
																	<!-- 답장하기 위한 폼 -->
																	<form id="replyForm${ r.noteNo }" action="sendForm.nt" method="post">
																		<input type="hidden" name="receiveNo" value="${ r.sendNo }">
																		<input type="hidden" name="receiveName" value="${ r.sendName }">
																		<input type="hidden" name="receiveTitle" value="${ r.noteTitle }">
																		<input type="hidden" name="receiveId" value="${ r.sendId }">
																	</form>
	                                    						</th>
	                                    						<td>${ r.sendName }</td>
	                                    						<td>${ r.noteTitle }</td>
	                                    						<td>${ r.sendDateStr }</td>
	                                  						</tr>
	                                  						</c:forEach>
	                                					</tbody>
	                              					</table>
	
	                              					<br>
	                              					
	                              					<!-- 버튼들 -->
	                              					<div style="padding-left: 15px; padding-right: 15px;">
						                                <button class="btn btn-diy" style="float: left;color:white;" id="sendBtn"><small>쪽지 보내기</small></button>
						                                <button class="btn btn-diy" style="float: left;color:white;" id="replyBtn"><small>답장하기</small></button>
	                                					<div style="float:right;">
	                                  						<button class="btn btn-diy" style="color:white;" id="deleteReceive"><small>삭제하기</small></button>
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
	              
	              	<div class="tab-pane fade" id="sendNote" role="tabpanel" aria-labelledby="sendNoteTab">              
	                	<!-- 본문 테이블 -->
	                	<div class="row" style="display: block;">
	                  		<div class="col-md-12 col-sm-12 ">
	                    		<div class="x_panel">                  
	                      			<div class="x_content">
	                        			<div class="row">
	                          				<div class="col-sm-12">
	                            				<div class="card-box table-responsive">
	                              					<table id="sTable" class="table table-bordered" style="width:100%" data-order="">
	                                					<thead>
	                                  						<tr>
							                                    <th ></th>
							                                    <th >받는 사람</th>
							                                    <th >제목</th>
							                                    <th >보낸 시간</th>
							                                    <th >수신 확인</th>
	                                  						</tr>
	                                					</thead>
	                                					<tbody>
	                                						<c:forEach items="${ sendList }" var="s">
	                                  						<tr>
	                                    						<th style="text-align: center;">
	                                    							<input type="hidden" value="${ s.noteNo }">
	                                      							<div class="checkbox">
	                                        							<label>
	                                          								<input type="checkbox" class="flat" name="rcvCheckBtn" value="${ s.noteNo }">
	                                        							</label>
	                                      							</div>
																	<!-- 디테일 보여주기 위한 폼 -->
																	<form id="detailForm${ s.noteNo }" action="detail.nt" method="post">
																		<input type="hidden" name="noteNo" value="${ s.noteNo }">
																	</form>
	                                    						</th>
							                                    <td>${ s.receiveName }</td>
							                                    <td>${ s.noteTitle }</td>
							                                    <td>${ s.sendDateStr }</td>
							                                    <td>
							                                    	<input type="hidden" value="${ s.readCheck }" id="readNo${ s.noteNo }">
							                                    	<label id="readStr${ s.noteNo }">
								                                    	<c:choose>
								                                    	<c:when test="${ s.readCheck eq 0 }">
								                                    		<strong>읽음</strong>
								                                    	</c:when>
								                                    	<c:when test="${ s.readCheck eq 1 }">
								                                    		읽지 않음
								                                    	</c:when>
								                                    	<c:when test="${ s.readCheck eq 2 }">
								                                    		발송 취소
								                                    	</c:when>
								                                    	</c:choose>
							                                    	</label>
							                                    </td>
	                                  						</tr>
	                                  						</c:forEach>
	                                					</tbody>
	                              					</table>
	
	                              					<br>
	                              					
	                              					<!-- 버튼들 -->
	                              					<div style="padding-left: 15px; padding-right: 15px;">
	                                					<div style="float:right;">
	                                  						<button class="btn btn-diy" style="color:white;" id="cancelBtn"><small>발송 취소</small></button>
	                                  						<button class="btn btn-diy" style="color:white;" id="deleteSend"><small>삭제하기</small></button>
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
	              	<!-- 즐겨찾기 끝 -->
				</div>
				<!-- 탭 끝 -->
			</div>
		</div>
	        
		<!-- footer content -->
		<jsp:include page="../common/footer.jsp"/>
	        
	</div>
</div>
	<script>
		var url;
		var data;
		var deleteCheck;
		
		var rTable = $("#rTable").DataTable();
		var sTable = $("#sTable").DataTable();
		
		$(function(){
			// 쪽지 보내기
			$("#sendBtn").click(function() {
				location.href = "sendForm.nt";
			});
			
			// 쪽지 세부조회
			$("table>tbody>tr").click(function() {
				var noteNo = $(this).children().eq(0).children().eq(0).val();
				var formName = "#detailForm" + noteNo;
				$(formName).submit();
			});
			
			// 답장하기
			$("#replyBtn").click(function() {
				
				// 한 건만 체크하도록 카운트 세기
				var count = 0;
	    		
	    		$('input:checkbox[name=checkBtn]:checked').each(function() {
	    			count++;
	    		});
	    		
	    		// 체크된 건이 2개 이상이면
	    		if(count >= 2) {
	    			alertify.alert("1개의 쪽지를 선택해주세요");
	    			return;
	    		} else if(count == 1) { // 그렇지 않으면 답장하기 가능
	    			// 답장하기의 경우, 받는 사람 이름, 번호, 받았던 쪽지의 제목이 필요하다...
	    			// 답장하는 폼에 데이터를 넣은 뒤에 submit 시킨다
	    			
	    			// 체크박스
	    			var checkbox = $('input:checkbox[name=checkBtn]:checked');
	    			// 체크박스 된 줄의 해당 칸
	    			var td = checkbox.parent().parent().parent().parent();
	    			// 해당 쪽지 번호
	    			var nNo = td.children().eq(0).val();
	    			
	    			var replyFormName = "#replyForm" + nNo;
	    			
	    			// 제출하기
	    			$(replyFormName).submit();
	    		} else {
	    			alertify.alert("답장할 쪽지를 선택해주세요");
	    		}
			});
			
			// 발송 취소
			$("#cancelBtn").click(function() {
				// 얘도 체크박스를 읽어와서
				// 이 중에 readCheck가 0인 건이 있다면 이미 읽음처리 된 쪽지가 있습니다. 가 나오도록 하자
				
				// 우선 내가 생성한 건만 체크 되었는지 확인해야 한다
				var checkbox = $('input:checkbox[name=rcvCheckBtn]:checked');
				var tr;
				var readCheck;
				
				var cnt = 0;
				
	    		checkbox.each(function(i) {
	    			tr = checkbox.parent().parent().parent().parent().parent().eq(i);
	    			readCheck = tr.children().eq(4).children().eq(0).val();
	    			
	    			console.log(readCheck);
	    			if(readCheck == 0 || readCheck == 2) {
	    				alertify.alert("이미 읽음처리 되거나 발송취소 된 쪽지가 있습니다.");
	    				cnt = 1;
	    				return false;
	    			} else {
	    				cnt = 0;
	    			}
	    		}); 
    		
	    		if(cnt == 0) {
	    			// 읽지 않은 건들만 선택 되었다면
					var cancelArr = new Array();
	    			
		    		$('input:checkbox[name=rcvCheckBtn]:checked').each(function() {
		    			cancelArr.push(this.value);
		    		});
		    		
		    		if(cancelArr.length >= 1) {
		    			
		    			alertify.confirm("정말 발송을 취소하시겠습니까?", function() {
				    		
			    			var cancelNo = cancelArr.join(",");
		
							$.ajax({
								url:"cancel.nt",
								type:"post",
								data:{"no":cancelNo},
								success:function(list){
									console.log("성공");
									
									var readStr;
									var readNo;
									// 발송취소가 되었으니 해당 컬럼의 발송취소 칸의 값과 텍스트를 변경시켜야 한다
									$.each(list, function(i, obj){
										readStr = "#readStr" + obj.noteNo;
										$(readStr).html("발송 취소");
										
										readNo = "#readNo" + obj.noteNo;
										$(readNo).val(obj.readCheck);
										
										alertify.alert("발송 취소되었습니다.");
									});
								},error:function(){
									console.log("ajax 통신 에러");
								}
							});
		    			});
		    		} else {
		    			alertify.alert("취소할 쪽지를 선택해주세요");
		    		}
	    		}
			});
			
			// 삭제하기
			$("#deleteReceive").click(function() {
				var deleteArr = new Array();
    			
	    		$('input:checkbox[name=checkBtn]:checked').each(function() {
	    			deleteArr.push(this.value);
	    		});
	    		
	    		if(deleteArr.length >= 1) {
	    			alertify.confirm("삭제된 쪽지는 복구 되지 않습니다. 쪽지를 삭제하시겠습니까?", function() {
	    				var deleteNo = deleteArr.join(",");
	    				
	    				deleteCheck = $('input:checkbox[name=checkBtn]:checked');
	    				
	    				url = "delete.nt";
	    				data = {"no":deleteNo,
	    						"classStr":"receive"};
	    				
	    				deleteNote();
	    			});
	    		} else {
	    			alertify.alert("삭제할 쪽지를 선택해주세요");
	    		}
			});
			
			$("#deleteSend").click(function() {
				var deleteArr = new Array();
    			
	    		$('input:checkbox[name=rcvCheckBtn]:checked').each(function() {
	    			deleteArr.push(this.value);
	    		});
	    		
	    		if(deleteArr.length >= 1) {
	    			alertify.confirm("삭제된 쪽지는 복구 되지 않습니다. 쪽지를 삭제하시겠습니까?", function() {
	    				var deleteNo = deleteArr.join(",");
	    				deleteCheck = $('input:checkbox[name=rcvCheckBtn]:checked');
	    				
	    				url = "delete.nt";
	    				data = {"no":deleteNo,
	    						"classStr":"send"};
	    				
	    				deleteNote();
	    			});
	    		} else {
	    			alertify.alert("삭제할 쪽지를 선택해주세요");
	    		}
			});
			
			function deleteNote() {
				$.ajax({
					url:url,
					type:"post",
					data:data,
					success:function() {
						alertify.alert("삭제되었습니다.");
						
						var countFor = 0;
						deleteCheck.each(function(i) {
			    			tr = deleteCheck.parent().parent().parent().parent().parent().eq(i);
			    			tr.addClass("deleted");
			    			countFor ++;
			    		}); 
						
						for(var i=0; i<countFor; i++) {
							rTable.row(".deleted").remove().draw(false);
							sTable.row(".deleted").remove().draw(false);
						}
						
					},error:function() {
						console.log("ajax 통신 오류");
					}
				});
			}
		});
		
	</script>
    
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
</body>
</html>