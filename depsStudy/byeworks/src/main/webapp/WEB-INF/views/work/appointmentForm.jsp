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
 
 	<style>
    	.form-kdh{
        border: 1px solid white;   
        font-family :'FontAwesome';
        font-size: inherit; 
        padding-top:2.5%;
        background:white;
        }
        .form-kdh[readonly]{
        background:white;
        }
    
    	
    </style>
 	<title>Byeworks</title>
</head>
<body class="nav-md ">
	<div class="container body">
    	<div class="main_container">
        
        <!-- 메뉴바 -->
        <jsp:include page="../common/menubar.jsp"/>
		 <div class="right_col" role="main">
          <!-- top tiles -->
        <div class="page-title">
	   		<div class="title_left">
	     		<h3>인사 관리 <small style="color:lightgray">발령서</small></h3>
	   		</div>
       	</div> 
       	  <div class="clearfix"></div>
	         <br>
            <div class="col-md-6 ">
              <div class="x_panel">
              <br>
                <div class="x_content">
                  <br>
                  <form id="appointmentInsert" action="appointment.app" method="post" class="form-horizontal form-label-left" >
                    <div class="form-group row" style="margin-bottom:5%;">
                      <label class="control-label col-md-3 col-sm-3">이름</label>
                      <div class="col-md-3 col-sm-3">
                        <input type="text" class="form-kdh" id="appointmentName" name="appointmentMemName" style="border-bottom: 1px solid #425b80;">
                      </div>
                      <div class="com-md-2 col-sm-2"></div>
                      <div class="col-md-3 col-sm-3">
                        <button type="button" class="btn btn-secondary" id="searchMemberName" data-toggle="modal">검색</button>
					  </div>
                        <div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" id="selectEmp">
                          <div class="modal-dialog modal-sm">
                            <div class="modal-content">
      
                              <div class="modal-header">
                                <h4 class="modal-title" id="modalT">사번을 선택해주세요</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                                </button>
                              </div>
                              <div class="modal-body" id="empNoList">
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                                <button type="button" data-dismiss="modal" id="insertEmpNo" class="btn btn-diy" style="color:white;" >등록하기</button>
                              </div>
       
                          	</div>
                        	</div>
                     	 </div>
                  	  </div>
                    
                   
                    <div class="form-group row" style="margin-bottom:5%;">
                      <label class="control-label col-md-3 col-sm-3 ">사번</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-kdh"  id="appEmpno" readonly="readonly" value="">
                        <input type="hidden" id="appMno" name="memberNo" value="">
                        
                      </div>
                    </div>
                    <div class="form-group row" style="margin-bottom:5%;">
                      <label class="control-label col-md-3 col-sm-3 ">입사일</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-kdh" id="enrollday" readonly="readonly" value="">
                      </div>
                    </div>
                    <div class="form-group row" style="margin-bottom:5%;">
                      <label for="department" class="control-label col-md-3 col-sm-3 ">소속</label>
                      <div class="col-md-3 col-sm-3">
                        <input type="text" class="form-kdh" id="departmentName" name="dbName" readonly="readonly" value="">
                        <input type="hidden" name="depBefore" id="depBefore" value="">
                      </div>
                      <div class="com-md-2 col-sm-2"></div>
                      <div class="col-md-4 col-sm-4">
                    
                        <select id="depAfter" class="select2_single form-control" tabindex="-1"name="depAfter">
                          <option value="9">선택 해주세요.</option>
                          <option value="2">사업팀1</option>
                          <option value="3">사업팀2</option>
                          <option value="4">인사팀1</option>
                          <option value="5">인사팀2</option>
                          <option value="6">총무팀1</option>
                          <option value="7">총무팀2</option>
                        </select>
                      </div>  
                    </div> 
                    
                    <div class="form-group row" style="margin-bottom:5%;">
                      <label for="position" class="control-label col-md-3 col-sm-3 ">직위</label>
                        <div class="col-md-3 col-sm-3">
                          <input type="text" class="form-kdh" readonly="readonly" id="positionName" name="pbName" value="">
                          <input type="hidden" id="positionBefore" name="positionBefore" value="">
                        </div>
                        <div class="com-md-2 col-sm-2"></div>
                        <div class="col-md-4 col-sm-4">
                          <select id="positionAfter" class="select2_single form-control" tabindex="-1"  name="positionAfter">
                                <option value="9">선택 해주세요.</option>
                                <option value="1">임원</option>
                                <option value="2">팀장</option>
                                <option value="3">대리</option>
                                <option value="4">사원</option>
                          </select>
                        </div>
                    </div>
                  
                  	
                  <div class="form-group row" style="margin-bottom:5%;">
                	 <label for="" class="control-label col-md-3 col-sm-3 "></label>
                  	 <div class="col-md-3 col-sm-3">
                     	<input type="hidden" value="A" name="docType">
                    	<input type="hidden" value="2" id="appointLev" name="appointLev">
                   	</div>
                   	 <div class="com-md-2 col-sm-2"></div>
                    <div class="col-md-3 col-sm-3">
                    	    	
                    </div>
                  </div>
                   
                    <div class="form-group">
                      <div class="col-md-9 col-sm-9  offset-md-3" style="margin-top:8%;">
                       <button type="button" class="btn btn-secondary" id="confirmBtn">확정</button>      
                        <button type="submit" id="signPass" class="btn btn-diy" style="color:white;" disabled>전자결재로 이동</button>
                      </div>
                    </div>

                  </form>
                </div>
              </div>
            </div>
		<script>
           	$(function(){
           		
           		$("#searchMemberName").click(function(){
           			if($("#appointmentName").val().trim().length == 0) {
            			alertify.alert("이름을 입력해주세요");
            			return;
            		}
           			$("#selectEmp").modal();
           			$.ajax({
           				url:"searchMemberName.me",
           				data:{"memberName":$("#appointmentName").val()},
           				type:"get",
           				success:function(list){
           					
           					var value="";
           					$.each(list, function(i, m){
                                value += '<input type="radio" class="radioflat" id="searchMemberNo" name="searchMemberNo" style="margin-top:5%" value='+ m.memberNo +'>' +
				                		 '<input type="text" class="form-kdh" id="empno" name="empno" style="margin-left:5%" value='+ m.empNo +' readonly><br>';
				        	
           					});
           					$("#empNoList").html(value);
           				
           					
           				},error:function(){
           					console.log("실패");
           				}
           				
           			});
           		});
           		$("#insertEmpNo").click(function(){
           			if($('input[name="searchMemberNo"]').is(':checked')){
           				
           				$.ajax({
           					url:"retireList.me",
   	        				data:{mno:$('input[name="searchMemberNo"]:checked').val()},
   	        				type:"get",
   	        				success:function(m){
   	        					$("#appMno").val(m.memberNo);
   	        					$("#appEmpno").val(m.empNo);
   	        					$("#enrollday").val(m.enrollday);
   	        					$("#positionName").val(m.position);
   	        					$("#departmentName").val(m.department);
   	        					$("#depBefore").val(m.departmentNo);
   	        					$("#positionBefore").val(m.positionNo);
   	        					
   	        				
           					
   	        				},error:function(){
   	        					console.log("x");
   	        				}
           				});
           				
           			}
           		});
           		
           		$("#confirmBtn").click(function(){
           			
           			if($("#depAfter").val() == 9 && $("#positionAfter").val() !=9  ){
           				
           				$("#appointLev").val(1);
           				$("#depAfter").val($("#depBefore").val());
           			}else if($("#positionAfter").val()==9 && $("#depAfter").val() != 9){
           				
           				$("#appointLev").val(0);
           			}else{
           				
           				$("#appointLev").val(2);
           			}
           			
           			$("#signPass").removeAttr("disabled");
           				
           		});
           				
           	});
           
           	
           	
                    
        </script>
     </div>
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