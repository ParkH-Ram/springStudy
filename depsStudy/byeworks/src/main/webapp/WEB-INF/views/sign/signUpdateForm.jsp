<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="icon" href="${pageContext.request.contextPath}/resources/images/LogoExample.png" type="image/png" />
<title>ByeWorks</title>

</head>



<style>

      #outer{
          width: 700px;height: 600px;
      }
      #outer div{
         
          float: left;
      }
  
      .a1{
          width: 30%; height: 100%;border-style: inset;
      }
  
      .a2{
          width: 25%; height:100%;
      }
  
      .space{
          width:5%;height: 100%;
      }
  
      .btnSpace{
          width: 5%; height: 100%;text-align: center; 
      }
  
  
      .fa{
         border: none !important;    
          
      }
  
      .fa:hover{
          color: gray;
          cursor: pointer;
      }
  
      .btnOuter{
          width: 100%;
          height: 50%;
          border: none !important;
      }
  
      .iconFrame{
          border: none !important;
          margin-top: 120px;;width: 100%;
      }
  
      .title{
          width: 100%; height: 5%;line-height: 30px;text-align: center;
      }
  
      .nameBox{
  
          width: 100%; height: 45%;border-style: inset;
      }
  
      .orderDirection{
          width: 5%; height:100%;
          text-align: center;
      }
      
      .signDirection{
          margin-top: 30px; margin-left: 10px;
      }
  
      .menu a{cursor:pointer;}
      .menu .hide{display:none;}
  
      .department, .hide{
  
          list-style: none;
      }
  
      .department{
          font-weight: bold;
          font-size: 20px;
          color: #425567;
      }
  
      .menu{
          cursor: pointer;
      }
      .hide{
          font-weight: normal;
          font-size: 14px;
      }

      #signList{
        width: auto;
        height: auto;
      }

	 #buttonArea > button{
	 	width:100px;
	 	height:40px;
	 	border-radius:10px
	 }
     

     
  
  </style>
  
  	<!-- menubar 위에 있던 파일의 경우에 css가 적용되지 않아 두 개의 css 따로 적용  -->
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/basic/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/resources/css/custom.min.css" rel="stylesheet">
    

 
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">

	<!-- menubar include -->
	<jsp:include page="../common/menubar.jsp"/>
	
			 <div class="right_col" role="main">
          <div class="">
           <div class="page-title">
              <div class="title_left">
              <h3>전자결재 <small style="color:lightgray">결재 수정</small></h3>
              </div>
              </div>

            <div class="clearfix"></div><br>

            <div class="">

              <div class="col-md-6 col-sm-6  ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2><i class="fa fa-align-left"></i> 결재 양식</h2>
                    <div class="clearfix"></div>
                  </div>
                  
                 
                  <script>

                    $(function(){
                    
                      var docu = $("#docu option:selected").text();

                      console.log(docu);
                      if(docu == "사직서"){
                        $(".quit").show();
                        $("#enroll").attr("action","updateSignQuit.si");
                        $(".up").hide();
                        $(".vacation").hide();
                        $(".promotion").hide();
                  
                      }else if(docu =="기안서"){
                        $(".up").show();
                        $(".quit").hide();
                        $(".vacation").hide();
                        $(".promotion").hide();
                   
                      }else if(docu =="휴가/연차"){
                        $(".vacation").show();
                        $("#enroll").attr("action","updateSignAnnual.si");
                        $(".up").hide();
                        $(".quit").hide();
                        $(".promotion").hide();
                        
                      }else if(docu =="승진/발령"){
                        $(".promotion").show();
                        $("#enroll").attr("action","updateSignAppointment.si");
                        $(".up").hide();
                        $(".vacation").hide();
                        $(".quit").hide();
                        
                      }

                     });

                    


                     </script>
                  
                  <div class="x_content">
                    
                    <!-- start accordion -->
                    <div class="accordion" id="accordion" role="tablist" aria-multiselectable="true">
                    <form id="enroll" action="" enctype="multipart/form-data" method="post">
                      <div class="panel">
                        <a class="panel-heading" role="tab" id="headingOne" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                          <h4 class="panel-title">기본설정</h4>
                        </a>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                          
                          <div class="panel-body">
                            
                              	<input type="hidden" name="signNo" value="${ list.signNo }">
                            <table class="table table-bordered">
                                <tr>
                                  <th>문서종류</th>
                                  <td>
                                  	<c:choose>
                                  		<c:when test="${ list.docuType eq 'V' }">
		                                      <select style="border:none;" id="docu" name="docuType">
		                                        <option>선택</option>
		                                        <option value="">기안서</option>
		                                        <option value="V" selected>휴가/연차</option>
		                                        <option value="A">승진/발령</option>
		                                        <option value="Q">사직서</option>
	                                      </select>
	                                     </c:when>
	                                      
	                                      <c:when test="${ list.docuType eq 'A' }">
		                                      <select style="border:none;" id="docu" name="docuType">
		                                        <option>선택</option>
		                                        <option value="">기안서</option>
		                                        <option value="V">휴가/연차</option>
		                                        <option value="A" selected>승진/발령</option>
		                                        <option value="Q">사직서</option>
		                                      </select>
	                                      </c:when>
	                                      <c:otherwise>
		                                      <select style="border:none;" id="docu" name="docuType">
		                                        <option>선택</option>
		                                        <option value="">기안서</option>
		                                        <option value="V">휴가/연차</option>
		                                        <option value="A">승진/발령</option>
		                                        <option value="Q" selected>사직서</option>
		                                      </select>
	                                      </c:otherwise>
                                      
                                    </c:choose>
                                  </td>
                                  <th>작성자<input type="hidden" name="signNo" value="${list.signNo }"></th>
                                  <td>${loginUser.memberName }<input type='hidden' name="memberNo" value="${ loginUser.memberNo }"></td>
                                </tr>
                              
                              
                            </table>
                          </div>
                        </div>
                      </div>
                      <div class="panel">
                        <a class="panel-heading collapsed" role="tab" id="headingTwo" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                          <h4 class="panel-title">결재선</h4>
                        </a>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                          <div class="panel-body">
                            <br>

                            <div class="fa-hover col-md-3 col-sm-4"class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg"><a href="#/plus"><i class="fa fa-plus"></i><span id="signLineAdd">결재선수정</span>  </a>
            						<c:forEach items="${slist }" var="s" varStatus="status">
            							<input type="hidden" name="slist[${ status.index }].signNo" value="${ s.signNo }">
            						</c:forEach>
                            </div>
                            <br><br>
                            <table id="signList" class="table table-bordered"  style="text-align: center;">
                                  <tr id="level">
                                  	<c:forEach items="${slist }" var="s" varStatus="status">
                                  		<th style='width:80px;height:50px;!important'>${ s.position }<input type='hidden' name='slist[${ status.index }].signLineMemberNo' value='${ s.memberNo }'></th>
                                  	</c:forEach>
                                  </tr>

                                  <tr id="stamp">
                                  <c:forEach items="${slist }" var ="s" varStatus="status">
                                  	<td style='width:80px;height:50px;!important'><input type='hidden' name='slist[${ status.index }].memberOrder' value='${ status.index+1 }'></td>
                                  
                                  </c:forEach>
                                      
                                  </tr>

                                  <tr id="name">
                                  <c:forEach items="${ slist }" var ="s" varStatus="status">
                                  	<td style='width:80px;height:50px;!important'>${ s.memberName }</td>
                                  </c:forEach>
                                  
                                      
                                  </tr>

                                  
                              </table>
                              <br>
                              <h6>참조자</h6>
                              <div id="referList">
                              	
                              		<c:forEach items="${ rlist }" var="r" varStatus="rStatus">
	                              		
	                              		<input type="hidden" name="rlist[${rStatus.index}].signNo" value="${ r.signNo }">
	                              	
	                              	</c:forEach>
                              	
                              <c:choose>
                              	<c:when test = "${ !empty rlist }">
	                              	<c:forEach items="${ rlist }" var="r" varStatus="rStatus">
	                              		
	                              		${ r.memberName }&nbsp;//&nbsp;<input type='hidden' name='rlist[${rStatus.index}].referMemberNo' value='${ r.memberNo }'><input type="hidden" name="rlist[${rStatus.index}].signNo" value="${ r.signNo }">
	                              	
	                              	</c:forEach>
                              	</c:when>
                              	
                              	<c:otherwise>
                              	
                              		참조자 없음
                              	</c:otherwise>
                              	</c:choose>
                              </div>
                              
                              
                            
                          
                        </div>
                      </div>
                      </div>
                      <div class="panel">
                        <a class="panel-heading collapsed" role="tab" id="headingThree" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                          <h4 class="panel-title">상세입력</h4>
                        </a>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <c:if test="${ list.docuType eq 'V' }">
                          <div class="panel-body vacation" style="display: none;">
                            <h2 align="center">휴 가 신 청</h2>
                            <br><hr><br>

                            <table class="table table-bordered" style="width:100%; text-align: center; height:200px">

							   <tr>
							   	 <th>제목</th>
							   	 <td><input type="text" name="title" style="border:none;" value="${ list.title }"></td>
							   
							   </tr>
							   
                               <tr>
                                  <th>소속</th>
                                  <td>${list.department }</td>
                              </tr>

                               <tr>
                                  <th>성명</th>
                                  <td>${list.memberName }</td>
                               </tr>

                               <tr>
                                  <th>직위</th>
                                  <td>${loginUser.position }</td>
                              </tr>

                              <tr>
                                 <th>연차종류</th>
                                 <td>
                                 	
                                <c:choose>
                                	<c:when test="${list.annualType eq '0' }">
	                                 	<select name="annualType">
	                                 		<option value="0" selected>연차</option>
	                                 		<option value="1">오전반차</option>
	                                 		<option value="2">오후반차</option>
	                                 	</select>
                                 	</c:when>
                                 	
                                 	<c:when test="${list.annualType eq '1' }">
	                                 	<select name="annualType">
	                                 		<option value="0">연차</option>
	                                 		<option value="1" selected>오전반차</option>
	                                 		<option value="2">오후반차</option>
	                                 	</select>
                                 	</c:when>
                                 	
                                 	<c:when test="${list.annualType eq '2' }">
	                                 	<select name="annualType">
	                                 		<option value="0">연차</option>
	                                 		<option value="1">오전반차</option>
	                                 		<option value="2" selected>오후반차</option>
	                                 	</select>
                                 	</c:when>
                                </c:choose>
                                 </td>
                              </tr>
                              
                              <tr>
                              	<th>연차사유</th>
                              	<td>
                              		<textarea name="annualContent" style="width: 100%;resize: none;border:none;"rows="20">${list.annualContent }</textarea>
                              	</td>
                              </tr>
                              
                              
                              
                              <tr>
                                <th>날짜</th>
                                <td><input type="date" name="annualStartDay" style="border:none;" value="${list.annualStartDay }"> ~ <input type="date" name="annualEndDay" style="border:none;" value="${list.annualEndDay }"></td>
                              </tr>
                              
                              <tr>
                              	<th>기간</th>
								<th><input type="number" name="annualPeriod" value="${list.annualPeriod }"></th>                              	
                              </tr>

                            </table><br>


                          </div>
                          </c:if>
                        
                         <c:if test="${ list.docuType eq 'Q' }">
                          <div class="panel-body quit" style="display: none;">
                            <h2 align="center">사직</h2>
                            <br><hr><br>

                            <table class="table table-bordered" style="width:100%; text-align: center; height:200px">
                              <tr>
                              	 <th>제목</th>
                              	 <td><input type="text" name="title" style="border:none;" value="${list.title }"></td>
                              </tr>
                              
                              <tr>
                              	 <th>입사일</th>
                              	 <td><input type="date" name="enrollDate" style="border:none;" value="${list.enrollDate }"></td>
                              </tr>
                              
                              <tr>
                              	 <th>퇴사일</th>
                              	 <td><input type="date" name="quitDate" style="border:none;" value="${list.quitDate }"></td>
                              </tr>
                              
                              <tr>
                                 <th>퇴사이유</th>
                                 <td>
                                    <textarea style="width: 100%;resize: none;border:none;" name="reason"rows="20">${ list.reason }</textarea>
                                </td>
                              </tr>

                            </table><br>



                          </div>
                          </c:if>

                          <div class="panel-body up" style="display: none;">
                            
                              
                              <h2 align="center">기안서</h2>
                              <br><hr><br>
                      
                              <table id="vacation"class="table table-bordered" style="width:100%; text-align: center; height:200px; border-spacing: 25px;">

                                 <tr>
                                    <th>제목</th>
                                    <td><input type="text" style="width:100%;border:none;"></td>
                                </tr>
                      
                      
                                <tr>
                                    <th>기안 내용</th>
                                    <td>
                                      <textarea style="width: 100%;resize: none;border:none;"rows="20"></textarea>
                                    </td>
                                </tr>
                      
                              </table><br>
                      
                      
                
      

                          </div>
							
						<c:if test="${ list.docuType eq 'A' }">
                          <div class="panel-body promotion" style="display:none">
                            <br>
                                <h2 align="center">승진/발령</h2>
                                <br><hr><br>

                                <table id="promotion"class="table table-bordered" style="width:100%; text-align: center; height:200px; border-spacing: 25px;">
								  
								  <tr>
								  	  <th>제목</th>
								  	  <td><input type="text" name="title" style="border:none;"value="${list.title }"required></td>
								  </tr>
								  
								  <tr>
                                      <th>(승진/발령)종류</th>

		                                      <td>
		                                      	  부서이동 <input id="onlyD" type="radio" value="0" name="appointmentLev" checked>&nbsp; 
		                                      	  직위승진 <input id="onlyP" type="radio" value="1" name="appointmentLev">&nbsp;
		                                      	  부서이동+직위승진 <input id="both" type="radio" value="2" name="appointmentLev">&nbsp; 
		                                      </td>
                                  </tr>
								  
                                  <tr>
                                      <th>성명</th>
                                      <td>
                                      	<select id="aDepartment" name="depBefore" style="border:none;">

                                      		<option>부서를 선택하세요</option>

                                      	</select>
                                      
                                      	<select id="aEmpList" name="appointmentMem" style="border:none;">
                                      		<option>직원을 선택하세요</option>
                                      	</select>
                                      </td>
                                  </tr>
                                  
                                  <tr>
                                      <th>직위</th>
                                      <td>
                                      <input id="selectedMemPosition" type="text" class="" style="border:none;"readonly required>
                                      <input type="hidden" name="positionBefore" value="">
                                      </td>
                                  </tr>

                                  <tr>
                                    <th>승진보직</th>
                                    <td>
                                    	<select id="updatePosition" name="positionAfter" style="border:none;" required>
                                    			<option>선택</option>
                                    			<option value="1">임원</option>
                                    			<option value="2">팀장</option>
                                    			<option value="3">대리</option>
                                    			<option value="4">일반사원</option>
                                    	</select>
                                    </td>
                                  </tr>
                                  
                                  <tr>
                                  	<th>발령부서</th>
                                  	<td>
                                  		<select id="updateDepartment" name="depAfter" style="border:none;"required>
                                  			<option>선택</option>
                                  		</select>
                                  	</td>
                                  </tr>
                                  
                                  <tr>
                                    <th>시행일시</th>
                                    <td><input type="date" name="appointmentDate" style="border:none;"required></td>
                                  </tr>
                                </table><br>

                          </div>
                          </c:if>
                        
                        </div>
                      </div>
                      
                      
                      <script>
                      		//ajax 요청으로 문서 타입이 'A'일경우 셀렉트 박스에  부서출력
							$(function(){
								
									$("#aDepartment option").remove();
									$("#updateDepartment option").remove();
									
									if($("#docu option:selected").val() == 'A'){
									
									$.ajax({
										 url:"selectDepartmentList.si",
						    			 type:"post",
						    			 success:function(list){
						    				 var value="";
						    				 
						    				 $("#aDepartment").append("<option>부서를 선택하세요.</option>");
						    				 $("#updateDepartment").append("<option>부서를 선택하세요.</option>");
						    				 $.each(list, function(i,obj){
						    					if(obj.departmentNo != 0){
						    					 value +=  "<option value='" + obj.departmentNo + "'>" + obj.departmentName + "</option>";
						    					}
						    					 
						    				 })
						    				 
						    				 $("#aDepartment").append(value);
						    				 $("#updateDepartment").append(value);
						    				
						    			 },
						    			 error:function(){
						    				 console.log("ajax 통신 실패");
						    			 }
										
									});
									
									}else{
										$("#aDepartment option").remove();
										$("#aEmpList option").remove();
										$("#selectedMemPosition").val("");
									}
									
								
								
								//ajax 요청으로 부서 클릭시 오른쪽 직원 명단 출력
								$("#aDepartment").on("change", function(){
									$("#selectedMemPosition").val("");
									$("#aEmpList option").remove();
									
									var departmentNo = $("#aDepartment option:selected").val();
									
									$.ajax({
						    			 url:"selectEmpList.si",
						    			 type:"post",
						    			 data: {"departmentNo":departmentNo},
						    			 success:function(list){
						    				
						    				 
						    				 $.each(list, function(i,obj){
						    					 if(list[i].position != 0){
						    					 $("#aEmpList").append("<option value='"+ list[i].memberNo +"' class='"+ list[i].position +"'>"+ list[i].memberName+"</option>");
						    					 }
						    				 })
						    				 
						    					 
						    			 },
						    			 error:function(){
						    				 console.log("ajax 통신 실패");
						    			 }
						    			  
						    		  });
								});
								
								//선택한 직원의 직책정보 출력 및 readonly 처리
								$("#aEmpList").on("change", function(){
									
									$("#selectedMemPosition").val("");

									var selectedEmpPosition = $("#aEmpList option:selected").attr("class");
									console.log(selectedEmpPosition);
									if(selectedEmpPosition == 0){
										$("#selectedMemPosition").val("대표").attr("class",selectedEmpPosition).siblings().eq(0).val(selectedEmpPosition);
									}else if(selectedEmpPosition == 1){
										$("#selectedMemPosition").val("임원").attr("class",selectedEmpPosition).siblings().eq(0).val(selectedEmpPosition);
									}else if(selectedEmpPosition == 2){
										$("#selectedMemPosition").val("팀장").attr("class",selectedEmpPosition).siblings().eq(0).val(selectedEmpPosition);
									}else if(selectedEmpPosition == 3){
										$("#selectedMemPosition").val("대리").attr("class",selectedEmpPosition).siblings().eq(0).val(selectedEmpPosition);
									}else if(selectedEmpPosition == 4){
										$("#selectedMemPosition").val("일반사원").attr("class",selectedEmpPosition).siblings().eq(0).val(selectedEmpPosition);
									}
								});
								
								//승진/발령/두개다 의 목록에 따라서 진행되는 추가적인 JQuery
								$("#onlyD").on("click", function(){
									var check = $("#selectedMemPosition").attr("class");
									console.log(check);
									$("#updatePosition option").each(function(i,item){								
										if(item.value == check){
											$(this).attr("selected",true);
										}
									});
									
									$("#updatePosition").attr("readonly",true);
									$("#updateDepartment").attr("readonly",false);
								});
								
								$("#onlyP").on("click", function(){
									
									var check = $("#aDepartment option:selected").val();
									console.log(check);
									$("#updateDepartment option").each(function(i,item){								
										if(item.value == check){
											$(this).attr("selected",true);
										}
									});
									
									$("#updateDepartment").attr("readonly",true);
									$("#updatePosition").attr("readonly",false);
									
								});
								
								$("#both").on("click", function(){
									$("#updatePosition").attr("readonly",false);
									$("#updateDepartment").attr("readonly",false);
								});
								
								
								
								
								
							});                      
                      </script>
                      
                      <div class="panel">
                        <a class="panel-heading" role="tab" id="headingFour" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="true" aria-controls="collapseFour">
                          <h4 class="panel-title">파일첨부</h4>
                        </a>
                        <div id="collapseFour" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingFour">
                          <br><br>
                          <div class="panel-body">
                            <input type="file" name="reUpLoadFile" multiple>
                            <c:choose>
                            <c:when test = "${!empty alist  }">
					          	<div>
					          		<h6>첨부파일 :</h6> 
					          		<c:forEach items="${ alist }" var="a" varStatus="aStatus">
					          			<a href="${pageContext.servletContext.contextPath }/resources/sign_files/${ a.maName }" download="${ a.oaName }">${ a.oaName}</a>&nbsp;&nbsp; // &nbsp;&nbsp;
					          			<br><br>
					          			
					          			
					          			<input type="hidden" name="alist[${ aStatus.index }].oaName" value="${ a.oaName }">
					          			<input type="hidden" name="alist[${ aStatus.index }].maName" value="${ a.maName }">
					          			<input type="hidden" name="alist[${ aStatus.index }].signNo" value="${ a.signNo }">
					          			<input type="hidden" name="alist[${ aStatus.index }].fileNo" value="${ a.fileNo }">
					          		</c:forEach>
					          		
					          	</div>
							</c:when>
							
							<c:otherwise>
								<br><br><br>기존 결재에 첨부된 파일이 없습니다.
							</c:otherwise>
							</c:choose>	
                            
                          </div>
                        </div>
                      </div>
                      <br>
                      <div id="buttonArea" style="text-align:center;">
                      <button class="btn btn-diy" style="color:white;" type="submit">결재수정</button> <button class="btn btn-diy" style="color:white;"type="reset">초기화</button>
                      </div>
                      </form>
                    </div>
                    <!-- end of accordion -->


                  </div>
                </div>
              </div>

            </div>
            <div class="clearfix"></div>

          
        </div>
       

        <div class="modal fade bs-example-modal-lg signLineModal" tabindex="-1" role="dialog" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">

              <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">결재선지정</h4>
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                </button>
              </div>
              <div class="modal-body">

                <div id="outer">
                  <div class="a1" style="overflow-y: scroll;">
                      <h3>BYEWORKS</h3>
                      <ul class="department">
                         
                      </ul>
                  
                  
                  
                  </div>
          
                  <div class="space"></div>
          
                  <div class="a1">
                      <select name="" id="selectEmp" multiple style="width: 100%;height: 100%;">
                          
                      </select>
                  </div>
          
                  <div class="btnSpace">
                     
                      <div class="btnOuter">    
                          
                          <div class="iconFrame">
                              <i class="fa fa-chevron-circle-right signAddBtn" style="font-size:35px;"></i> 
                              <i class="fa fa-chevron-circle-left signRemoveBtn" style="font-size:35px;"></i>
                          </div>
                      </div>
          
                      <div class="btnOuter">
                          <div class="iconFrame">
                              <i class="fa fa-chevron-circle-right referAddBtn" style="font-size:35px;"></i> 
                              <i class="fa fa-chevron-circle-left referRemoveBtn" style="font-size:35px;"></i>
                          </div>
                      </div>
                  </div>
          
                  <div class="a2">
                      <div class="title"><b>결재자</b></div>
                      <div class="nameBox">
                          <select name="" id="sign" multiple style="width: 100%;height: 100%;">
                            
                          </select>
                      </div>
                      <div class="title"><b>참조자</b></div>
                      <div class="nameBox">
                          <select name="" id="refer" multiple style="width: 100%;height: 100%;">
                            
                          </select>
                      </div>
                  </div>
          
                  <div class="orderDirection">
                      
                      <div class="signDirection">
                          <p>결</p>
                          <p>재</p>
                          <p>방</p>
                          <p>향</p>
                          <i class="fa fa-long-arrow-down"></i>
                      </div>
                  </div>
          
          
          
          
          
              </div>

              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary cancel" data-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary check" data-dismiss="modal">확인</button>
              </div>

            </div>
          </div>
        </div>
        <!-- /page content -->

      

    <div id="custom_notifications" class="custom-notifications dsp_none">
      <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
      </ul>
      <div class="clearfix"></div>
      <div id="notif-group" class="tabbed_notifications"></div>
    </div>
