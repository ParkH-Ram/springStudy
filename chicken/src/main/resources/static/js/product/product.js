// 제품 상세 파트에서 수정 후 저장
function saveProduct(obj){

    let id = obj.dataset.id;
    console.log(id);
    let url = '/product/update';
    console.log(url)

    let productDto = new FormData();
    productDto.append('productNo', id);
    productDto.append('productWriter', $('#productWriter').val());
    productDto.append('productName', $('#productName').val());
    productDto.append('productCalories', $('#productCalories').val());
    productDto.append('productSodium', $('#productSodium').val());
    productDto.append('productCarbohydrate', $('#productCarbohydrate').val());
    productDto.append('productSugar', $('#productSugar').val());
    productDto.append('productCholesterol', $('#productCholesterol').val());
    productDto.append('productFat', $('#productFat').val());
    productDto.append('productProtein', $('#productProtein').val());

    let productImage = $('#productImage').get(0).files[0];
    if (productImage) {
        productDto.append('productImage', productImage);
    }

    console.log(productDto);
    console.log($('#productName').val());

    $.ajax({
        url : url,
        type : 'PATCH',
        data : productDto,
        dataType : 'html',
        contentType: false, // multipart/form-data를 사용하므로 false로 설정
        processData: false, // FormData를 사용하므로 false로 설정
        cache: false,

        beforeSend: function (xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token);
        },
        success: function(e){
            console.log(productDto)
            $('#updateBtn').css('display', 'block');
            $('#saveBtn').css('display', 'none');
            changeProductCard(e)
            location.replace(location.href);
        },
        error: function (request, status, error) {
            console.log("code: " + request.status)
            console.log("message: " + request.responseText)
            console.log("error: " + error);
        }
    })
}



// 업데이트 시 갈아 끼울 카드
function changeProductCard(html){
    let productCard = $('#productCard')
    productCard.children().remove();
    productCard.append(html);
}



//상품 수정
function updateProduct(){
    $('.form-control').attr('disabled', false);  // 폼 작동 안하게
    $('#updateBtn').css('display', 'none');      // 수정 버튼 가리고
    $('#saveBtn').css('display', 'block');       // 저장 버튼 키고
    $('#setMulti1').css('display', 'none');
    $('#setMulti1_2').css('display', 'none');
    $('#setMulti1_5').css('display', 'none');
    $('#setMulti2').css('display', 'none');
}



//상품 삭제
function deleteProduct(obj){
    let productNo = obj.dataset.id;
    let url = '/product/delete/' + productNo;

    $.ajax({
        url : url,
        type : 'post',
        cache : false,


        beforeSend: function (xhr) {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
            let token = $("meta[name='_csrf']").attr("content");
            let header = $("meta[name='_csrf_header']").attr("content");
            xhr.setRequestHeader(header, token);
        },

        success : function(e){
            alert(" 상품 삭제 됐습니다. ")
            location.replace("http://localhost:8080/product/info");

        },
        error : function(err){

        }
    })
}

// 치킨 계산기
function chickenCal1() {
    // 모달 보이기
    document.getElementById('proteinModal').style.display = "block";
}


/**
 *  제품 단백질 계산   -  계수 1 , 1.2 , 1.5 , 2
 * **/

let multiplier = 1;
let productProtein = document.querySelector('button[data-toggle="modal"]').dataset.protein;

function setMultiplier(value) {
    multiplier = value;
}

function calculateProtein() {
    let weight = document.getElementById('weight').value;
    let neededProtein = weight * multiplier;
    let neededProduct = Math.round((neededProtein / productProtein)*10) / 10;

    console.log(neededProduct);

    document.getElementById('result').innerText =
        '당신의 몸무게에 필요한 단백질량은 ' + neededProtein + 'g 입니다. \n' +
        ' 이를 충족하기 위해선 해당 제품을 ' + neededProduct + '개 먹어야 합니다.';
}


///////////////////////////사진 첨부 관련///////////////////////////
function attachPhoto() {
    const photoInput = document.getElementById("photo_input");
    photoInput.click(); // 숨겨진 파일 입력 요소 클릭

    photoInput.addEventListener("change", function () {
        const photoPreview = document.getElementById("photo_preview");
        const selectedFile = photoInput.files[0];

        if (selectedFile) {
            const objectUrl = URL.createObjectURL(selectedFile);
            photoPreview.src = objectUrl;
        }
    });
}

// 유효성 검증
$(function () {
    $.validator.setDefaults({
    });
    //validation
    $('#productForm').validate({ // 폼의 id를 수정해주세요.
        rules: {
            productName: {  // 제품명
                required: true,
            },
            productCalories: { // 칼로리
                required: true,
                number: true,
            },
            productProtein: { // 단백질
                required: true,
                number: true,
            },
            // 필요한 만큼 다른 필드에 대한 규칙을 추가하세요.
        },
        messages: {
            productName:{
                required:"제품명을 입력하세요."
            },
            productCalories:{
                required:"칼로리를 입력하세요.",
                number:"숫자만 입력 가능합니다."
            },
            productProtein:{
                required:"단백질을 입력하세요.",
                number:"숫자만 입력 가능합니다."
            },
            // 필요한 만큼 다른 필드에 대한 메시지를 추가하세요.
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});
