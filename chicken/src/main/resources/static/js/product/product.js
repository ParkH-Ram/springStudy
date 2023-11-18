// 제품 상세 파트에서 수정 후 저장
function saveProduct(obj){

    let id = obj.dataset.id;
    console.log(id);
    let url = '/product/update';
    console.log(url)

    let productDto = {
        productNo :id,
        productName : $('#productName').val(),
        productCalories : $('#productCalories').val(),
        productSodium : $('#productSodium').val(),
        productCarbohydrate : $('#productCarbohydrate').val(),
        productSugar : $('#productSugar').val(),
        productCholesterol : $('#productCholesterol').val(),
        productFat : $('#productFat').val(),
        productProtein : $('#productProtein').val()
    };

    console.log(productDto);
    console.log($('#productName').val());

    $.ajax({
        url : url,
        type : 'PATCH',
        data : JSON.stringify(productDto),
        dataType : 'html',
        contentType: "application/json;charset='UTF-8'",
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
            console.log(e)
            changeProductCard(e)
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
    console.log(html)
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