</div>
</div>
</div>



    <script>
      //맨 왼쪽 부서 관련 JQuery
     
      
      
      $(document).ready(function(){
    	  
    	  //결재선 부서 요청 ajax
    	  $("#signLineAdd").on("click",function(){
     		 
    		  $.ajax({
    			 url:"selectDepartmentList.si",
    			 type:"post",
    			 success:function(list){
    				 var value="";
    				 
    				 $.each(list, function(i,obj){
    					
    					 value +=  "<li class='menu'><span>" + obj.departmentName +"</span><input type='hidden' name='departmentNo' value='"+ obj.departmentNo +"'></li>";
    					 
    					 
    				 })
    				 
    				 $(".department").html(value);
    			 },
    			 error:function(){
    				 console.log("ajax 통신 실패");
    			 }
    			  
    			  
    			  
    		  });
    		  
    	  });
    	  
    	  //부서 클릭시 해당 부서 속한 사원들 요청 ajax
    	  $(".department").on("click","li",function(){
    			$("#selectEmp").children("option").remove();  
    	  
    			var departmentNo = $(this).children("input").val();
    		  
    		  $.ajax({
    			 url:"selectEmpList.si",
    			 type:"post",
    			 data: {"departmentNo":departmentNo},
    			 success:function(list){
    				 console.log(list);
    				 
    				 $.each(list, function(i,obj){
    					 
    					if(list[i].position == 0){
    				$("#selectEmp").append("<option value='"+ list[i].memberNo +"' class='"+ list[i].position +"'>"+ list[i].memberName+ "(대표)" + "</option>");
    						}else if(list[i].position == 1){
    							$("#selectEmp").append("<option value='"+ list[i].memberNo +"' class='"+ list[i].position +"'>"+ list[i].memberName + "(임원)" + "</option>");				 
    						}else if(list[i].position == 2){
    							$("#selectEmp").append("<option value='"+ list[i].memberNo +"' class='"+ list[i].position +"'>"+ list[i].memberName + "(팀장)" + "</option>");
    						}else if(list[i].position == 3){
    							$("#selectEmp").append("<option value='"+ list[i].memberNo +"' class='"+ list[i].position +"'>"+ list[i].memberName + "(대리)" + "</option>");
    						}else if(list[i].position == 4){
    							$("#selectEmp").append("<option value='"+ list[i].memberNo +"' class='"+ list[i].position +"'>"+ list[i].memberName + "(사원)" + "</option>");
    						}
    					
    				 })
    				 
    					 
    			 },
    			 error:function(){
    				 console.log("ajax 통신 실패");
    			 }
    			  
    		  });
    		  
    	  
    		  });
    	  
      
    	  
    	  
      
          $(".menu span").click(function(){
              var submenu = $(this).next("ul")
   
      
              if( submenu.is(":visible") ){
                  submenu.slideUp();
              }else{
                  submenu.slideDown();
              }
          });

      //가운데 부서별 직원들 관련 JQuery
          var emp ="";
          $("#selectEmp").on("click","option",function(){
              emp = $(this);

              //console.log(emp);
          });
      
      //버튼 통해서 결재자 등록

          $(".signAddBtn").click(function(){

              //쌤꺼
              // var bool = true;

              // $.each($("#sign option"), function(i, item){
              //     if($(this).text() == emp.text()){
              //         bool = false;
              //     }
              // });

              // if(bool){
              //     $("#sign").append("<option>" + emp.text() + "</option>");
              // }else{
              //     alert("이미있음");
              // 

              if(emp.attr("class")!=4){
              var count = 0;
              $("#sign option").each(function(i,item){
                  if($(this).val() == emp.val()){

                      count = 1;
                  }

              });

                  if(count==0 && $("#sign option").length == 0){
                      $("#sign").append("<option value='" + emp.val() + "' class='" +emp.attr("class") + "'>" + emp.text() + "</option>");
                  }else if(count == 0 && $("#sign option").length != 0){
                      if($("#sign option").last().attr("class") > emp.attr("class")){
                          $("#sign").append("<option value='" + emp.val() + "' class='" +emp.attr("class") + "'>" + emp.text() + "</option>");
                      }else{
                          alert("결재 순서가 잘못되었습니다.");
                      }
                      
                  }else{
                      alert("이미 결재자에 등록된 사원입니다.");
                  }
              }else{
                  alert("일반사원은 결재권한이 없습니다.");
              }
          });
      
          
          //결재자 등록된 사원 삭제
          $(".signRemoveBtn").click(function(){

              $("#sign option:selected").remove();

          });

          //버튼 통해서 참조자 등록
          $(".referAddBtn").click(function(){

              var count =0;

              $("#refer option").each(function(i,item){
                  if($(this).text() == emp.text()){

                      count = 1;
                  }
              });

              if(count==0){
                  $("#refer").append("<option value='" + emp.val() + "'>" + emp.text()+"</option>");
              }else{
                  alert("이미 참조자에 등록된 사원입니다.");
              }

          });

          
          //참조자 등록된 사원 삭제
          $(".referRemoveBtn").click(function(){

              $("#refer option:selected").remove();
          });

          //취소버튼
          $(".cancel").click(function(){
            $("#sign option").remove();
            $("#refer option").remove();

          });

          //확인버튼-->모달창 사라지며 지정된 결재자,참조자에 있는 정보들 모달전화면 결재선에 표로 노출 
         $(".check").click(function(){
            $("#level").children().remove();
            $("#stamp").children().remove();
            $("#name").children().remove();
            $("#referTitle").remove();

            $("#referList").text("");
           
          
		
            $("#sign option").each(function(i,item){
              var level = $(this).attr("class");
              console.log(i);
              switch(level){
              	case "0" : $("#level").append("<th style='width:80px;height:50px;!important'>대표<input type='hidden' name='slist[" + i + "].signLineMemberNo' value='" + $(this).val() + "'></th>"); break;
              	case "1" : $("#level").append("<th style='width:80px;height:50px;!important'>임원<input type='hidden' name='slist[" + i + "].signLineMemberNo' value='" + $(this).val() + "'></th>"); break;
                case "2" : $("#level").append("<th style='width:80px;height:50px;!important'>팀장<input type='hidden' name='slist[" + i + "].signLineMemberNo' value='" + $(this).val() + "'></th>"); break;
                case "3" : $("#level").append("<th style='width:80px;height:50px;!important'>대리<input type='hidden' name='slist[" + i + "].signLineMemberNo' value='" + $(this).val() + "'></th>"); break;
              }
              $("#stamp").append("<td style='width:80px;height:50px;!important'><input type='hidden' name='slist["+i+"].memberOrder' value='"+ (i+1) +"'></td>");
              $("#name").append("<td style='width:80px;height:50px;!important'>"+ $(this).text() + "</td>");
             
             
              
              
              });
            
            
				
            
            

              if($("#refer option").length != 0){

                

                $("#refer option").each(function(i,item){
                var emp = $(this);
                
                $("#referList").append(emp.text()+" // " + "<input type='hidden' name='rlist[" + i + "].referMemberNo' value='" + emp.val() + "'>" + "<input type='hidden' name='rlist[" + i + "].signNo' value='${list.signNo}'>");

              });
              }
              
              
            


            $("#sign option").remove();
            $("#refer option").remove();

         });

      });
      
     
      

  
  </script>	
	
	
	
	
	<!-- footer include -->
	<jsp:include page="../common/footer.jsp"/>
	<!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>

    
	
</body>
</html>