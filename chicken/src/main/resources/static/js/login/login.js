$(function () {
    $.validator.setDefaults({
    });
    //validation
    $('#loginForm').validate({
        rules: {

            memberId: {    // input name 이랑 이름 똑같이
                required: true,    // required : 반드시 입력
            },
            memberPassword: {
                required: true,
                minlength: 5,
            },
            passwordConf: {
                required: true,
                equalTo: '#memberPassword',
            },
            memberName : {
                required : true
            },
            memberEmail :{
                required :true,
                email:true
            },
            memberBirth:{
                required:true
            },
            memberHeight:{
                required:true,
                pattern: /^\d+(\.\d)?$/,
            },
            memberWeight:{
                required:true,
                pattern: /^\d+(\.\d)?$/,
            },
            terms:{
                required:true
            }
        },
        messages: {
            memberId:{
                required:"아이디를 입력하세요."
            },
            memberPassword:{
                required:"비밀번호를 입력하세요.",
                minlength:"비밀번호는 최소 5자 이상이어야 합니다."
            },
            passwordConf:{
                required:"비밀번호 확인을 위해 다시 한번 비밀번호를 입력하세요.",
                equalTo:"입력한 두 비밀번호가 일치하지 않습니다."
            },
            memberName:{
                required:"성명을 입력하세요."
            },
            memberEmail :{
                required :"이메일 주소를 입력하세요.",
                email :"올바른 이메일 주소 형식으로 입력해주세요."
            },
            memberBirth:{
                required:"생년월일을 선택해주세요."
            },
            memberHeight:{
                required:"키를 입력해주세요.",
                pattern :"숫자와 소수점 첫째자리까지만 입력 가능합니다."
            },
            memberWeight:{
                required :"몸무게를 입력해주세요.",
                pattern :"숫자와 소수점 첫째자리까지만 입력 가능합니다."
            },
            memberGender: {
                required: "성별 선택"
            },
            terms:{required :"회원가입에 동의란에 체크해주셔야 합니다."}
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