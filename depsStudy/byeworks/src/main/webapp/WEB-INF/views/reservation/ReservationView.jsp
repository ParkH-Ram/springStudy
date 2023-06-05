<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import = "com.dadada.byeworks.reservation.model.vo.ReservationDTO" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.ArrayList" %>
<%
	List<ReservationDTO> list = (ArrayList<ReservationDTO>)request.getAttribute("showSchedule");
%>
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
 	
 	<link href="${pageContext.request.contextPath}/resources/css/core/main.css" rel="stylesheet">
    <!-- daygrid -->
    <link href="${pageContext.request.contextPath}/resources/css/daygrid/main.css" rel="stylesheet">
    <!-- timegrid -->
    <link href="${pageContext.request.contextPath}/resources/css/timegrid/main.css" rel="stylesheet">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	
    <!-- <script src='../packages/core/main.js'> -->
    <script src="${pageContext.request.contextPath}/resources/js/core/main.js"></script>
    <!-- <script src='../packages/core/locales/ko.js'></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/core/locales/ko.js"></script>
    <!-- <script src='../packages/interaction/main.js'></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/dateJS/date.js"></script>
    <!-- <script src='../packages/daygrid/main.js'></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/daygrid/main.js"></script>
    <!-- <script src='../packages/timegrid/main.js'></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/timegrid/main.js"></script>
    
        <!-- <script src='../packages-premium/resource-common/main.js'></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/resource-common/main.js"></script>
        <!-- <script src='../packages-premium/resource-daygrid/main.js'></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/resource-daygrid/main.js"></script>
        <!-- <script src='../packages-premium/resource-timegrid/main.js'></script> -->
    <script src="${pageContext.request.contextPath}/resources/js/resource-timegrid/main.js"></script>









<style>
        .checkbox-container{
        margin-top: 7px;
      	}
		input[type="radio"] {
		    display:none;
		}
		
		input[type="radio"] + label {
		    color:black;
		    font-family:Arial, sans-serif;
		}
		
		input[type="radio"] + label span {
		    display:inline-block;
		    width:19px;
		    height:19px;
		    margin:-2px 10px 0 0;
		    vertical-align:middle;
		    background:url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/check_radio_sheet.png) -38px top no-repeat;
		    cursor:pointer;
		}
		
		input[type="radio"]:checked + label span {
		    background:url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/check_radio_sheet.png) -57px top no-repeat;
		}
		body {
		    margin: 0;
		    padding: 0;
		    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
		    font-size: 14px;
		  }
	
		  #calendar {
		    max-width: 80%;
		    margin: 50px auto;
		  }
		
.fc-content {
    cursor: pointer;
}
        .btn-diy {
          margin-left: 1px;
        }
</style>
<script>

  document.addEventListener('DOMContentLoaded', function() {
	  
    var calendarEl = document.getElementById('calendar');
    
    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'resourceDayGrid', 'resourceTimeGrid' ],
      //라이센스따온거 이거는 근데 어쩔수없음 ㅠ
      schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
      defaultView: 'resourceTimeGridDay',
      // 날짜 초기값
      defaultDate: new Date(),
      // '2020-02-07'
      editable: false,
      selectable: true,
      eventLimit: true, // allow "more" link when too many events
      locale:'ko',
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'resourceTimeGridDay,resourceTimeGridTwoDay,timeGridWeek,dayGridMonth'
      },
      views: {
        resourceTimeGridTwoDay: {
           type: 'resourceTimeGrid',
          duration: { days: 2 },
           buttonText: '2 days',
        }
      },
      //// uncomment this line to hide the all-day slot
      //allDaySlot: false,
      eventClick:  function(calEvent) {
            /* $('#modalTitle').html(event.title);
            $('#modalBody').html(event.description);
            $('#eventUrl').attr('href',event.url); */
			
            var rno = calEvent.event._def.publicId
                console.log(rno);        
            $.ajax({
            	url:"getRinfo.res",
            	type:"post",
            	data:{"rno":rno},
            	success:function(obj){
					console.log(obj);
            		$("#rName").val(obj.memberName);            		
            		$("#count").val(obj.peopleCount);
            		$("#stime").val(obj.startDate);
            		$("#etime").val(obj.endDate);
            		$("#office").val(obj.conferenceTitle);
            		$("#content").val(obj.conferenceContent);
                    $('#calendarModal').modal();
            	},
            	error: function(){
            		console.log("ajax 실패");
            	}
            }); 
        },
      resources: [
        { id: '1', title: 'A 회의실' },
        { id: '2', title: 'B 회의실', eventColor: 'green' },
        // { id: 'c', title: 'Room C', eventColor: 'orange' },
        // { id: 'd', title: 'Room D', eventColor: 'red' }
      ],
      events: [
    	  
    <% 
       for (int i = 0; i < list.size(); i++) {
        	ReservationDTO dto = (ReservationDTO)list.get(i);
     %>	    	
        { 
        	id: '<%= dto.getReservationNo() %>',
            resourceId: '<%= dto.getRoomNo() %>',
            start: '<%= dto.getStartDate() %>',
            end: '<%= dto.getEndDate() %>',
            title: '<%= dto.getConferenceTitle() %>' 
        },
<%
     	}
%>
        { id: '999', resourceId: '2', start: '2020-05-13 09:00:00', end: '2020-05-13 14:00:00', title: 'event 2' }
        // { id: '4', resourceId: 'c', start: '2020-02-07T07:30:00', end: '2020-02-07T09:30:00', title: 'event 4' },
        // { id: '5', resourceId: 'd', start: '2020-02-07T10:00:00', end: '2020-02-07T15:00:00', title: 'event 5' }
      ],

      select: function(arg) {
        console.log(
          'select',
          arg.startStr,
          arg.endStr,
          arg.resource ? arg.resource.id : '(no resource)'
        );
      },
      dateClick: function(arg) {
        console.log(
          'dateClick',
          arg.date,
          arg.resource ? arg.resource.id : '(no resource)'
        );
      }
    });

    calendar.render();
  });

