<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>save</title>
</head>
<body>
<!-- input에 입력된 값을  /member/save에 여기 주소로  post 방식으로 보내겠다 -->
<form action="/member/save" method="post">
  <input type="text" name="memberEmail" placeholder="이메일">
  <input type="text" name="memberPassword" placeholder="비밀번호">
  <input type="text" name="memberName" placeholder="이름">
  <input type="text" name="memberAge" placeholder="나이">
  <input type="text" name="memberMobile" placeholder="전화번호">
  <input type="submit" value="회원가입">
</form>
</body>
</html>