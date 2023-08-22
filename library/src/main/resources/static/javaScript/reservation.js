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
	} else{
		whichRoom = "SR"
	}
}

// 페이지 로드 시 버튼 색상을 설정합니다.
setButtonColorByURL();

// 페이지 URL이 변경될 때마다 버튼 색상을 업데이트합니다.
window.onpopstate = function() {
	setButtonColorByURL();
};
console.log("url에 따른 룸 이름 : ", whichRoom)



//각 좌석 클릭 리스너 등록
let each_seat = document.querySelectorAll('.each_seat');
//console.log(each_seat);
each_seat.forEach(item => item.addEventListener('click', activateEach_seat));


// 탭 클릭 시에 좌석 상태 업데이트
var reservedSeat = [];
let seatXhr;
let emptyNum;
let usingNum;
let empty_seat = document.querySelector('.empty_seat').textContent;
let using_seat = document.querySelector('.using_seat').textContent;
showReservedSeat(whichRoom);

//선택한 좌석에 className 추가하기.(안하면 새로고침시 다 사라짐.)
function showReservedSeat(whichRoom) {
	console.log(whichRoom);
	seatXhr = new XMLHttpRequest();
	seatXhr.open('POST', "room");
	//seatXhr.setRequestHeader('content-type', 'application/json');
	seatXhr.send(whichRoom);
	seatXhr.onreadystatechange = roomProc;
}

function roomProc() {
	console.log("roomProc");
	if (seatXhr.readyState === 4) {
		console.log("roomProc1");
		if (seatXhr.status === 200) {
			console.log("roomProc2");
			reservedSeat = JSON.parse(seatXhr.responseText); console.log("roomProc3");
			
			usingNum = reservedSeat.length;
			emptyNum = 96 - usingNum;
			
			updateSeatStatus();
			//reservedSeat = seatXhr.responseText;
			console.log("usingNum", usingNum);
			console.log(typeof emptyNum);
		} else {
			console.log('에러: ' + seatXhr.status);
		}
	}
}


// 초기 로딩 시 좌석 상태 업데이트
updateSeatStatus();

// 좌석 상태 업데이트 함수

//let emptyNum = Number(empty_seat);
//let usingNum = Number(using_seat);
console.log("empty_seat typeof : ", emptyNum);
console.log("using_seat : ", typeof usingNum);

function updateSeatStatus() {
	//empty_seat = emptyNum;
	//using_seat = usingNum;
	
	document.querySelector('.empty_seat').textContent = emptyNum;
	document.querySelector('.using_seat').textContent = usingNum;
	console.log("updateSeatStatus : ", empty_seat);
	console.log("updateSeatStatus : ", typeof empty_seat);
	//console.log(empty_seat );
	each_seat.forEach(item => {
		for (let i = 0; i < reservedSeat.length; i++) {
			if (item.textContent === String(reservedSeat[i])) {
				console.log("이고" + String(reservedSeat[i]));
				console.log("여기요", i);
				item.classList.add('using');

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
		} else {
			location.href = "/main";
			return;
		}
	}

	//좌석 번호, 아이디, 열람실 이름을 변수에 설정.
	console.log(this.classList);
	if(this.classList.contains("using")){
		alert("이미 예약된 좌석입니다.");
		return;
	}
	seatNumber = this.textContent;
	let roomDiv = document.querySelector('.whichRoom');
	recentRoom = roomDiv.innerText.split('\n')[0];

	let message = `열람실: ${recentRoom}\n좌석 번호: ${seatNumber}\n이름: ${userName}\n\n예약하시겠습니까?`; // 메시지 구성
	let isConfirmed = confirm(message); // confirm 다이얼로그 표시
		console.log("백엔드로 보낼때 룸이름 : ", whichRoom);
	if (isConfirmed) {
		// 사용자가 확인을 선택한 경우 처리할 내용
		console.log("사용자가 확인을 선택했습니다.");
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
	console.log("reserveProc");
	if (reserveXhr.readyState === 4) {
		if (reserveXhr.status === 200) {
			console.log(whichRoom);
			showReservedSeat(whichRoom);

			let response = reserveXhr.responseText;
			console.log(response);
			// 여기서 classList를 가져와서 addClassName 함수에 전달
			console.log(whichRoom);
			console.log(seatNumber);
			//let clickedSeat = document.querySelector(`.each_seat[data-room="${whichRoom}"][data-seat="${seatNumber}"]`);
			//console.log(clickedSeat);
			//clickedSeat.classList.add('using');
			//addClassName(response, classList)
			alert(response);

		} else {
			console.log('에러: ' + reserveXhr.status);
		}
	}
	//window.location.reload();
}