</script>
</head>
	<body class="nav-md">
		<div class="container body">
	    	<div class="main_container">
		        <!-- 메뉴바 -->
		        <jsp:include page="../common/menubar.jsp"/>
		
					<!-- page content -->
			        <div class="right_col" role="main">
			        <!-- top tiles -->
				        <div class="row" style="display: inline-block;" >
				        	<div id='calendar'></div>
				        </div>
					<!-- ㅊㅊ -->
							<div class="x_panel">
								<div class="x_title">
							    	<h2>회의실 예약하기</h2>
							    <div class="clearfix"></div>
							  	</div>
							  		<div class="x_content">
									    <form action="./createView.res" method="post">
									      <p>회의실 예약 시간은<code> 30분 단위로만</code> 가능합니다.</p>
											<span class="section"></span>
											<div class="field item form-group">
											  <label class="col-form-label col-md-3 col-sm-3  label-align">예약자 이름<span class="required">*</span></label>
												  <div class="col-md-6 col-sm-6">
												  	<p style="margin-top:8px;">${ loginUser.memberName }</p>
												    <input type="hidden" class="form-control" data-validate-length-range="6" data-validate-words="2" name="memberNo" value="${ loginUser.memberNo }">
												  </div>
											</div>
											<div class="field item form-group">
											  <label class="col-form-label col-md-3 col-sm-3  label-align">회의실 명 및 위치*</label>
											  <div class="col-md-6 col-sm-6 ">
											      <div class="checkbox-container">
											        <input type="radio" id="1" name="roomNo" value="1"/>
											        <label for="1"><span></span>A회의실</label>
											        <input type="radio" id="2" name="roomNo" value="2"/>
											        <label for="2"><span></span>B회의실</label>
											      </div>
											  </div>
											</div>
									
											<div class="field item form-group">
											  <label class="col-form-label col-md-3 col-sm-3  label-align">인원수 <span class="required">*</span></label>
											  <div class="col-md-6 col-sm-6">
											    <input class="form-control" type="text" name="peopleCount" required="required"></div>
											</div> <!-- 인원수 닫는거 -->
									
											<div class="field item form-group">
											  <label class="col-form-label col-md-3 col-sm-3  label-align">날짜<span class="required">*</span></label>
											  <div class="col-md-6 col-sm-6">
											    <input class="form-control" type="text" placeholder="YYYY-MM-DD" name="dateTime" required="required"></div>
											</div> <!-- 날짜 닫는거 -->
									
											<!-- 시간 추가 된거 -->
											<div class="field item form-group">
											  <label class="col-form-label col-md-3 col-sm-3  label-align">시간<span class="required">*</span></label>
											  	<div class="col-md-6 col-sm-6">
											
											        <div class="fl">
											          <select id="start" name="startTime" class="select-box" style="width:125px;">
													     <option value="07:00">오전 07:00</option>
													     <option value="07:30">오전 07:30</option>
													     <option value="08:00">오전 08:00</option>
													     <option value="08:30">오전 08:30</option>
													     <option value="09:00">오전 09:00</option>
													     <option value="09:30">오전 09:30</option>
													     <option value="10:00">오전 10:00</option>
													     <option value="10:30">오전 10:30</option>
													     <option value="11:00">오전 11:00</option>
													     <option value="11:30">오전 11:30</option>
													     <option value="12:00">오후 12:00</option>
													     <option value="12:30">오후 12:30</option>
													     <option value="13:00">오후 01:00</option>
													     <option value="13:30">오후 01:30</option>
													     <option value="14:00">오후 02:00</option>
													     <option value="14:30">오후 02:30</option>
													     <option value="15:00">오후 03:00</option>
													     <option value="15:30">오후 03:30</option>
													     <option value="16:00">오후 04:00</option>
													     <option value="16:30">오후 04:30</option>
													     <option value="17:00">오후 05:00</option>
													     <option value="17:30">오후 05:30</option>
													     <option value="18:00">오후 06:00</option>
													   </select>
											               ~
											   		   <select id="end" name="endTime" class="select-box" style="width:125px;">
											            <option value="07:30">오전 07:30</option>
											            <option value="08:00">오전 08:00</option>
											            <option value="08:30">오전 08:30</option>
											            <option value="09:00">오전 09:00</option>
											            <option value="09:30">오전 09:30</option>
											            <option value="10:00">오전 10:00</option>
											            <option value="10:30">오전 10:30</option>
											            <option value="11:00">오전 11:00</option>
											            <option value="11:30">오전 11:30</option>
											            <option value="12:00">오후 12:00</option>
											            <option value="12:30">오후 12:30</option>
											            <option value="13:00">오후 01:00</option>
											            <option value="13:30">오후 01:30</option>
											            <option value="14:00">오후 02:00</option>
											            <option value="14:30">오후 02:30</option>
											            <option value="15:00">오후 03:00</option>
											            <option value="15:30">오후 03:30</option>
											            <option value="16:00">오후 04:00</option>
											            <option value="16:30">오후 04:30</option>
											            <option value="17:00">오후 05:00</option>
											            <option value="17:30">오후 05:30</option>
											            <option value="18:00">오후 06:00</option>
											            <option value="18:30">오후 06:30</option>
											          </select>
											        </div>
											  </div>
											</div>
											<!-- 시간 추가 된거 -->
											<div class="field item form-group"> <!-- 제목 -->
											  <label class="col-form-label col-md-3 col-sm-3  label-align">회의 제목*</label>
											  <div class="col-md-6 col-sm-6 ">
											    <input type="text" class="form-control" name="conferenceTitle" placeholder="회의 제목">
											    
											  </div>
											</div><!-- 제목 -->
											<div class="field item form-group">
											  <label class="col-form-label col-md-3 col-sm-3  label-align">회의 내용<span class="required">*</span></label>
											  <!-- 숫자9면 더 커짐  -->
												<div class="col-md-6 col-sm-6 ">
											  		<textarea class="form-control" rows="3" placeholder="회의내용" name="conferenceContent" style="margin-top: 0px; margin-bottom: 0px; height: 106px;"></textarea>
											    </div>
											</div>
											<div class="ln_solid">
											  <div class="form-group">
											    <div class="col-md-6 offset-md-3">
											      <br>
													<button type="submit" style="color:white;" class="btn btn-diy">등록하기</button>
											    </div>
											  </div>
											</div>   
									    </form>
							  		</div>
							</div>
					 <!-- ㅊㅊ -->
		        	 </div>
		        <!-- /page content -->
		        <!-- footer content -->
		        <jsp:include page="../common/footer.jsp"/>
			</div>
		</div>
		    
      <div id="calendarModal" class="modal fade">
	      <div class="modal-dialog">
	      	<div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span> <span class="sr-only">close</span></button>
	                <h4 id="modalTitle" class="modal-title">회의실 예약 상세조회</h4>
	            </div>
	            
	            	<div id="modalBody" class="modal-body">
	                
		                <form id="antoform" class="form-horizontal calender" role="form">
			                  <div class="form-group">
			                    <label class="col-sm-3 control-label">예약자 이름</label>
			                    <div class="col-sm-9">
			                      <input type="text" class="form-control" id="rName">
			                    </div>
			
			                    <label class="col-sm-3 control-label">인원 수</label>
			                    <div class="col-sm-9">
			                      <input type="text" class="form-control" id="count" >
			                    </div>
			
			                    <label class="col-sm-3 control-label">시작 시간</label>
			                    <div class="col-sm-9">
			                      <input type="text" class="form-control" id="stime" >
			                    </div>
			                    
			                    <label class="col-sm-3 control-label">끝나는 시간</label>
			                    <div class="col-sm-9">
			                      <input type="text" class="form-control" id="etime" >
			                    </div>
			
			                    <label class="col-sm-3 control-label">회의 제목</label>
			                    <div class="col-sm-9">
			                      <input type="text" class="form-control" id="office" >
			                    </div>
			
			                  </div>
			                  <div class="form-group">
			                    <label class="col-sm-3 control-label">회의 내용</label>
			                    <div class="col-sm-9">
			                      <textarea class="form-control" style="height:55px;" id="content" ></textarea>
			                    </div>
			                  </div>
		                </form>
	                </div>
	              <div class="modal-footer">
	                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	              </div>
	     	</div>
	  	 </div>
      </div>    
    
    
    
    
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
	</body>
</html>