<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/images/LogoExample.png" type="image/png" />

<title>ByeWorks</title>
<style>
	#datatable tbody tr:hover{
		cursor:pointer;
	}

</style>
</head>
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
	
	 <div class="right_col" role="main" id="printMe">
          <div class="">
            <div class="page-title">
              <div class="title_left">
              <c:choose>
              	<c:when test="${ type == 1}">
                	<h3>내 결재 전체 조회</h3>
                </c:when>
                
                <c:when test="${ type == 2 }">
                	<h3>결재 대기 문서 조회</h3>
                </c:when>
                
                <c:when test="${ type == 3 }">
                	<h3>결재 진행 문서 조회</h3>
                </c:when>
                
                <c:when test="${ type == 4}">
                	<h3>결재 완료 문서 조회</h3>
                </c:when>
                
                <c:when test="${ type == 5 }">
                	<h3>결재 반려 문서 조회</h3>
                </c:when>
                
                <c:when test="${ type == 6 }">
                	<h3>결재 회수 문서 조회</h3>
                </c:when>
                
                <c:when test="${ type ==7 }">
                	<h3>참조 결재 문서 조회</h3>
                </c:when>
                
                <c:otherwise>
                	<h3>결재 할 문서 조회</h3>
                </c:otherwise>
              </c:choose>
              </div>
            </div>

            <div class="clearfix"></div><br>

            <div class="row">
              <div class="col-md-12 col-sm-12 ">
                <div class="x_panel">
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">
                            <div class="card-box table-responsive">
                            <c:choose>
                            	<c:when test="${ type == 1}">
                            					<c:set var="ccount" value="0"/>
                            					<c:set var="ocount" value="0"/>
                            					<c:set var="rcount" value="0"/>
                            		<c:forEach items="${list}" var="l">
                            			<c:choose>
                            				<c:when test="${l.signStatus eq 'C' }">
												<c:set var="ccount" value="${ ccount + 1 }"/>
                            				</c:when>
                            				
                            				<c:when test="${l.signStatus eq 'O' }">
												<c:set var="ocount" value="${ ocount + 1 }"/>
                            				</c:when>
                            				
                            				<c:otherwise>
												<c:set var="rcount" value="${ rcount + 1 }"/>
                            				</c:otherwise>
                            			
                            			</c:choose>                           			
                            		</c:forEach>
                            		
                            		
                            		
	                                <button disabled style="width: 100px;height: 30px; border: none;">결재 반려 (<c:out value="${ rcount }"/>)</button>
	                                <button disabled style="width: 100px;height: 30px; border: none;">결재 진행 (<c:out value="${ ocount }"/>)</button>
	                                <button disabled style="width: 100px;height: 30px; border: none;">결재 완료 (<c:out value="${ ccount }"/>)</button>
	                                <br><br>
	                            </c:when>

                    		</c:choose>
                    <table id="datatable" class="table table-striped table-bordered" style="width:100%;text-align: center;" data-order="">
                      <thead>
                        <tr>
                          <th style="width: 5%;">번호</th>
                          <th style="width: 20%">제목</th>
                          <th style="width: 40%">진행상태</th>
                          <th style="width: 10%">작성자</th>
                          <th style="width: 15%">작성일자</th>
                          <th style="width: 10%">결재상태</th>
                        </tr>
                      </thead>


                      <tbody>
                      <c:forEach items="${ list }" var="l">
                        <tr>
                          <td>${ l.signNo }<input type="hidden" value="${l.docuType }"></td>
                          <td>${ l.title }</td> 
                          <td>${ l.signLine }</td>
                          <td>${ l.memberName }</td>
                          <td><fmt:formatDate value="${ l.signUpDate }" type="both" dateStyle="full" timeStyle="short"/></td>
                          
                        
                          		<c:choose>                       		
                          		
                          			<c:when test="${ l.signStatus eq 'C' && l.flag eq 'Y' }">
                          				<td>완료</td>
                          			</c:when>
                          			
                          			<c:when test="${ l.signStatus eq 'O' && l.flag eq 'Y' }">
                          				<td>진행</td>
                          			</c:when>
                          			
                          			<c:when test="${ l.signStatus eq 'R' && l.flag eq 'Y' }">
                          				<td>반려</td>
                          			</c:when>
                          			
                          			
                          			<c:when test="${ l.signStatus eq 'D' && l.flag eq 'N' }">
                          				<td>회수</td>
                          			</c:when>
                          			
                          			<c:otherwise>
                          				<td>대기</td>
                          			</c:otherwise>
                          		</c:choose>

                          
                        </tr>
                        </c:forEach>
     
                      </tbody>
                    </table>
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
        </div>
        
        <script>
        $(function(){
        
        	$("#datatable tbody tr").click(function(){
    			console.log($(this).children().eq(0).children().eq(0));
    			location.href = "signDetail.si?sno=" + $(this).children().eq(0).text() +"&mno=${loginUser.memberNo}" +"&type=" + $(this).children().eq(0).children("input").val();
    			
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