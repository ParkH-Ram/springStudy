function removeAllListItems() {
    let div_cont_select = document.querySelectorAll(
        "[data-mate-select='active']"
    );

    for (let e = 0; e < div_cont_select.length; e++) {
        div_cont_select[e].setAttribute("data-indx-select", e);
        div_cont_select[e].setAttribute("data-selec-open", "false");
        let ul_cont = document.querySelectorAll(
            "[data-indx-select='" + e + "'] > .cont_list_select_mate > ul"
        );

        ul_cont.forEach(ul => {
            ul.innerHTML = ''; // Removes all li elements inside ul
        });
    }
}

function resetForm() {
    modal_onoff(1, 2);
    document.getElementById('member_id').value = "";
    document.getElementById('member_pw').value = "";
    document.getElementById('member_name_kr').value = "";
    document.getElementById('member_birthdate').value = "";
    document.getElementById('member_phone').value = "";
    document.getElementById('member_email').value = "";
    document.getElementById('member_gender').options[0].selected = true;
    removeAllListItems();
    crear_select();
}


// 회원가입
function registerForm() {
    console.log("들어옴");
    let form = document.getElementById("registrationForm");
    let data = new FormData(form);

    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");

    // Set up request
    var xhr = new XMLHttpRequest();
    xhr.open("POST", form.action, true);
    xhr.setRequestHeader(header, token);

    // Handle response
    xhr.onload = function () {
        if (xhr.status === 200) {
            // Handle successful response
            console.log("회원가입 성공");
            alert_pop(1, xhr.responseText);
            // alert(xhr.responseText);
            resetForm();
        } else {
            // Handle error response
            const msg = xhr.responseText ? xhr.responseText : "회원가입에 실패했습니다.";
            alert_pop(1, msg);
            console.error(msg);
            // alert(xhr.responseText || "회원 가입에 실패했습니다.");
        }
    };

    xhr.onerror = function () {
        // Handle error
        alert_pop(1, "에러가 발생했습니다.");
        console.error("회원가입 실패");
    };

    // Send the request
    xhr.send(data);
}


// 임시 비밀번호 전송 함수
function sendTemporaryPassword() {

    showLoading();

    const memberId = document.getElementById('reg-member-id').value;
    const memberEmail = document.getElementById('reg-email').value;
    const memberNameKr = document.getElementById('reg-name').value;

    if (!memberId || !memberEmail || !memberNameKr) {
        alert_pop(1, '모든 필드를 입력해주세요.');
        return;
    }

    const data = {
        memberId: memberId,
        memberEmail: memberEmail,
        memberNameKr: memberNameKr
    };

    const url = "/auth/confirmInfo";

    $.ajax({
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token);
        },
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        contentType: "application/json;charset=UTF-8",
        success: function (response) {
            console.log(response)

            if (response.check) {
                sendTemporaryPasswordEmail(memberId, memberEmail, memberNameKr);
            } else {
                alert_pop(1, '일치하는 정보가 없습니다.');
            }
        },
        error: function (xhr, status, error) {
            console.error('에러 발생:', error);
            alert_pop(1, '임시 비밀번호 전송 중 오류가 발생했습니다.');
        }
    });
}

function sendTemporaryPasswordEmail(memberId, memberEmail, memberNameKr) {
    const url = "/auth/sendEmail";

    console.log(memberId, memberEmail, memberNameKr);

    // 데이터를 JSON 형식으로 변환
    const requestData = JSON.stringify({
        memberId: memberId,
        memberEmail: memberEmail,
        memberNameKr: memberNameKr
    });

    $.ajax({
        beforeSend: function (xhr) {
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token);
        },
        type: "POST",
        url: url,
        data: requestData, // JSON  데이터를 요청 본문에 포함
        contentType: "application/json", // 요청 본문의 컨텐츠 타입을 JSON으로 설정
        success: function (response) {
            alert_pop(1, '임시 비밀번호 이메일이 성공적으로 전송되었습니다.');
            modal_onoff(6,2);
        },
        error: function (xhr, status, error) {
            console.error('에러 발생:', error);
            alert_pop(1, '임시 비밀번호 이메일 전송 중 오류가 발생했습니다.');
        },
        complete: function () {
            // Ajax 요청이 완료되면 로딩 표시를 숨깁니다.
            hideLoading();
        }
    });
}

// 로딩 표시를 보여주는 함수
function showLoading() {
    const loading = document.querySelector('.loading');
    loading.style.display = 'block';
}

// 로딩 표시를 숨기는 함수
function hideLoading() {
    const loading = document.querySelector('.loading');
    loading.style.display = 'none';
}

document.getElementById('sendEmailBtn').addEventListener('click', sendTemporaryPassword);

function checkLogin(){


}