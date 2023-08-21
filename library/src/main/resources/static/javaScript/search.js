
// 현재 URL을 가져오는 JavaScript 함수
function getCurrentURL() {
	return window.location.href;
}

// URL에 따라 버튼 색상을 변경하는 JavaScript 함수
function setButtonColorByURL() {
	var currentURL = getCurrentURL();
	document.getElementById("subMyBookStatus").classList.add("active");
	// 원하는 URL 패턴에 따라 버튼 색상을 변경합니다.
	if (currentURL.includes("myInfo")) {
		document.getElementById("subMyInfo").classList.add("active");
		document.getElementById("subMyBookStatus").classList.remove("active");
		document.getElementById("subMyInquiry").classList.remove("active");
	} else if (currentURL.includes("myBookStatus")) {
		document.getElementById("subMyBookStatus").classList.add("active");
		document.getElementById("subMyInquiry").classList.remove("active");
		document.getElementById("subMyInfo").classList.remove("active");
	} else if (currentURL.includes("myInquiry")) {
		document.getElementById("subMyInquiry").classList.add("active");
		document.getElementById("subMyBookStatus").classList.remove("active");
		document.getElementById("subMyInfo").classList.remove("active");
	}
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
