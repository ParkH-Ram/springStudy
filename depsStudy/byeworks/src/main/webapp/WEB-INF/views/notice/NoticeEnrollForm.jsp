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
<title>Insert title here</title>
  <style>
      #enrollForm>table{width:100%;}
      #enrollForm>table *{ margin:5px;}
      .form-notice {
            /* display: block; */
            width: 600px;
            height: calc(1.5em + .75rem + 2px);
            padding: .375rem .75rem;
            font-size: 1rem;
            font-weight: 400;
            line-height: 1.5;
            color: #495057;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #ced4da;
            border-radius: .25rem;
            transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
        }
        .btn-primary {
          margin-left: 180px;
        }
          
  </style>
</head>
<body class="nav-md">
	<div class="container body">
    	<div class="main_container">
        
        <!-- 메뉴바 -->
        <jsp:include page="../common/menubar.jsp"/>
	
	
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">

            <div class="page-title">
              <div class="title_left">
                <h3>공지사항 <small style="color:lightgray">작성하기</small></h3>
              </div>


            </div>

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12">
                <div class="x_panel">
                  <!-- <div class="x_title"> -->
                    <!-- <h2>
                      <label for="fullname">공지사항 제목 * :</label>
                      <input type="text" id="fullname" class="form-control" name="fullname" required="" style="width: 760px;">              
                      <div class="col-md-12 col-sm-12  form-group">
                      <input type="text" placeholder="공지사항제목수정부분" class="form-control">
                      </div>
                    </h2>
                    <small>작성자 : (작성자이름) </small>
                    <div class="clearfix"></div>
                  </div>

                  <div class="x_content">
                    <br>


                    <input type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage">
                    <br>
                    <br>
                    <br>
                    <textarea class="form-control" rows="3" placeholder="공지사항 내용 작성" style="margin-top: 0px; margin-bottom: 0px; height: 106px;"></textarea>
 -->
 				<form id="enrollForm" method="post" action="insert.not" enctype="multipart/form-data">
                    <table align="center">
                      <tr>
                          <th><label for="title" style="margin-right:60px ;">제목</label></th>
                          <td><input type="text" id="title" class="form-notice" name="noticeTitle" required></td>
                      </tr>
                      <tr>
                          <th>
                          	<label for="writer">작성자</label>
                          	<input type="hidden" value="${ loginUser.memberNo }" name="memberNo">
                          </th>
                          <td><input type="text" id="writer" class="form-control" value="${ loginUser.memberName }" name="memberName" readonly></td>
                      </tr>
                      <tr>
                          <th><label for="upfile">첨부파일</label></th>
                          <td><input type="file" id="upfile" class="form-control-file border" name="uploadFile"></td>
                      </tr>
                      <br>
                      <tr>
                          <th colspan="5"><label for="content">내용</label></th>
                      </tr>
                      <tr>
                          <th colspan="5"><textarea class="form-control" required name="noticeContent" id="content" rows="10" style="resize:none;"></textarea></th>
                      </tr>
                    </table>

                    
                    
                    <br>
                    <div class="item form-group" style="float:right;">
                        <button type="submit" style="color:white;" class="btn btn-diy">등록하기</button>
                    </div>
 </form>                   
                    
                  <!-- </div> -->


                </div>
              </div>
            </div>
          </div>
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
</body>
</html>