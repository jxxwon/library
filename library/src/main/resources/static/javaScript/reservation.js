let whichRoom;
// 현재 URL을 가져오는 JavaScript 함수
function getCurrentURL() {
	return window.location.href;
}

// URL에 따라 버튼 색상을 변경하는 JavaScript 함수
function setButtonColorByURL() {
	var currentURL = getCurrentURL();
	// 원하는 URL 패턴에 따라 버튼 색상을 변경합니다.
	if (currentURL.includes("readingRoom1")) {
		whichRoom = "R1"
	} else if (currentURL.includes("readingRoom2")) {
		whichRoom = "R2"
	} else {
		whichRoom = "SR"
	}
}

// 페이지 로드 시 버튼 색상을 설정합니다.
setButtonColorByURL();

// 페이지 URL이 변경될 때마다 버튼 색상을 업데이트합니다.
window.onpopstate = function() {
	setButtonColorByURL();
};



//각 좌석 클릭 리스너 등록
let each_seat = document.querySelectorAll('.each_seat');
each_seat.forEach(item => item.addEventListener('click', activateEach_seat));


// 좌석 상태 업데이트 관련
var reservedSeat = [];
let seatXhr;
let emptyNum;
let usingNum;

showReservedSeat(whichRoom);

// 예약된 좌석 리스트를 가져와서 사용중임을 표시
function showReservedSeat(whichRoom) {
	seatXhr = new XMLHttpRequest();
	seatXhr.open('POST', "room");
	seatXhr.send(whichRoom);
	seatXhr.onreadystatechange = roomProc;
}

function roomProc() {
	if (seatXhr.readyState === 4) {
		if (seatXhr.status === 200) {
			reservedSeat = JSON.parse(seatXhr.responseText);
			usingNum = reservedSeat.length;
			emptyNum = 96 - usingNum;

			updateSeatStatus();
		} else {
			console.log('에러: ' + seatXhr.status);
		}
	}
}


// 초기 로딩 시 좌석 상태 업데이트
updateSeatStatus();


//선택한 좌석에 className 추가하기.(안하면 새로고침시 다 사라짐.)
function updateSeatStatus() {
	document.querySelector('.empty_seat').textContent = emptyNum;
	document.querySelector('.using_seat').textContent = usingNum;
	
	
	each_seat.forEach(item => {
		for (let i = 0; i < reservedSeat.length; i++) {
			if (item.textContent === String(reservedSeat[i].seatId)) {
				item.classList.add('using');
				if(sessionId ==  String(reservedSeat[i].userId)){
					document.querySelector('.my_seat').textContent = reservedSeat[i].seatId+"번";
					item.classList.add('mine');
				}
			}
			
		}
	});

}

//팝업으로 좌석 예약하기
let popupWindow;
//let popupForm;
let seatNumber;

let reserveXhr;

function activateEach_seat() {

	if (sessionId == "") {
		result = confirm("로그인 후 이용해주세요.")
		if (result) {
			location.href = "/login";
			return;
		} 
		return;
	}

	//좌석 번호, 아이디, 열람실 이름을 변수에 설정.
	if (this.classList.contains("using")) {
		alert("이미 예약된 좌석입니다.");
		return;
	}
	seatNumber = this.textContent;
	let roomDiv = document.querySelector('.whichRoom');
	recentRoom = roomDiv.innerText.split('\n')[0];

	let message = `열람실: ${recentRoom}\n좌석 번호: ${seatNumber}\n이름: ${userName}\n\n예약하시겠습니까?`; // 메시지 구성
	let isConfirmed = confirm(message); // confirm 다이얼로그 표시
	if (isConfirmed) {
		// 사용자가 확인을 선택한 경우 처리할 내용
		var reqData = { seatId: seatNumber, room: whichRoom }
		reqData = JSON.stringify(reqData);
		reserveXhr = new XMLHttpRequest();
		reserveXhr.open('POST', "/reservation/reserveProc");
		reserveXhr.setRequestHeader('content-type', 'application/json');
		reserveXhr.send(reqData);
		reserveXhr.onreadystatechange = reserveProc;

	}
}

function reserveProc() {
	if (reserveXhr.readyState === 4) {
		if (reserveXhr.status === 200) {
			showReservedSeat(whichRoom);

			let response = reserveXhr.responseText;
			alert(response);

		} else {
			console.log('에러: ' + reserveXhr.status);
		}
	}
	window.location.reload();
}

