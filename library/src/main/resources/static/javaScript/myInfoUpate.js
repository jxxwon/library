    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
				if(data.userSelectedType === 'R'){
					document.getElementById('address').value= data.roadAddress;
					console.log(typeof data.roadAddress)
				}else{
					document.getElementById('address').value= data.jibunAddress;
				}
				postCode = document.getElementById('postCode').value= data.zonecode;
            }
        }).open();
    }
    
	var xhr;
	var idCheckBtnClicked = false;
	function idCheckSend() {
		// Ajax 요청을 초기화합니다
		
		xhr = new XMLHttpRequest();
		xhr.open('post', 'exist');
		var idCheck = document.getElementById('id');
		var button = document.getElementById('idCheckBtn');
		console.dir(button);
		xhr.send(idCheck.value); 
		
 		xhr.onreadystatechange = idCheckResProc;
	}
				
	$(function() {
	  var placeholderDate = $('input[name="birth"]').attr('value');
	  var defaultStartDate = moment(placeholderDate, 'YYYY-MM-DD');
	
	  $('input[name="birth"]').daterangepicker({
	    singleDatePicker: true,
	    autoApply: true,
	    showDropdowns: true,
	    startDate: defaultStartDate, // 시작 날짜를 placeholderDate로 설정합니다.
	    minYear: 1940,
	    maxYear: parseInt(moment().format('YYYY'), 10),
	    locale: {
	      format: 'YYYY-MM-DD'
	    }
	  }, function(start, end, label) {
	    $('input[name="birth"]').val(start.format('YYYY-MM-DD'));
	  });
	});
	
	const menuItems = document.querySelectorAll('.menu-item');
	console.log(menuItems);
	
	function activateMenuItem() {
		// 모든 메뉴 항목에서 '활성' 클래스 제거
		menuItems.forEach(item => item.classList.remove('active'));
	
		// 클릭한 메뉴 항목에 '활성' 클래스 추가
		this.classList.add('active');
	}
	
	//기본설정으로 첫번째 li는 활성화
	menuItems[0].classList.add('active');
	
	// 클릭한 메뉴 항목에 '활성' 클래스 추가
	menuItems.forEach(item => item.addEventListener('click', activateMenuItem));

	function showInfo(menu) {
			const url = "/myLibrary/"+ menu;
			  const myInfoContainer = document.getElementById('myInfoContainer');
			  const xhr = new XMLHttpRequest();
			  xhr.open('GET', url, true);
			  xhr.onreadystatechange = function () {
			    if (xhr.readyState === 4 && xhr.status === 200) {
			      myInfoContainer.innerHTML = xhr.responseText;
			    }
			  };
			  xhr.send();
		}
	
	  // 페이지가 로드되면 초기 메뉴 선택 설정을 수행합니다.
	  window.onload = function () {
	    showInfo('updateInfo'); // 해당 메뉴에 대한 정보를 로드하여 표시합니다.
	  };
		  
