<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dadada.byeworks.schedule.model.vo.Schedule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<Schedule> list = (ArrayList<Schedule>)request.getAttribute("testL"); %>
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

	<link href='${pageContext.request.contextPath}/resources/fullCalendar/core/main.css' rel='stylesheet' />
	<link href='${pageContext.request.contextPath}/resources/fullCalendar/daygrid/main.css' rel='stylesheet' />
	<link href='${pageContext.request.contextPath}/resources/fullCalendar/timegrid/main.css' rel='stylesheet' />
	<link href='${pageContext.request.contextPath}/resources/fullCalendar/list/main.css' rel='stylesheet' /> 	
	
	<!-- alertifyJs -->
	<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
	<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
	
	<!-- 다음 주소 API -->
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
 	<style>
  		#calendar {
    		max-width: 900px;
    		margin: 0 auto;
  		}
	</style>
 	<title>Byeworks</title>
</head>
<body class="nav-md">
	<div class="container body">
    	<div class="main_container">
        
	        <!-- 메뉴바 -->
	        <jsp:include page="../common/menubar.jsp"/>
        
       		<!-- page content -->
       		<div class="right_col" role="main">
       			<div class="">
           			<div class="page-title">
           				<div class="title_left">
             				<h3>내 일정 관리</h3>
           				</div>
           			</div>

           			<div class="clearfix"></div>
           			<br>

           			<!-- 본문 테이블 -->  
           			<div class="row" style="display: block;">
           				<div class="col-md-12 col-sm-12 ">
           					<div class="col-md-3 col-sm-3 ">
           						<div class="x_panel">
           							<div class="x_content">
           								<form action="insert.sc" method="post">
											<span class="section"><small>일정 등록</small></span>
											<input type="hidden" name="memberNo"class="form-control" value="${ loginUser.memberNo }">
													
											<label for="schTitle">일정명 *</label>
						                    <input type="text" id="schTitle" class="form-control" name="title" style="margin-bottom:2%" required />
						
						                    <label for="beginDate">시작일 *</label>
						                    <input type="date" id="beginDate" class="form-control date" name="beginDate" style="margin-bottom:2%" required />
						                    
						                    <label for="endDate">종료일 *</label>
						                    <input type="date" id="endDate" class="form-control date" name="endDate" style="margin-bottom:5%" required />
						                    
						                    <label for="endDate">위치</label>
						                    <div class="input-group">
                            				<input type="text" id="location" class="form-control" name="location" placeholder="도로명 주소">
                            					<span class="input-group-btn">
                                              		<button type="button" class="btn btn-diy" style="color:white" onclick="sample6_execDaumPostcode(1)">위치</button>
                                          		</span>
                          					</div>
                          					
						                    <label for="endDate">메모</label>
						                    <textarea class="form-control" name="note" id="note" style="margin-bottom:5%; resize:none; height:200px"></textarea>
										
											<label for="color">색상 *</label>
											<select class="form-control" id="color" name="color">
					                            <option selected value="#17a2b8" style="color:#17a2b8">Blue</option>
					                            <option value="#20c997" style="color:#20c997">Green</option>
					                            <option value="#ff9f89" style="color:#ff9f89">Salmon</option>
					                            <option value="#a5adb5" style="color:#a5adb5">Gray</option>
					                            <option value="#F8F195" style="color:#F8F195">Yellow</option>
				                          	</select>
											
											<span class="section"></span>
											<button type="submit" style="float:right" class="btn btn-danger btn-sm">등록하기</button>	
           								</form>
           							</div>
           						</div>
           					</div>
           					<div class="col-md-9 col-sm-9 ">
	             				<div class="x_panel">               
	               					<div class="x_content">
	       			 					<div id='calendar'></div>
	       			 				</div>
	       			 			</div>
	       			 		</div>
       			 		</div>
       			 	</div>
       			</div>
       		</div>
        	<!-- /page content -->
        	<!-- footer content -->
        	<jsp:include page="../common/footer.jsp"/>
      	</div>
    </div>
    
    <!-- Modal  -->
	<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="schModal">
       	<div class="modal-dialog modal-lg">
        	<div class="modal-content">
				<form action="update.sc" method="post" class="form-label-left input_mask">
	           		<div class="modal-header">
	             		<h4 class="modal-title" id="myModalLabel">일정 수정</h4>
	             		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
	           		</div>
	           		<div class="modal-body">	
	           			<input type="hidden" id="updateNo" name="schNo"/>           			
	           			<div class="col-md-12 col-sm-12  form-group has-feedback">
		           			<label for="modalTitle">일정명 *</label>
							<input type="text" id="modalTitle" class="form-control " name="title" required />
						</div>
	           		
               			<div class="col-md-6 col-sm-6  form-group has-feedback">
                 			<label for="modalBegin">시작일 *</label>
                  			<input type="date" id="modalBegin" class="form-control date" name="beginDate" required />
               			</div>
               			<div class="col-md-6 col-sm-6  form-group has-feedback">
                 			<label for="modalEnd">종료일 *</label>
	                    	<input type="date" id="modalEnd" class="form-control date has-feedback-right" name="endDate" required />
               			</div>
                  		
                  		<div class="col-md-3 col-sm-3  form-group has-feedback">                   			
                   			<label for="color">색상 *</label>
							<select class="select2_single form-control" id="modalColor" name="color">
	                            <option selected value="#17a2b8" style="color:#17a2b8">Blue</option>
	                            <option value="#20c997" style="color:#20c997">Green</option>
	                            <option value="#ff9f89" style="color:#ff9f89">Salmon</option>
	                            <option value="#a5adb5" style="color:#a5adb5">Gray</option>
	                            <option value="#F8F195" style="color:#F8F195">Yellow</option>
	                        </select>
                  		</div>
						<div class="col-md-9 col-sm-9 form-group has-feedback">
							<label for="modalLocation">위치</label>
							<div class="input-group">
		                    	<input type="text" id="modalLocation" class="form-control" name="location" onclick="sample6_execDaumPostcode(2)" placeholder="도로명 주소"/>
								<span class="input-group_btn">
									<button type="button" class="btn btn-diy" style="color:white" onclick="sample6_execDaumPostcode(2)">위치</button>
								</span>														
							</div>
                  		</div>
                  		
                  		<div class="col-md-12 col-sm-12 form-group has-feedback">
                  			<label for="modalNote">메모</label>
                  			<textarea style="resize:none; height:200px" class="form-control" name="note" id="modalNote"></textarea>
                  		</div>
	           		</div>
	           
	           		<div class="modal-footer">
	           			<button type="button" class="btn btn-danger"id="deleteBtn">삭제</button>
	             		<button type="submit" class="btn btn-diy" style="color: white;" id="submitBtn">수정</button>
	           		</div>
	           	</form>
	           	
	           	<form action="delete.sc" method="post" id="deleteForm">
	           		<input type="hidden" id="modalId" name="schNo"/>
	           	</form>
         	</div>
       	</div>
	</div>
    
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
	<script>
	var calendarEl;
	
	$(function() {
		calendarEl = document.getElementById('calendar');
		
    	getEvent();
    	
    	// 삭제하기
    	$("#deleteBtn").click(function() {
    		alertify.confirm("삭제 시 복원되지 않습니다. 정말 삭제하시겠습니까?", function() {
	    		$("#deleteForm").submit();			
    		});
    	});
	});
	
	// DB에서 가져오기
	function getEvent() {
		
		var eventData = [];
		
		// 이렇게 담았는데 결국 보여지지 않아서 동기화...
		$.ajax({
			url: "select.sc",
			type: "post",
			success:function(data) {
				
				for(var i=0; i<data.length; i++) {
					var evt = {
						title: data[i].title,
						start: data[i].begin,
						end: data[i].end,
						color: data[i].color,
						no: data[i].schNo
					}
					
					eventData.push(evt);
				}
			}, error:function() {
				console.log("ajax 통신 에러");
			}
		}); 	
		calendarEvent(eventData); // 캘린더 메소드 호출
	}
	
	function calendarEvent(eventData) {
    	
    	var calendar = new FullCalendar.Calendar(calendarEl, {
    		plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list'],
      		header: {
        		left: 'prev,next',
        		center: 'title',
        		right: 'dayGridMonth'
      		},
      		defaultDate: '2020-05-11',
      		navLinks: true, // can click day/week names to navigate views
      		editable: true,
      		selectable: true,
      		displayEventTime: false,
      		/* select: function(arg) {
          		var title = prompt('Event Title:');
          		if (title) {
            		calendar.addEvent({
              			titl	e: title,
              			start: arg.start,
              			end: arg.end,
              			allDay: arg.allDay
            		})
          		}
          		calendar.unselect()
          		
          		//
        	}, */
        	eventClick:function(info) {
        		//alert('Event : ' + info.event.id);
        		//alert('start : ' + info.event.start);
        		//alert('end : ' + info.event.end);
        		//alert('color : ' + info.event.color);
        		// 클릭했을 때 모달창에 해당 이벤트만 뜨게 하자
        		// 이벤트 어차피 다 내가 등록한 거니까 바로 수정되게 하고 그럼 수정 누르면 모달창 사라지면서 리셋 되고
        		// 삭제 누르면 모달창 사라지면서 리셋... ㅇㅋㅇㅋ
        		$("#modalTitle").val(info.event.title);
        		$("#modalId").val(info.event.id);
        		$("#updateNo").val(info.event.id);
        		
        		// ajax로 불러와야겠다
        		var no = info.event.id;
        		
        		$.ajax({
        			url:"selectOne.sc",
        			data:{no:no},
        			type:"post",
        			success:function(data) {
        				$("#modalColor").val(data.color);
        				$("#modalBegin").val(data.begin);
        				$("#modalEnd").val(data.end);
        				$("#modalNote").val(data.note);
        				$("#modalLocation").val(data.location);
        			},error:function() {
        				console.log("에러");
        			}
        		});
        		
        		$("#schModal").modal();
        	},
        	eventLimit:false, // 드래그로 날짜 변경 가능 여부 X
        	events:[<% 
						for(int i=0; i<list.size(); i++) {
							Schedule s = (Schedule)list.get(i);
					%>
					{
						title: "<%= s.getTitle() %>",
						start: "<%= s.getBegin() %>",
						end: "<%= s.getEnd()%>",
						color: "<%= s.getColor() %>",
						id: "<%= s.getSchNo() %>"
					},
					<% } %>
					{
						title: 'test',
						start:'9999-05-01',
						end:'9999-05-03',
						color:'green'
					}
				]
    	});
    	
    	console.log(eventData);
    	calendar.render();
	}
	
	function sample6_execDaumPostcode(classNo) {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                /* // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                } */

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                //document.getElementById('sample6_postcode').value = data.zonecode;
                //document.getElementById("sample6_address").value = addr;
                
                if(classNo == 1) {
	                document.getElementById("location").value = addr;            	
                } else if(classNo == 2) {
	                document.getElementById("modalLocation").value = addr;            	                	
                }
                console.log(addr);
                // 커서를 상세주소 필드로 이동한다.
                //document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }

</script>
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
	<script src='${pageContext.request.contextPath}/resources/fullCalendar/core/main.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullCalendar/interaction/main.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullCalendar/daygrid/main.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullCalendar/timegrid/main.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullCalendar/list/main.js'></script>
    
    <script src="${pageContext.request.contextPath}/resources/js/fastclick/fastclick.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
    
</body>
</html>