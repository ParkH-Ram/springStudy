<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <!-- 태그-->
<html>
<head>
    <title>list</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>email</th>
        <th>password</th>
        <th>name</th>
        <th>age</th>
        <th>mobile</th>
        <th>조회</th>
        <th>삭제</th>
    </tr>
    <c:forEach items="${memberList}" var="member">     <!-- forEach 반복문 tr을 감쌌다 -->
        <tr>
            <td>${member.id}</td>
            <td>
                <a href="/member?id=${member.id}">${member.memberEmail}</a>
            </td>
            <td>${member.memberPassword}</td>
            <td>${member.memberName}</td>
            <td>${member.memberAge}</td>
            <td>${member.memberMobile}</td>
            <td>
                <a href="/member?id=${member.id}">조회</a>
            </td>
            <td>
                <button onclick="deleteMember('${member.id}')">삭제</button>  <!--매개 변수를 전달할 때는 '' 홀따옴표 사용-->
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<script>
    const deleteMember = (id) => {
        console.log(id);
        location.href = "/member/delete?id="+id;
    }
</script>
</html>