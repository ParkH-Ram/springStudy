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
          <div class="page-title">
             		<div class="title_left">
               		<h3>근태 관리 <small style="color:lightgray">출퇴근 현황 조회(인)</small></h3>
             		</div>
           	</div>

           	<div class="clearfix"></div>
           	<br>
            <div class="x_content">
              <div class="calendar-exibit">
                <div class="col-md-3">
                  <fieldset>
                    <div class="control-group">
                      <div class="controls">
                        <div style="padding-left:0px" class="col-md-11 xdisplay_inputx form-group row has-feedback">
                          <input type="text" class="form-control" style="padding-left:55px;" id="commuteDateStr" value="" name="commuteDateStr" aria-describedby="inputSuccess2Status">
                          <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                          <span id="inputSuccess2Status" class="sr-only">(success)</span>
                        </div>
                      </div>
                    </div>
                  </fieldset>
                

                </div>
              </div>
            
              <div class="row" style="display: block;">
                <div class="col-md-8 col-sm-8">
                  <table id="commuteTable" class="table " style="width:100%">
                    <thead>
                      <tr>
                        <th>사원</th>
                        <th>사번</th>
                        <th>부서</th>
                        <th>직책</th>
                        <th>출근시간</th>
                        <th>퇴근시간</th>
                        <th>비고</th>
                      </tr>
                    </thead>


                    <tbody>
                    	
                      
                    </tbody>
                  </table>
                  
                </div>
              </div>    

            </div>
			
			
			<script src="${pageContext.request.contextPath}/resources/js/bootstrap-daterangepicker/daterangepicker.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/bootstrap-daterangepicker/moment.min.js"></script>
			<script>
		
			$(function() {
				
				 selectCommuteList();
				  $('input[name="commuteDateStr"]').daterangepicker({
				    singleDatePicker: true,
				    showDropdowns: true,
				    locale : {
				    	format:'YY/MM/DD'
				    }
				  });
				  
				 
				  
				 
			});
			
			function selectCommuteList(){
				 $('input[name="commuteDateStr"]').change(function(){
					 $.ajax({
						url:"commuteDate.co",
						data:{commuteDateStr:$("input[name='commuteDateStr']").val()},
						type:"get",
						success:function(list){
							
							var value = "";
							$.each(list, function(i, obj){
								value += "<tr>" +
			                        			"<td>" + obj.empName + "</td>" +
						                        "<td>" + obj.empNos + "</td>" +
						                        "<td>" + obj.empDept + "</td>" +
						                        "<td>" + obj.empPos +  "</td>" +
						                        "<td>" + obj.commuteWork + "</td>" + 
						                        "<td>" + obj.commuteLeaved + "</td>" +
						                        "<td>" + obj.commuteStatus + "</td>" +
						                 "</tr>";
								
							});
							
							if(!value){
								
								value = "<td colspan='6' style='text-aligin:center;'> 출근 내역이 없습니다.</td>"; 
								 $("#commuteTable tbody").html(value);
								
							}else{
								
								 $("#commuteTable tbody").html(value);
								 
							}
							
	                    	
						},error:function(){
							
						}
						 
					 });
					  
				  });
			}
			
			</script>
     

          <div class="clearfix"></div>

        </div>
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
	 <!-- datepicker js-->
    
</body>
</html>