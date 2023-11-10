//상품 등록
function saveProduct(obj){

}

//상품 수정
function updateProduct(obj){

    let productNo = obj.dataset.id;
    console.log(productNo);

    let url = '/product/update/' + productNo;
    console.log(url)

    $.ajax({
        url : url,
        type : 'get',
        dataType : 'html',
        success: function(e){

        }

    })


}


//상품 삭제
function deleteProduct(obj){

}

// 치킨 계산기
function chickenCal1() {
    // 모달 보이기
    document.getElementById('proteinModal').style.display = "block";
}


let multiplier = 1;
let productProtein = document.querySelector('button[data-toggle="modal"]').dataset.protein;

function setMultiplier(value) {
    multiplier = value;
}

function calculateProtein() {
    let weight = document.getElementById('weight').value;
    let neededProtein = weight * multiplier;
    let neededProduct = Math.ceil(neededProtein / productProtein);

    document.getElementById('result').innerText =
        '당신의 몸무게에 필요한 단백질량은 ' + neededProtein + 'g 입니다. \n' +
        ' 이를 충족하기 위해선 해당 제품을 ' + neededProduct + '개 먹어야 합니다.';
}