<%--
  Created by IntelliJ IDEA.
  User: codepc
  Date: 2023-06-13
  Time: 오전 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form action="/sample/exUploadPost" method="post" enctype="multipart/form-data">
    <div>
<%--        파일 추가 --%>
        <input type='file' name='files'>
    </div>
    <div>
        <input type='file' name='files'>
    </div>
    <div>
        <input type='file' name='files'>
    </div>
    <div>
        <input type='file' name='files'>
    </div>
    <div>
        <input type='file' name='files'>
    </div>
    <div>
<%--        값을 넘기는 것--%>
        <input type="submit">
    </div>


</form>

</body>
</html>
