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
 		<!-- alertifyJS 스크립트 -->
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
    <title>Byeworks</title>
    
 	<style>
    	.form-kdh{
        border: 1px solid white;   
        font-family :'FontAwesome';
        font-size: inherit; 
        padding-top:2.5%;
       background:#e9ecef;
        }
       
    
    </style>
</head>
<body class="nav-md">
   <div class="container body">
       <div class="main_container">
        
        <!-- 메뉴바 -->
        <jsp:include page="../common/menubar.jsp"/>
  		<c:if test="${ !empty sessionScope.retire }">
			<script>
			alertify.alert("${sessionScope.retire}");	
			</script>
			<c:remove var="retire" scope="session"/>
		</c:if>

        <!-- page content -->
        <div class="right_col" role="main">
          <!-- top tiles -->
          <div class="row">
            <div class="col-md-12 col-sm-12">
              <div class="page-title">
	   				<div class="title_left">
	     				<h3>인사 관리 <small style="color:lightgray">전체 사원 관리</small></h3>
	   				</div>
       			 </div>  
        		<div class="clearfix"></div>
         		<br>
              <div class="x_panel">
               
                <div class="x_content">
                    <div class="row">
                        <div class="col-sm-12">
                          <div class="card-box table-responsive">
      
                            <table id="datatable" style="cursor:pointer" class="table table-bordered table-hover">
                              <thead>
                                <tr>
                                  <th></th>
                                  <th>이름</th>
                                  <th>부서</th>
                                  <th>직책</th>
                                  <th>사내전화</th>
                                  <th>이메일</th>
                                </tr>
                              </thead>
                              <tbody>
                                
                                <c:forEach items="${ list }" var="m">
	                                <tr>
	                              	  <input type="hidden" name="memberNo" value="${ m.memberNo }">
	                                  <td onclick="event.cancelBubble=true"><input type="checkbox" class="flat" id="retireday" name="retireday" value="${ m.memberNo }" ></td>
	                                  <td>${ m.memberName }</td>
	                                  <td>${ m.department }</td>
	                                  <td>${ m.position }</td>
	                                  <td>${ m.extension }</td>
	                                  <td>${ m.email }</td>
	                                </tr>
                                </c:forEach>
                              </tbody>
                            </table>
                          
                          </div>
                          <br>
                          <div style="float:right; padding-right:1%;">
                            <button id="retireBtn" type="button" class="btn btn-diy" style="color:white" data-toggle="modal" data-target=".bs-example-modal-sm">사원퇴사</button>

                            <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-hidden="true">
                              <div class="modal-dialog modal-sm">
                                <div class="modal-content">

                                  <div class="modal-header">
                                    <h4 class="modal-title" id="myModalLabel2">퇴사 처리하시겠습니까?</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                                    </button>
                                  </div>
                                  <div class="modal-body">
                                  	<form id="retireForm" action="" method="post">
                                  		<input type="hidden" id="reMemberNo" name="mno" value="">
                                  	</form>
                                  
                                     <label for="memberName">* 이름 :</label>
				                    <input type="text" class="form-control form-kdh" id="memberName" name="memberName" value="" readonly><br>
				                    
				                    <label for="empNo">* 사번 :</label>
				                    <input type="text" class="form-control form-kdh" id="empNo" name="empNo" value="" readonly><br>
				                    
				                    <label for="position">* 직급 :</label>
				                    <input type="text" class="form-control form-kdh" id="position" name="position" value="" readonly><br>
				                    
				                    <label for="소속">* 소속 :</label>
				                    <input type="text" class="form-control form-kdh" id="department" name="department" value="" readonly><br>
                                  </div>
                                  <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">아니오</button>
                                    <button type="button" onclick="deleteMem()" class="btn btn-danger">네</button>
                                  </div>

                                </div>
                              </div>
                            </div>
                          
		                            
                            <button type="button" class="btn btn-diy" style="color:white" onclick="location.href='memberInsertForm.me'">사원등록</button>
                        	</div>
                        </div>
                    </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        
        <script>
        	$(function(){
        		$("#datatable tbody tr").click(function(){
        			location.href="detail.me?mno=" + $(this).children().eq(0).val();	
        		});
        		
        		$("#retireBtn").click(function(){
        		
        			
        			if($('input[name="retireday"]').is(":checked")){
        				
	        			$.ajax({
	        				url:"retireList.me",
	        				data:{mno:$('input[name="retireday"]:checked').val()},
	        				type:"get",
	        				success:function(m){
	        					$("#reMemberNo").val(m.memberNo);
	        					$("#memberName").val(m.memberName);
	        					$("#empNo").val(m.empNo);
	        					$("#position").val(m.position);
	        					$("#department").val(m.department);
	        				},error:function(){
	        					console.log("에이작스x");
	        				}
        				})	
        			}else{
        				alert("체크해야됨");
        			}
        		});
        	});
        	
        	
        
        	
        
        	$(function(){
        		$('input[type="checkbox"][name="retireday"]').click(function(){
        			if($(this).prop('checked')){
        				$('input[type="checkbox"][name="retireday"]').prop('checked', false);
        				$(this).prop('checked',true);
        			}
	        		
        		});
        	});
        	
        	
		function deleteMem(){
			$("#retireForm").attr("action", "retire.me");
			
			$("#retireForm").submit();
		}        
        
        </script>
        
        
        
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
   
</body>
</html>