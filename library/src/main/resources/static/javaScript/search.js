
// 현재 URL을 가져오는 JavaScript 함수
function getCurrentURL() {
	return window.location.href;
}

// URL에 따라 버튼 색상을 변경하는 JavaScript 함수
function setButtonColorByURL() {
	var currentURL = getCurrentURL();
	document.getElementById("subTotalSearch").classList.add("active");
	// 원하는 URL 패턴에 따라 버튼 색상을 변경합니다.
	if (currentURL.includes("totalSearch")) {
		document.getElementById("subTotalSearch").classList.add("active");
		//document.getElementById("subMyBookStatus").classList.remove("active");
		//document.getElementById("subMyInquiry").classList.remove("active");
	} /*else if (currentURL.includes("myBookStatus")) {
		document.getElementById("subMyBookStatus").classList.add("active");
		document.getElementById("subMyInquiry").classList.remove("active");
		document.getElementById("subMyInfo").classList.remove("active");
	} else if (currentURL.includes("myInquiry")) {
		document.getElementById("subMyInquiry").classList.add("active");
		document.getElementById("subMyBookStatus").classList.remove("active");
		document.getElementById("subMyInfo").classList.remove("active");
	}*/
}

// 페이지 로드 시 버튼 색상을 설정합니다.
setButtonColorByURL();

// 페이지 URL이 변경될 때마다 버튼 색상을 업데이트합니다.
window.onpopstate = function() {
	setButtonColorByURL();
};


function showMyLibSub(menu) {

	var url = "/myLibrary/" + menu;
	console.log(url);
	const myLibraryContent = document.querySelector('.myLibraryContent');
	const xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			myLibraryContent.innerHTML = xhr.responseText;

		}

	};
	xhr.send();
}

// 검색어에 해당하는 글씨 색 바꾸기
/*const title = document.querySelector(".title");
const searchInput = document.querySelector(".searchInput").value;

// 검색어를 정규식 패턴으로 변환하여, 대소문자 구분 없이 검색어를 찾습니다.
const regex = new RegExp(searchInput, "gi");

// 제목 내에서 검색어와 일치하는 부분을 찾아 강조 표시합니다.
const highlightedTitle = title.innerHTML.replace(regex, match => `<span class="highlight">${match}</span>`);

// 강조 표시한 결과를 제목에 적용합니다.
title.innerHTML = highlightedTitle;
*/

// 책 정보 모달
const customModal = null;
const modalContent = null;
const leaveButton = null;
const cancelButton = null;
const bookDtlButton = null;

function getBookDetail(){

if (customModal !== null)
	customModal = document.getElementById('searchModal');
if (modalContent !== null)
	modalContent = document.querySelector('.modal-content');
if (leaveButton !== null)
	leaveButton = document.getElementById('leaveButton');
if (cancelButton !== null)
	cancelButton = document.querySelector('.cancelButton');
	
	bookDtlButton =  document.querySelector('.bookDtlButton');
	
	
	
}


function openCustomModal() {
	customModal.style.display = 'flex';
}

function closeCustomModal() {
	customModal.style.display = 'none';
}

if (customModal !== null){
	customModal.addEventListener('click', function(event) {
		// 클릭된 요소가 모달 내부의 컨텐츠 영역이 아니면 모달을 닫습니다.
		/*  if (event.target !== modalContent) {*/
		customModal.style.display = 'none';
		/* }*/
	});
	
}

if (modalContent !== null){
	
	modalContent.addEventListener('click', function(event) {
		event.stopPropagation();
	});
}


let leaveXhr;
function leaveSeat() {
	let result = confirm("퇴실하시겠습니까?");
	if (result) {
		leaveXhr = new XMLHttpRequest();
		//leaveXhr.open('POST', "leaveProc");
		//leaveXhr.send(sessionId);
		//leaveXhr.onreadystatechange = leaveSeatProc;
	}
}

function leaveSeatProc() {
	if (leaveXhr.readyState === 4) {
		if (leaveXhr.status === 200) {
			//showReservedSeat(whichRoom);
			//updateSeatStatus();
			let response = leaveXhr.responseText;
			alert(response);
			//closeCustomModal();
		} else {
			console.log('에러: ' + leaveXhr.status);
		}
	}
	//좌석 예약 알람 기능을 바로 적용하기 위해 새로고침 적용.
	window.location.reload();
}

//퇴실 버튼
/*if (leaveButton !== null)
	leaveButton.addEventListener('click', leaveSeat);*/
if (cancelButton !== null)
	cancelButton.addEventListener('click', closeCustomModal);
