function successAlert(message) {
    Swal.fire({
        title: '',
        text: message,
        icon: 'success',
        showConfirmButton: true,
        timer: 2000,
        customClass: {
            popup: 'animate__animated animate__bounceIn' // 애니메이션 클래스
        }
    }).then((result) => {
        if (result.isConfirmed || result.dismiss === Swal.DismissReason.timer) {
            // 확인 버튼을 누르거나 타이머에 의해 알럿이 닫힌 경우
            location.reload();  // 페이지 새로 고침
        }
    });
}

function warnAlert(message) {
    Swal.fire({
        title: '',
        text: message,
        icon: 'warning',
        showConfirmButton: true,
        timer: 2000,
        customClass: {
            popup: 'animate__animated animate__bounceIn' // 애니메이션 클래스
        }
    })
}



function failAlert(message) {
    Swal.fire({
        title: '',
        text: message,
        icon: 'error',
        showConfirmButton: true,
        timer: 2000,
        customClass: {
            popup: 'animate__animated animate__bounceIn' // 애니메이션 클래스
        }
    }).then((result) => {
        if (result.isConfirmed || result.dismiss === Swal.DismissReason.timer) {
            // 확인 버튼을 누르거나 타이머에 의해 알럿이 닫힌 경우
            location.reload();  // 페이지 새로 고침
        }
    });
}