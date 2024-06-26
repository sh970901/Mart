/*[[CDATA[*/
async function cancelCartItem(cartItemId) {
    try {
        const response = await fetch(`/api/v1/c/cart/items/${cartItemId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });
        const result = await response.json();

        if (response.ok  || result.resultCode === 200) {
            successAlert(result.data.description)
        }
        else {
            failAlert(result.data.description)
        }

    } catch (error) {
        failAlert("서버 통신이 실패했습니다.");
    }
}

async function cancelCart(cartItemId) {
    try {
        const response = await fetch(`/api/v1/c/cart/${cartItemId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        const result = await response.json();
        if (response.ok  || result.resultCode === 200) {
            successAlert(result.data.description)
        }
        else {
            failAlert(result.data.description)
        }
    } catch (error) {
        failAlert("서버 통신에 실패했습니다.")
    }
}

async function purchaseSelectedItems() {
    const selectedItems = [];
    const checkboxes = document.querySelectorAll('.itemCheckbox:checked');
    let totalPrice = 0;
    checkboxes.forEach(checkbox => {
        const itemId = checkbox.getAttribute('data-id');
        const itemName = checkbox.getAttribute('data-itemName');
        const quantity = checkbox.getAttribute('data-quantity');
        const cartItemId = checkbox.getAttribute('data-cartItemId');
        const itemStock = checkbox.getAttribute('data-itemStock')
        selectedItems.push({ itemId: itemId, quantity: quantity, cartItemId: cartItemId, itemStock: itemStock, itemName: itemName });
        totalPrice += parseInt(checkbox.getAttribute('data-price')) * quantity;
    });

    if (selectedItems.length === 0) {
        warnAlert('구매할 상품을 선택해주세요.');
        return;
    }
    var mbCoin = parseInt(document.getElementById('mbCoinValue').textContent.trim());
    if (totalPrice > mbCoin) {
        warnAlert('현재 코인이 부족합니다. \n 현재 코인: '+ mbCoin + "\n 총 가격: " + totalPrice);
        return;
    }

    for (let i = 0; i < selectedItems.length; i++) {
        const item = selectedItems[i];
        if (item.itemStock < item.quantity) {
            warnAlert("상품의 재고가 부족합니다. \n" + "상품 이름: " + item.itemName + "\n 구매 수량: " + item.quantity + "\n 상품 재고: " + item.itemStock);
            // 함수를 종료합니다.
            return;
        }
    }

    const orderData = {
        orderItemDtos: selectedItems
    };

    try {
        const response = await fetch('/api/v1/o/order/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(orderData)
        });

        const result = await response.json();

        if (response.ok  || result.resultCode === 200) {
            successAlert(result.data.description)
        }
        else {
            failAlert(result.data.description)
        }
    } catch (error) {
        failAlert("서버 통신에 실패했습니다.")
    }
}

async function addCart(button) {

    const itemId = button.getAttribute('data-item-id');
    const itemName = button.getAttribute('data-item-name');

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

        const result = await response.json();
        if (response.ok  || result.resultCode === 200) {
            successAlert(result.data.description)
        }
        else {
            failAlert(result.data.description)
        }

    } catch (error) {
        failAlert("서버 통신에 실패했습니다.")
    }
}
/*]]>*/
