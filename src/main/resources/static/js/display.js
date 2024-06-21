/*[[CDATA[*/
let currentPage = 0;  // 초기 페이지 값
const size = 18;  // 한 번에 가져올 아이템 수
let isLoading = false;  // 데이터 로딩 중 여부를 나타내는 플래그
let isFinished = false; // 모든 데이터를 가져왔는지 여부를 나타내는 플래그

// Intersection Observer 생성
const observer = new IntersectionObserver(entries => {
    // 마지막 아이템이 화면에 보이면 새로운 데이터 로드
    if (entries[0].isIntersecting && !isLoading && !isFinished) {  // 로딩 중이 아니고, 모든 데이터를 가져오지 않았을 때
        isLoading = true;  // 로딩 상태로 변경
        fetchMoreItems();  // 새로운 데이터 로드
        observer.unobserve(entries[0].target);
    }
}, {
    // 타겟이 뷰포트에 들어오는 지점을 지정합니다.
    threshold: 0.1
});

// 페이지 로드 시 첫 번째 데이터 가져오기
document.addEventListener('DOMContentLoaded', () => {
    observer.observe(document.querySelector('.item-list .item:last-child'));
});

function fetchMoreItems() {
    currentPage += 1;  // 페이지 값을 증가시킴
    const url = `/api/v1/i/items?page=${currentPage}&size=${size}`;

    // // 스켈레톤 표시
    document.getElementById('loading-skeleton').style.display = 'flex';

    fetch(url)
        .then(response => response.json())
        .then(rep => {
            if (rep.data.length === 0) { // 받아올 데이터가 없을 때
                isFinished = true; // 모든 데이터를 가져왔다고 플래그를 설정합니다.
                document.getElementById('loading-skeleton').style.display = 'none';
                return;
            }
            const itemList = document.getElementById('item-list');
            rep.data.forEach(item => {
                const newItem = document.createElement('div');
                newItem.classList.add('item');
                newItem.innerHTML = `
                    <img src="https://mdbootstrap.com/img/Photos/Horizontal/E-commerce/8-col/img%285%29.jpg" class="d-block w-100" alt="Exotic Fruits"/>
                    <h3>${item.itemName}</h3>
                    <p>${item.description}</p>
                    <p>재고: ${item.itemStock}개</p>
                    <span>가격: ₩${item.itemPrice}</span>
<!--                    <p><button class="btn btn-outline-primary me-2" th:onclick="|placeOrder(${item.itemId})|">바로 구매</button></p>-->
                    <p><button class="btn btn-outline-primary" onclick="showPurchaseModal(${item.itemId}, '${item.itemName}', ${item.itemStock},${item.itemPrice})" data-bs-toggle="modal" data-bs-target="#orderModal">바로 구매</button></p>
                    <p>
                        <button class="btn btn-outline-primary me-2"
                                th:attr="data-item-id=${item.itemId}, data-item-name=${item.itemName}"
                                onclick="addCartJs(${item.itemId}, '${item.itemName}')">
                            찜하기
                        </button>
                    </p>

                `;
                itemList.appendChild(newItem);
            });

            // 마지막 아이템 관찰 대상으로 추가
            observer.observe(document.querySelector('.item-list .item:last-child'));

            // 스켈레톤 숨기기
            document.getElementById('loading-skeleton').style.display = 'none';
            isLoading = false;  // 데이터 로딩 상태 해제
        })
        .catch(error => {
            console.error('Error fetching items:', error);
            document.getElementById('loading-skeleton').style.display = 'none';
            isLoading = false;  // 데이터 로딩 상태 해제
        });
}

function showPurchaseModal(itemId, itemName, itemStock, itemPrice) {

    fetch(`/api/v1/i/items/${itemId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
    .then(itemDetails => {
        console.log(itemDetails);
        document.getElementById('selectedItem').textContent = itemName;
        document.getElementById('itemStock').textContent = itemDetails.data.itemStock;
        document.getElementById('itemPrice').textContent = itemDetails.data.itemPrice;
        document.getElementById('totalPrice').textContent = itemDetails.data.itemPrice;
        document.getElementById('itemQuantity').textContent = 1;
        document.getElementById('selectedItemId').textContent = itemId;
        document.getElementById('selectedItemId').style.display = 'none';
    })
    .catch(error => {
        console.error('Error fetching item details:', error);
    });
}

function countItemQuantityP(){
    let itemQuantity = parseInt(document.getElementById('itemQuantity').textContent);

    let itemStock = parseInt(document.getElementById('itemStock').textContent);

    const itemPrice = parseInt(document.getElementById('itemPrice').textContent);
    if (itemQuantity < itemStock) { // 재고보다 많아지지 않도록 제한
        itemQuantity += 1;
        const newTotalPrice = itemQuantity * itemPrice;
        document.getElementById('itemQuantity').textContent = itemQuantity;
        document.getElementById('totalPrice').textContent = newTotalPrice;
    } else {
        alert("재고가 부족합니다.");
    }
}

function countItemQuantityM(){
    let itemQuantity = parseInt(document.getElementById('itemQuantity').textContent);
    let itemPrice = parseInt(document.getElementById('itemPrice').textContent);
    if (itemQuantity > 1) { // 재고보다 많아지지 않도록 제한
        itemQuantity -= 1;
        const newTotalPrice = itemQuantity * itemPrice;
        document.getElementById('itemQuantity').textContent = itemQuantity;
        document.getElementById('totalPrice').textContent = newTotalPrice;
    } else {
        alert("최소 주문은 한 개 이상입니다.");
    }
}

async function purchaseItem(){
    let itemStock = parseInt(document.getElementById('itemStock').textContent);
    let itemQuantity = parseInt(document.getElementById('itemQuantity').textContent);
    const itemId = parseInt(document.getElementById('selectedItemId').textContent);

    let totalPrice = parseInt(document.getElementById('totalPrice').textContent);

    var mbCoin = parseInt(document.getElementById('mbCoinValue').textContent.trim());

    if(itemStock <= 0){
         alert("재고가 존재하지 않습니다.");
         return;
    }

    if (totalPrice > mbCoin) {
        alert('현재 코인이 부족합니다. \n 현재 코인: '+ mbCoin + "\n 총 가격: " + totalPrice);
        return;
    }

    const orderData = {
        itemId: itemId, quantity: itemQuantity
    };

    try {
        const response = await fetch('/api/v1/o/order/create/item', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(orderData)
        });

        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }

        const result = await response.json();
        console.log('Order placed successfully:', result);

        // 주문 성공 시 사용자에게 알림 또는 페이지 이동
        alert(result.data.description);
        location.reload();

    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
        alert('Failed to place order: ' + error.message);
    }
}



async function addCartJs(itemId, itemName) {

    const cartData = {
        itemId: itemId, quantity: 1, itemName: itemName
    };

    try {
        const response = await fetch('/api/v1/c/cart/item', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cartData)
        });

        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }

        const result = await response.json();
        console.log('Car placed successfully:', result);

        alert(result.data.description);
        // location.href = '/order-success'; // 주문 성공 페이지로 이동

    } catch (error) {
        console.error('There was a problem with the fetch operation:', error);
        alert('Failed to place order: ' + error.message);
    }
    // 필요한 API 호출 등의 로직 추가
}
async function addCart(button) {

    const itemId = button.getAttribute('data-item-id');
    const itemName = button.getAttribute('data-item-name');
    await addCartJs(itemId, itemName);

}
/*]]>*/