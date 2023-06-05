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
        
	        <!-- 메뉴바 -->
	        <jsp:include page="../common/menubar.jsp"/>
	        
	        <!-- page content -->
	        <div class="right_col" role="main">
	          	<!-- top tiles -->
	          	<div class="">
	            	<div class="page-title">
	              		<div class="title_left">
	                		<h3>업체 주소록 <small style="color:lightgray">주소록 전체 조회</small></h3>
	              		</div>
	            	</div>
	
	            	<div class="clearfix"></div>
	            	<br>
	                
	                <div class="row" style="display: block;">
	                  	<div class="col-md-12 col-sm-12 ">
	                    	<div class="x_panel">
	                      		<!-- 그룹 -->
	                      		<div class="form-group row" style="padding-right: 15px; padding-left:15px;">
	                        		<label class="control-label col-md-2 col-sm-2" style="text-align: right;height: 35px; line-height: 35px;">그룹별로 조회하기</label>
	                        		<div class="col-md-2 col-sm-2 ">
	                          			<select class="select2_single form-control" tabindex="-1" id="groupSelect">
				                            <option></option>
				                            <c:forEach items="${ gList }" var="g">
				                            <option value="${ g.groupNo }"
				                            	<c:if test="${ g.groupNo eq sessionScope.groupNo }">
				                            		selected
				                            	</c:if>
				                            >${ g.groupName }</option>
				                            </c:forEach>
	                          			</select>
	                        		</div>
	                      		</div>
	                    	</div>
	                  	</div>
					</div>
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
	                                          							<input type="checkbox" class="flat" name="dCheck" value="${ l.addressNo }">
	                                        						</label>
	                                      						</div>
	                                    					</td>
	                                    					<th style="text-align: center; cursor: pointer;" name="heartBtn" onclick="event.cancelBubble=true">
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
	    
	                              				<br>
	                              				<!-- 버튼들 -->
	                              				<div style="padding-left: 15px; padding-right: 15px;">
	                                				<div style="float:right;">
	                                  					<button class="btn btn-diy" style="color:white;" data-toggle="modal" id="insertBtn"><small>주소 추가</small></button>
	                                  					<button class="btn btn-diy" style="color:white;" id="deleteBtn"><small>주소 삭제</small></button>
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
		
        <!-- Modal  -->
		<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="addrModal">
          	<div class="modal-dialog modal-lg">
           		<div class="modal-content">

              		<div class="modal-header">
                		<h4 class="modal-title" id="myModalLabel"></h4>
                		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
              		</div>
              		
              		<div class="modal-body">
                		<br />
                		<form class="form-label-left input_mask">

                  			<div class="col-md-6 col-sm-6  form-group has-feedback">
                    			<input type="text" class="form-control has-feedback-left" id="name" placeholder="이름" required>
                    			<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                  			</div>

                  			<div class="col-md-6 col-sm-6  form-group has-feedback">
                    			<input type="text" class="form-control" id="bizName" placeholder="회사명">
                    			<span class="fa fa-building form-control-feedback right" aria-hidden="true"></span>
                  			</div>

                  			<div class="col-md-6 col-sm-6  form-group has-feedback">
                    			<input type="text" class="form-control has-feedback-left" id="email" placeholder="이메일">
                    			<span class="fa fa-envelope form-control-feedback left" aria-hidden="true"></span>
                  			</div>

                  			<div class="col-md-6 col-sm-6  form-group has-feedback">
                    			<input type="text" class="form-control" id="phone" placeholder="연락처">
                    			<span class="fa fa-phone form-control-feedback right" aria-hidden="true"></span>
                  			</div>

                  			<div class="col-md-6 col-sm-6  form-group has-feedback">
                    			<input type="text" class="form-control has-feedback-left" id="position" placeholder="직급">
                    			<span class="fa fa-smile-o form-control-feedback left" aria-hidden="true"></span>
                  			</div>

                  			<div class="col-md-6 col-sm-6  form-group has-feedback">
                    			<select class="select2_single form-control" tabindex="-1" id="group">
			                       <option value="0">그룹</option>
		                           <c:forEach items="${ gList }" var="g">
		                           <option value="${ g.groupNo }">${ g.groupName }</option>
		                           </c:forEach>
                    			</select>
                  			</div>
               			</form>
               			
               			<!-- 필수사항 적어두는 부분 -->
               			<div id="checkSubmit">
               			</div>
              		</div>
              
              		<div class="modal-footer">
                		<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                		<button type="submit" class="btn btn-diy" style="color: white;" id="submitBtn">추가</button>
              		</div>
            	</div>
          	</div>
		</div>

        <!-- footer content -->
        <jsp:include page="../common/footer.jsp"/>

    </div>
    
    <script>
    var action = '';
    var url = '';
    var type = '';
    var no = 0;
    
    $(function() {
    	var myNo;
    	var addrNo;
    	var bookmark;
    	
    	// 주소 추가 버튼 클릭 시
    	$("#insertBtn").click(function() {
    		action = 'insert';
    		type = 'POST';
    		
    		// 추가 용 세팅
    		$("#myModalLabel").text("주소록 추가");
    		$("#submitBtn").text("추가");
    		
    		// 안에 내용 빈 칸으로 세팅
    		$("#name").val("");
    		$("#bizName").val("");
    		$("#phone").val("");
    		$("#email").val("");
    		$("#position").val("");
    		$("#group").val(0);
    		$("#checkSubmit").html("&nbsp;&nbsp;&nbsp;&nbsp;이름, 회사명, 그룹은 필수 입력 사항입니다.");
    		
    		// 모달 띄워주기
    		$("#addrModal").modal();
    	});
    	
    	// tr 클릭 시 --> 수정하기
    	$("#datatable-checkbox>tbody>tr").click(function() {
    		action = 'update';
    		type= 'POST';
    		
    		// 값 세팅
    		$("#name").val($(this).children().eq(2).text());
    		$("#bizName").val($(this).children().eq(3).text());
    		$("#position").val($(this).children().eq(4).text());
    		$("#group").val($(this).children().eq(0).children().eq(1).val());
    		$("#email").val($(this).children().eq(6).text());
    		$("#phone").val($(this).children().eq(7).text());
    		
    		$("#submitBtn").text("수정");
    		
    		// 수정하기 클릭 시 만약 내 번호와 수정자의 번호가 같으면 readonly가 없고
    		// 만약 다르면 readonly 태그를 생성해서 넣어주어야 한다...
    		// 다를 경우에는 submit 버튼도 활성화 되면 안 되겠다
    		
    		// 해당 주소를 생성한 사람의 memberNo
    		var no = $(this).children().eq(0).children().eq(0).val();
    		
    		// 내가 만들었다면, 수정하기가 가능하고
    		if(no == ${loginUser.memberNo}) {
    			$("#myModalLabel").text("주소록 수정");
    			$("#submitBtn").removeAttr("disabled");
    			
    			$("#name").removeAttr("readonly");
    			$("#bizName").removeAttr("readonly");
    			$("#position").removeAttr("readonly");
    			$("#group").removeAttr("readonly");
    			$("#email").removeAttr("readonly");
    			$("#phone").removeAttr("readonly");
    			
    			$("#checkSubmit").html("&nbsp;&nbsp;&nbsp;&nbsp;이름, 회사명, 그룹은 필수 입력 사항입니다.");
    			
    			addrNo = $(this).children().eq(1).children().eq(0).val();
    		} else { // 아니면 읽기만 가능해
    			$("#myModalLabel").text("주소록 조회");
    			$("#submitBtn").attr("disabled", true);
    			
    			$("#name").attr("readonly", true);
    			$("#bizName").attr("readonly", true);
    			$("#position").attr("readonly", true);
    			$("#group").attr("readonly", true);
    			$("#email").attr("readonly", true);
    			$("#phone").attr("readonly", true);
    		}
    		
    		$("#addrModal").modal();
    	});
    	
    	// modal창의 submitBtn 클릭 시
    	$("#submitBtn").click(function() {
    		// 지금 누른 버튼이 주소 추가라면
    		if(action == 'insert') {
    			addrNo = 0;
    			url = 'insert.bzad'
    		} else if(action == 'update'){
    			url = 'update.bzad'
    		}
    		
    		if($("#name").val().trim().length == 0) {
    			alertify.alert("이름을 입력해주세요");
    			return;
    		}
    		if($("#bizName").val().trim().length == 0) {
    			alertify.alert("회사명을 입력해주세요");
    			return;
    		}
    		if($("#group").val() == 0) {
    			alertify.alert("그룹을 설정해주세요");
    			return;
    		}
    		
    		var data = {
    			"addressNo" : addrNo,
    			"name" : $("#name").val(),
    			"bizName" : $("#bizName").val(),
    			"email" : $("#email").val(),
    			"phone" : $("#phone").val(),
    			"position" : $("#position").val(),
    			"groupNo" : $("#group").val(),
    			"memberNo" : ${ loginUser.memberNo }
    		}
    		
    		$.ajax({
    			url: url,
    			type: type,
    			data: data,
    			success:function(data) {
    				$("#addrModal").modal('toggle');
    			},
    			complete:function(data) {
    				location.href = "selectList.bzad";
    			}
    		});
    	});
    	
    	// 삭제 처리
    	$("#deleteBtn").click(function() {
    		
    		// 우선 내가 생성한 건만 체크 되었는지 확인해야 한다
    		var memberArr = new Array();
    		var noCount = 0;
    		
    		var checkbox = $('input:checkbox[name=dCheck]:checked');
    		var td;
    		var mem;
    		
    		checkbox.each(function(i) {
    			td = checkbox.parent().parent().parent().parent().eq(i);
    			mem = td.children().eq(0).val();
    			
    			var memberNo = ${ loginUser.memberNo };
    			
    			if(mem != memberNo) {
    				alertify.alert("${ loginUser.memberName }" + "님이 작성하지 않은 주소는 삭제할 수 없습니다.");
    				noCount = 1;
    				return false;
    			} else {
    				noCount = 0;
    			}
    		}); 
    		
    		if(noCount == 0) {
    			// 내가 생성한 건만 선택되었다면
				var deleteArr = new Array();
	    		
	    		$('input:checkbox[name=dCheck]:checked').each(function() {
	    			deleteArr.push(this.value);
	    		});
	    		
	    		if(deleteArr.length >= 1) {
	    			var deleteNo = deleteArr.join(",");

					$.ajax({
						url:"delete.bzad",
						type:"post",
						data:{"no":deleteNo},
						success:function(data){
							location.href = "wasteBasket.bzad";
						},error:function(){
							console.log("ajax 통신 에러");
						}
					});
	    		} else {
	    			alertify.alert("삭제할 건을 선택해주세요");
	    		}
    		}
    	});
    	
    	// 그룹 별 조회
    	$("#groupSelect").change(function() {
    		var groupNo = this.value;
    		
    		$.ajax({
				url:'group.bzad',
				type:'POST',
				data:{"groupNo":groupNo},
				success:function() {
					console.log("성공");
				},error:function() {
					console.log("통신 오류");
				},complete:function() {
					location.replace("selectList.bzad");
				}
			});
    		
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
						bookmark.val('N');
						heart.css("color", "#282c37");
					},error:function() {
						console.log("ajax 통신 실패");
					}
				});
			} else { // 되어있지 않다면 북마크에 값 넣어주기
				$.ajax({
					url:"bookmark.bzad",
					data:{addressNo:addrNo.val(),
						  memberNo:myNo},
					type:"post",
					success:function(result) {
						bookmark.val('Y');
						heart.css("color", "#2b90d9");
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