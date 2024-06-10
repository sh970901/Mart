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
    const url = `/api/v1/items?page=${currentPage}&size=${size}`;

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