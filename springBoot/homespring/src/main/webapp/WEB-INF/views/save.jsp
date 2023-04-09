<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>save</title>
  <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
</head>
<body>
  <form action="/member/save" method="post">
    <input type="text" name="memberEmail" placeholder="이메일" id="memberEmail" onblur="emailCheck()">
    <p id="check-result"></p>
    <input type="text" name="memberPassword" placeholder="비밀번호">
    <input type="text" name="memberName" placeholder="이름">
    <input type="text" name="memberAge" placeholder="나이">
    <input type="text" name="memberMobile" placeholder="전화번호">
    <input type="submit" value="회원가입">
    </form>
</body>
<script>
  // 이메일 입력값을 가져오고,
  // 입력값을 서버로 전송하고 똑같은 이메일이 있는지 체크한 후
  // 사용 가능 여부를 이메일 입력창 아래에 표시
  const emailCheck = () => {
    // 입력 값을 가져오는 내용
    const email = document.getElementById("memberEmail").value;
    // 출력을 위한 요소를 가져 오고
    const checkResult = document.getElementById("check-result");
    // 확인용 콘솔
    console.log("입력한 이메일", email);
    // ajax의 목적은 화면의 바뀜 없이 http와 추고 받으려고
    $.ajax({
      // 요청방식: post, url: "email-check", 데이터: 이메일
      type: "post",
      url: "/member/email-check",
      data: {
        "memberEmail": email  // 사용자가 입력한 이메일 값
      },
      success: function(res) {  // 요청이 성공해서 응답이 오는데 그걸 어떻게 처리 할지
        console.log("요청성공", res);
        if (res == "ok") {  //db에 이메일이 있나  없나 확인 후 // 값이 없으면
          console.log("사용가능한 이메일");
          checkResult.style.color = "green";
          checkResult.innerHTML = "사용가능한 이메일";
        } else {
          console.log("이미 사용중인 이메일");
          checkResult.style.color = "red";
          checkResult.innerHTML = "이미 사용중인 이메일";
        }
      },
      error: function(err) {
        console.log("에러발생", err);
      }
    });
  }
</script>
</html>
