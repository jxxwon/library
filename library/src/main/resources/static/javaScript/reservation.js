const room_menu = document.querySelectorAll('.room_menu');
//console.log(room_menu);

function activateMenuItem() {
	// 모든 메뉴 항목에서 '활성' 클래스 제거
	room_menu.forEach(item => item.classList.remove('active'));

	// 클릭한 메뉴 항목에 '활성' 클래스 추가
	this.classList.add('active');
}

//기본설정으로 첫번째 li는 활성화
room_menu[0].classList.add('active');

// 클릭한 메뉴 항목에 '활성' 클래스 추가
room_menu.forEach(item => item.addEventListener('click', activateMenuItem));

function showInfo(menu) {
	var url = "/reservation/" + menu;
	const room_container = document.getElementById('room_container');
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			room_container.innerHTML = xhr.responseText;

			const each_seat = document.querySelectorAll('.each_seat');

			each_seat.forEach(item => item.addEventListener('click', activateEach_seat));
		}
	};
	xhr.send();
}

let popupWindow;
let f;
let seatNumber;
let whichRoom;
function activateEach_seat() {
	seatNumber = this.textContent;
	let roomDiv = document.querySelector('.whichRoom');
	whichRoom = roomDiv.innerText.split('\n')[0];
	console.log(whichRoom);
	let reserve = confirm(seatNumber + "를 예약하시겠습니까?");
	if (reserve) {
		let url = "/reservation/roomPopUp?seatId=" + seatNumber + "&room=" + whichRoom;

		var popupWidth = 500;
		var popupHeight = 500;
		var leftPosition = (window.innerWidth - popupWidth) / 2;
		var topPosition = (window.innerHeight - popupHeight) / 2;

		// 팝업창 열 때 URL을 지정하여 열기
		console.log(seatNumber);
		console.log(url);
		popupWindow = window.open(url, 'Seat Popup', 'width=' + popupWidth +
			',height=' + popupHeight + ',left=' + leftPosition + ',top=' + topPosition);
		popupWindow.onload = function() {
			const cancelBtn = popupWindow.document.querySelector('.popCancelBtn');
			cancelBtn.addEventListener('click', closePopUp);
			const reserveBtn = popupWindow.document.querySelector('.reserveBtn')
			f = popupWindow.document.getElementById('f');
			console.log(f);
			reserveBtn.addEventListener('click', reserveSubmit);
		};
	}
}


function closePopUp() {
	console.log("취소버튼")
	if (popupWindow) {
		console.log("closePopUp");
		popupWindow.close();
	}
}
function reserveSubmit() {
	let result = confirm("예약 하시겠습니까?");
	if(result){
		var reqData = { seatId: seatNumber, room: whichRoom }
		// JSON.stringify(reqData) : 자바스크립트 object 자료형을 JSON 문자열 자료형으로 변환
		// 네트워크(인터넷)로 데이터를 전달하기 위해서 변환.
		console.log('JSON.stringify(reqData) : ' + JSON.stringify(reqData))

		reqData = JSON.stringify(reqData);
		const xhr = new XMLHttpRequest();
		xhr.open('POST', "/reservation/reserveProc");
		xhr.setRequestHeader('content-type', 'application/json');
		xhr.send(reqData);

		f.submit();
		popupWindow.close();
	}
}

