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
 	<style>
 		.form-control-sm {
			  height: calc(1.64844rem + 2px);
			  padding: 0.25rem 0.5rem;
			  font-size: 0.76563rem;
			  line-height: 1.5;
			  border-radius: 0.2rem; }
 	</style>
</head>
<body class="nav-md">
	<div class="container body">
    	<div class="main_container">
        
        <!-- 메뉴바 -->
        <jsp:include page="../common/menubar.jsp"/>
		 <div class="right_col" role="main">   
          
           <div class="page-title">
             		<div class="title_left">
               		<h3>근태 관리 <small style="color:lightgray">출퇴근 이력 조회</small></h3>
             		</div>
           	</div>

           	<div class="clearfix"></div>
           	<br>
              <div class="x_content">
                 <div>
	                <select class="form-control-sm" id="commuteYear" name="commuteYear">
	                     <option class="dropdown-item"  value="">년도</option>
		                 <option class="dropdown-item"  value="2020">2020</option>
		              
		             </select> 
	                 <select class="form-control-sm" id="commuteMon" name="commuteMon">
		                 <option class="dropdown-item"  value="">월</option>
		                 <option class="dropdown-item"  value="01">1월</option>
		                 <option class="dropdown-item"  value="02">2월</option>
		                 <option class="dropdown-item"  value="03">3월</option>
		                 <option class="dropdown-item"  value="04">4월</option>
		                 <option class="dropdown-item"  value="05">5월</option>
		                 <option class="dropdown-item"  value="06">6월</option>
		                 <option class="dropdown-item"  value="07">7월</option>
		                 <option class="dropdown-item"  value="08">8월</option>
		                 <option class="dropdown-item"  value="09">9월</option>
		                 <option class="dropdown-item"  value="10">10월</option>
		                 <option class="dropdown-item"  value="11">11월</option>
		                 <option class="dropdown-item"  value="12">12월</option>
	                </select>  
	              </div>	
	              <br><br>
                <table id="myCommuteTable" class="table table-bordered">
                  <thead>
                    <tr>
                      <th>날짜</th>
                      <th>출근 시간</th>
                      <th>퇴근 시간</th>
                      <th>비고</th>
                    </tr>
                  </thead>
                  <tbody>
                    
                   
                  </tbody>
                </table>

              </div>
          
          </div>

         

        </div>
        <!-- footer content -->
        <jsp:include page="../common/footer.jsp"/>
        
      </div>
  
    <script>
    	$(function(){
    		
    		$("#commuteMon").change(function(){
    			var commuteDateStr = $("select[id='commuteYear']").val() + '/' + $("select[id='commuteMon']").val();
    			
    			
    			$.ajax({
    				url:"myCommute.co",
    				data:{"commuteDateStr":commuteDateStr,
    					  "commuteMember" : ${loginUser.memberNo}},
    				type:"get",
    				success:function(list){
    					var value = "";
    					$.each(list, function(i, obj){
    						value += "<tr>" +
			    	                      "<th scope='row'>" + obj.commuteDateStr + "</th>" + 
			    	                      "<td>" + obj.commuteWork + "</td>" + 
			    	                      "<td>" + obj.commuteLeaved + "</td>" + 
			    	                      "<td>" + obj.commuteStatus + "</td>" + 
			    	                    "</tr>";
			    						
    					});
    					$("#myCommuteTable tbody").html(value);
    				}, error:function(){
    					console.log("통신실패");
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