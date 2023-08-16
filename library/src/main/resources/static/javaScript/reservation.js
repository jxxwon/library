const room_menu = document.querySelectorAll('.room_menu');

//열람실 탭 이동
function activateMenuItem() {
	room_menu.forEach(item => item.classList.remove('active'));

	this.classList.add('active');
}

room_menu[0].classList.add('active');

room_menu.forEach(item => item.addEventListener('click', activateMenuItem));





// 탭 클릭 시에 좌석 상태 업데이트
function showInfo(menu) {
	var url = "/reservation/" + menu;
	const room_container = document.getElementById('room_container');
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			room_container.innerHTML = xhr.responseText;
			each_seat = document.querySelectorAll('.each_seat');
			each_seat.forEach(item => item.addEventListener('click', activateEach_seat));
			
			// 좌석 상태 업데이트
			updateSeatStatus();
		}
	};
	xhr.send();
}

let each_seat = document.querySelectorAll('.each_seat');
each_seat.forEach(item => item.addEventListener('click', activateEach_seat));

// 초기 로딩 시 좌석 상태 업데이트
updateSeatStatus();

// 좌석 상태 업데이트 함수
function updateSeatStatus() {
	each_seat.forEach(item => {
		for (let i = 0; i < reservedSeat.length; i++) {
			if (item.textContent === String(reservedSeat[i])) {
				console.log("여기요",i);
				item.classList.add('using');
				
			} 
		}
	});
}

//팝업으로 좌석 예약하기
let popupWindow;
let f;
let seatNumber;
let whichRoom;
function activateEach_seat() {
	seatNumber = this.textContent;
	let roomDiv = document.querySelector('.whichRoom');
	whichRoom = roomDiv.innerText.split('\n')[0];
	//console.log(whichRoom);
	let reserve;
	if(this.classList.contains("using")){
		alert("이미 사용중인 좌석입니다.")
	}else{
		reserve = confirm(seatNumber + "를 예약하시겠습니까?");
	}
	if (reserve) {
		let url = "/reservation/roomPopUp?seatId=" + seatNumber + "&room=" + whichRoom;

		var popupWidth = 500;
		var popupHeight = 500;
		var leftPosition = (window.innerWidth - popupWidth) / 2;
		var topPosition = (window.innerHeight - popupHeight) / 2;

		// 팝업창 열 때 URL을 지정하여 열기
		//console.log(seatNumber);
		//console.log(url);
		popupWindow = window.open(url, 'Seat Popup', 'width=' + popupWidth +
			',height=' + popupHeight + ',left=' + leftPosition + ',top=' + topPosition);
		popupWindow.onload = function() {
			const cancelBtn = popupWindow.document.querySelector('.popCancelBtn');
			cancelBtn.addEventListener('click', closePopUp);
			const reserveBtn = popupWindow.document.querySelector('.reserveBtn')
			f = popupWindow.document.getElementById('f');
			//console.log(f);
			reserveBtn.addEventListener('click', reserveSubmit);
		};
	}
}


function closePopUp() {
	//console.log("취소버튼")
	if (popupWindow) {
		//console.log("closePopUp");
		popupWindow.close();
	}
}
function reserveSubmit() {
	let result = confirm("예약 하시겠습니까?");
	if(result){
		var reqData = { seatId: seatNumber, room: whichRoom }
		// JSON.stringify(reqData) : 자바스크립트 object 자료형을 JSON 문자열 자료형으로 변환
		// 네트워크(인터넷)로 데이터를 전달하기 위해서 변환.
		//console.log('JSON.stringify(reqData) : ' + JSON.stringify(reqData))

		reqData = JSON.stringify(reqData);
		const xhr = new XMLHttpRequest();
		xhr.open('POST', "/reservation/reserveProc");
		xhr.setRequestHeader('content-type', 'application/json');
		xhr.send(reqData);

		f.submit();
		popupWindow.close();
	}
}

