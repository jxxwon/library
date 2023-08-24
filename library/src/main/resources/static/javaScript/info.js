 // 현재 URL을 가져오는 JavaScript 함수
function getCurrentURL() {
  return window.location.href;
}

// URL에 따라 버튼 색상을 변경하는 JavaScript 함수
function setButtonColorByURL() {
  var currentURL = getCurrentURL();
  var subNoticeElement = document.getElementById("subNotice");
  var subQnaElement = document.getElementById("subQna");
  var subFreeElement = document.getElementById("subFree");
  var subVolunteerElement = document.getElementById("subVolunteer");
  
  // 원하는 URL 패턴에 따라 버튼 색상을 변경합니다.
  if (currentURL.includes("notice") && subNoticeElement) {
      subNoticeElement.classList.add("active");
  } else if(currentURL.includes("qna") && subQnaElement){
	  subQnaElement.classList.add("active");
  }
}

// 페이지 로드 시 버튼 색상을 설정합니다.
setButtonColorByURL();


function selectFile(inputElement){
	const selectedFile = inputElement.files[0];
	
	// 파일 크기가 10MB를 초과하는 경우
	if(selectedFile){
		const fileSize = Math.floor(selectedFile.size / 1024 / 1024);
		if(fileSize > 10){
			alert('파일 크기는 최대 10MB까지 가능합니다.');
			inputElement.value='';
			return;
		}
	}
}

