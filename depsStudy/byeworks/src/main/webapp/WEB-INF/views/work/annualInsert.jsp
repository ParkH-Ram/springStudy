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
    	.form-kdh{
        border: 1px solid white;   
        font-family :'FontAwesome';
        font-size: inherit; 
        padding-top:2.5%;
       background:#e9ecef;
        }
        .form-kdh[readonly]{
        background:white;
        }
    
    </style>
</head>
<body class="nav-md">
	<div class="container body">
    	<div class="main_container">
        
        <!-- 메뉴바 -->
        <jsp:include page="../common/menubar.jsp"/>
		<div class="right_col" role="main">
          <!-- top tiles -->
          <div class="page-title">
	   		<div class="title_left">
	     		<h3>연차 관리 <small style="color:lightgray">연차 신청</small></h3>
	   		</div>
       	</div> 
	        <div class="clearfix"></div>
	         <br>
	    
            <div class="col-md-6 ">
              <div class="x_panel">
               <br>
                <div class="x_content">
                  <br>
                  <form id="annualInsert" action="annualInsert.ann" method="post" class="form-horizontal form-label-left">
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">현황</label>
                      <div class="col-md-9 col-sm-9">
                        <div >총 휴가일 : <b>${ann.annualCreate}일</b> / 사용일 : <b>${ann.annualUseDay }일 </b> / 잔여일 : <b>${ann.annualRemain }일</b></div>
                      </div>
                   
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">작성자</label>
                      <div class="col-md-9 col-sm-9">
                        <input type="text" class="form-kdh" readonly="readonly" value="${loginUser.memberName}">
                      </div>
                      
                    </div>
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">연차 기간</label>
                      <div class="col-md-9">
                          <fieldset>
                            <div class="control-group">
                              <div class="controls">
                                <div class="input-prepend input-group">
                                  <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                                  <input type="text" name="annPeriod" id="annPeriod" class="form-control" value="" />
                                  <input type="hidden" name="annualStartDay" id="annualStartDay" value=""/>
                                  <input type="hidden" name="annualEndDay" id="annualEndDay" value=""/>
                                </div>
                              </div>
                            </div>
                          </fieldset>
                      </div>
                    </div>
                   <div class="form-group row">
                      <label for="annual" class="control-label col-md-3 col-sm-3 ">연차 종류</label>
                      <div class="col-md-9 col-sm-9">
                        <select class="select2_single form-control" tabindex="-1" id="annualType" name="annualType">
                          <option value="">선택 해주세요.</option>
                          <option value="0">연차</option>
                          <option value="1">오전 반차</option>
                          <option value="2">오후 반차</option>
                        </select>
                      </div>  
                    </div> 
                    <div class="form-group row">
                      <label class="control-label col-md-3 col-sm-3 ">연차 사유</label>
                      <div class="col-md-9 col-sm-9 ">
                        <input type="text" class="form-kdh" name="annualContent" style="resize:none; height:300px; width : 100%;" id="annualContent" >
                      </div>
                    </div>
                   <input type="hidden" name="annualPeriod" id="annualPeriod" value="">
                   <input type="hidden" value="V" name="docType">
                    <div class="ln_solid"></div>
                    <div class="form-group">
                      <div class="col-md-9 col-sm-9  offset-md-4">
                        <button type="submit" class="btn btn-diy" style="color:white;">전자결재로 이동</button>
                      </div>
                    </div>

                  </form>
                </div>
              </div>
            </div>
   	 </div>

	
        <!-- footer content -->
        <jsp:include page="../common/footer.jsp"/>
       </div> 
  
</div>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-daterangepicker/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-daterangepicker/moment.min.js"></script>
	<script>
	$(function() {
		

		$('input[name="annPeriod"]').daterangepicker({
		      autoUpdateInput: false,
		      locale: {
		        	format: 'YYYY-MM-DD',
		        	separator: ' ~ ',
		        	applyLabel: '확인',
		        	cancelLabel: '취소'                                      
		      	
		      }
		  });

		
		  $('input[name="annPeriod"]').on('apply.daterangepicker', function(ev, picker) { 
			/*   $(this).val(picker.startDate.format('YYYY-MM-DD') + ' ~ ' + picker.endDate.format('YYYY-MM-DD')); */
				var startDate = picker.startDate.format('YYYY-MM-DD');
				var endDate = picker.endDate.format('YYYY-MM-DD');
				var annPeriod = startDate + '~' + endDate;
		  		$('input[name="annualStartDay"]').val(startDate); 
		      	$('input[name="annualEndDay"]').val(endDate); 
		      	$('input[name="annPeriod"]').val(annPeriod); 
		      	
		      	if(startDate != endDate){
		      		$("select option[value*='1']").css('display','none');
		      		$("select option[value*='2']").css('display','none');
		      	}else{
		      		$("select option[value*='1']").css('display','');
		      		$("select option[value*='2']").css('display','');
		      	}
		      	
			});
		  
		  
		
		  
		  $("#annualType").change(function(){

      		var dateDiff = "";
      		
      		//var option = $("#annualType option:selected").val();
				var option = $('#annualType option:selected').val();
      	
      		if(option == 0){
      		
	        		var sdt = new Date($("#annualStartDay").val());
	        		
	        		var edt = new Date($("#annualEndDay").val());
	
	        		 dateDiff = Math.ceil((edt.getTime()-sdt.getTime())/(1000*3600*24)+1);
	        		
	        		 $("#annualPeriod").val(dateDiff);
      		
      		}else{
      		 $("#annualPeriod").val(0.5);
      		 
      		
      		}
		  })
	     
		  
	});
	

	</script>
    <!-- 메인 틀을 구성하기 위한 JS 추가 코드 -->
	<!-- Bootstrap -->
    <script src="${pageContext.request.contextPath}/resources/js/basic/bootstrap.bundle.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	
</body>
</html>