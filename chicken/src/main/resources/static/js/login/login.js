$(function () {
    $.validator.setDefaults({
    });
    //validation
    $('#loginForm').validate({
        rules: {
            memberName: {  // input name 이랑  맞춰야 한다.
                required: true,  // 안에 있는 내용은 속성 값들
            },
            memberEmail: {
                required: true,
                email: true,
            },
            memberPassword: {
                required: true,
                minlength: 5,
            },
            passwordConf: {
                required: true,
                equalTo: '#memberPassword',
            },
            terms: {
                required: true,
            }
        },
        messages: {
            memberId: {
                required: "아이디를 입력하세요. ",
            },
            email: {
                required: "이메일 주소를 입력하세요",
                email: "올바른 이메일 주소를 입력하세요",
            },
            password: {
                required: "비밀번호를 입력하세요",
                minlength: "5자리 이상 입력하세요",
            },
            passwordConf: {
                required: "비밀번호 확인을 입력하세요",
                equalTo: "입력한 비밀번호가 서로 일치하지 않습니다.",
            },


            terms: {
                required: "회원가입에 동의란에 체크하세요"
            }
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.input-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});