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
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.bundle.min.js"></script>

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
               		<h3>근태 관리 <small style="color:lightgray">지각 월별 조회(인)</small></h3>
             		</div>
           	</div>

           	<div class="clearfix"></div>
           	<br>
              <div class="x_content">
                 <div>
	                <select class="form-control-sm" id="commuteLateYear" name="commuteLateYear">
	                     <option class="dropdown-item"  value="">년도</option>
		                 <option class="dropdown-item"  value="2020">2020</option>
		                 
		             </select> 
	                 <select class="form-control-sm" id="commuteLate" name="commuteLate">
		                 <option class="dropdown-item"  value="">월</option>
		                 <option class="dropdown-item"  value="1">1월</option>
		                 <option class="dropdown-item"  value="2">2월</option>
		                 <option class="dropdown-item"  value="3">3월</option>
		                 <option class="dropdown-item"  value="4">4월</option>
		                 <option class="dropdown-item"  value="5">5월</option>
		                 <option class="dropdown-item"  value="6">6월</option>
		                 <option class="dropdown-item"  value="7">7월</option>
		                 <option class="dropdown-item"  value="8">8월</option>
		                 <option class="dropdown-item"  value="9">9월</option>
		                 <option class="dropdown-item"  value="10">10월</option>
		                 <option class="dropdown-item"  value="11">11월</option>
		                 <option class="dropdown-item"  value="12">12월</option>
	                </select> 
		                           
	              </div>	
				<br><br>
                <table class="table center" id="lateTable">
                  <thead>
                    <tr>
                      <th>이름</th>
                      <th>사번</th>
                      <th>부서</th>
                      <th>직책</th>
                      <th>지각횟수</th>
                    </tr>
                  </thead>
                  <tbody>
                    
                    
                  </tbody>
                </table>
				
              </div>  
              <div class="col-md-8 col-sm-8">
                	<canvas id="lateChart" width="60%" height="25%"></canvas>
                </div>

          <div class="clearfix"></div>

        </div>
        <!-- /page content -->
        <!-- footer content -->
        <jsp:include page="../common/footer.jsp"/>
        
      </div>
    </div>
    
    <script>
    
    $(function(){
    	
		$("#commuteLate").change(function(){
			
			var commuteLate = $("select[id='commuteLate']").val();
			var value = "";
			var dept1 = 0;
			var dept2 = 0;
			var dept3 = 0;
			$.ajax({
				url:"lateCommuteList.co",
				data:{"commuteDateStr":commuteLate},
				type:"get",
				success:function(list){
					
					$.each(list, function(i, obj){
						value += "<tr>" +
		    	                      "<td scope='row'>" + obj.empName + "</td>" + 
		    	                      "<td>" + obj.empNos + "</td>" + 
		    	                      "<td>" + obj.empDept + "</td>" + 
		    	                      "<td>" + obj.empPos + "</td>" +
		    	                      "<td>" + obj.lateCount + "</td>" + 
		    	                    "</tr>";
		    	                    
		    	        if(obj.empDept.indexOf("사업팀")>=0){
		    	        	dept1 += Number(obj.lateCount);
		    	        }else if(obj.empDept.indexOf("인사팀")>=0){
		    	        	dept2 += Number(obj.lateCount);
		    	        }else{
		    	        	dept3 += Number(obj.lateCount);
		    	        }
		    						
		    	       
					});
					$("#lateTable tbody").html(value);
					
			
					
					var ctx = document.getElementById("lateChart");
					var lateChart = new Chart(ctx,{
						type:'bar',
						data : {
							title:true,
							labels:["사업팀", "인사팀", "총무팀"],
					 		datasets: 
					 			[{
					 				label: "지각횟수",
					 				borderColor: [
					 					"#ff9f89",
					 					"#F8F195" ,
					 					"#20c997" 
					 					],
				 					backgroundColor: [
				 						"#ff9f89",
				 						"#F8F195",
				 						"#20c997" 
					 					],	
					 				borderWidth:1,
					 				data:[dept1, dept2, dept3]
					 			}]
							
						},   options: {
				             scales: {
				                 xAxes: [{
				                   time: {
				                     unit: 'department'
				                   },
				                   gridLines: {
				                     display: false
				                   },
				                   ticks: {
				                     maxTicksLimit: 6
				                   }
				                 }],
				                 yAxes: [{
				                   ticks: {
				                     min: 0,
				                     max: 15,
				                     maxTicksLimit: 5,
				                     display:true
				                   },
				                   gridLines: {
				                     display: true
				                   }
				                 }],
				               },
				               legend: {
				                 display: false
				               }
				               /* tooltips: {
				                   enabled: false
				               },
				               hover: {
				                   animationDuration: 0
				               },
				               animation: {
				                   duration: 1,
				                   onComplete: function () {
				                       var chartInstance = this.chart,
				                           ct = chartInstance.ctx;
				                       ct.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
				                       ct.textAlign = 'center';
				                       ct.textBaseline = 'bottom';

				                       this.data.datasets.forEach(function (dataset, i) {
				                           var meta = chartInstance.controller.getDatasetMeta(i);
				                           meta.data.forEach(function (bar, index) {
				                               var data = dataset.data[index];                            
				                               ct.fillText(data, bar._model.x, bar._model.y - 5);
				                           });
				                       });
				                   } */
				               
				              
				            }
					
						
					});
					
					
				}, error:function(){
					console.log("통신실패");
				}
				
			});
			
		});
		
		
		/* function chartClear(){
			for(i=0;i<20<i++){
				x[i]=null;
				y[i]=null;
				
			}
			ctx.update();
		
		} */
	});
    </script>
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	 <script src="${pageContext.request.contextPath}/resources/js/Chart.min.js"></script>
	  <!--  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script> -->
</body>
</html>