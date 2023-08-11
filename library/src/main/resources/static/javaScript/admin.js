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
	var li = document.getElementById('activeLi').textContent;
	if(li == '인증 승인/반려'){
		document.getElementById('idLbl').style.display="none";
		document.getElementById('searchSelect').style.display="none";
		document.getElementById('memberSearch').style.display="none";
	}
}

// 회원인증 상세화면
function deputyApply(){
	var select = document.getElementById('userGroup');
	var value = select.options[select.selectedIndex].value; // select에서 선택된 option의 value
	if (value != 'child'){
		document.getElementById('deputy').style.display='none';
	} else {
		document.getElementById('deputy').style.display='inline-block';
	}
}

//회원인증 상세화면에서 보호자체크
function deChk() {
    var deputyChk1 = document.getElementById('deputyChk1');
    if(deputyChk1.checked == true){
    	document.getElementById('deputyGuardian').style.display='inline-block';
    } else{
    	document.getElementById('deputyGuardian').style.display='none';
    }

	var deputyChk3 = document.getElementById('deputyChk3');
	if(deputyChk3.checked == true){
    	document.getElementById('guardianPaper').style.display='inline-block';
	}else{
    	document.getElementById('guardianPaper').style.display='none';
	}
    console.log(deputyChk3.checked); // Use 'checked' to get the checkbox value
}

//회원인증 화면에서 인증체크
function certify(){
	var group = document.getElementById('userGroup');
	var groupValue = group.options[group.selectedIndex].value;
	
	if(groupValue == ""){
		alert('회원분류를 선택하세요.');
	} else {
		var paper = document.getElementById('paper');
		var paperValue = paper.options[paper.selectedIndex].value;
		if(paperValue == ""){
			alert('신청서류를 선택하세요.');
		} else {
			if(groupValue == 'child' && paperValue != 'resident'){
				alert('신청서류를 확인하세요.');
			} else if(groupValue == 'student' && (paperValue == 'driving') || paperValue == 'alien'){
				alert('신청서류를 확인하세요.')
			} else if(groupValue != 'foreign' && paperValue == 'alien'){
				alert('신청서류를 확인하세요.')
			} else {
				var f = document.getElementById('f');
				f.submit();
			}
		}
	}
	
}