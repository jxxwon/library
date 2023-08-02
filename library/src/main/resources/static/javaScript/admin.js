 // 현재 URL을 가져오는 JavaScript 함수
function getCurrentURL() {
  return window.location.href;
}

// URL에 따라 버튼 색상을 변경하는 JavaScript 함수
function setButtonColorByURL() {
  var currentURL = getCurrentURL();
  // 원하는 URL 패턴에 따라 버튼 색상을 변경합니다.
  if (currentURL.includes("Member")) {
    document.getElementById("subMember").classList.add("active");
  } 

}

// 페이지 로드 시 버튼 색상을 설정합니다.
setButtonColorByURL();

// 페이지 URL이 변경될 때마다 버튼 색상을 업데이트합니다.
window.onpopstate = function () {
  setButtonColorByURL();
}


// 회원관리 - 조회조건
function condition(){
	console.log('ㅠㅠ')
	var li = document.getElementById('activeLi').textContent;
	if(li == '인증 승인/반려'){
		document.getElementById('idLbl').style.display="none";
		document.getElementById('searchSelect').style.display="none";
		document.getElementById('memberSearch').style.display="none";
	}
}