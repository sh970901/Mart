// document.addEventListener('DOMContentLoaded', function() {
//     fetchMemberInfo();
// });
//
// async function fetchMemberInfo() {
//     try {
//         const response = await fetch(`/api/v1/m/member/${}`, {
//             method: 'GET',
//             headers: {
//                 'Content-Type': 'application/json'
//             }
//         });
//
//         if (!response.ok) {
//             throw new Error('Network response was not ok ' + response.statusText);
//         }
//
//         const memberInfo = await response.json();
//         document.getElementById('memberName').textContent = memberInfo.name;
//         document.getElementById('memberEmail').textContent = memberInfo.email;
//         document.getElementById('memberCoin').textContent = memberInfo.coin;
//
//     } catch (error) {
//         console.error('There was a problem with the fetch operation:', error);
//     }
// }