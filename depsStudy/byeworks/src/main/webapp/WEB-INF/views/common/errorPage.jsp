<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Byeworks</title>

	<!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/resources/css/custom.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/css/font-awesome/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/resources/css/nprogress/nprogress.css" rel="stylesheet">
    
    <link rel="icon" href="${pageContext.request.contextPath}/resources/images/LogoExample.png" type="image/png" />
    <title>ByeWorks</title>
       
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body class="nav-md">
	<div class="container body">
      	<div class="main_container">
        	<!-- page content -->
        	<div class="col-md-12">
          		<div class="col-middle">
            		<div class="text-center text-center">
              		<h1 class="error-number">Error</h1>
              		<h2>에러가 발생했습니다.</h2>
              			<p>알 수 없는 에러가 발생하여 메인 화면으로 돌아가야 합니다. 
              			</p>
              			
              			<div class="">
                			<a href="main.do">메인으로 돌아가기</a>
            			</div>
          			</div>
        		</div>
        		<!-- /page content -->
      		</div>
    	</div>
    	
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.bundle.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/build/custom.min.js"></script>
		<!-- jQuery -->
    	<script src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>    
    	<!-- FastClick -->
	    <script src="${pageContext.request.contextPath}/resources/js/etc/fastclick.js"></script>
	    <!-- NProgress -->
	    <script src="${pageContext.request.contextPath}/resources/js/etc/nprogress.js"></script>

</body>
</html>