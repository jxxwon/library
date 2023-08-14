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
      var url = "/reservation/"+ menu;
        const room_container = document.getElementById('room_container');
        const xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.onreadystatechange = function () {
          if (xhr.readyState === 4 && xhr.status === 200) {
            room_container.innerHTML = xhr.responseText;
            
            const each_seat = document.querySelectorAll('.each_seat');  
		
			each_seat.forEach(item => item.addEventListener('click', activateEach_seat));
			
			const popupDiv = document.getElementById('roomPopUp');
			const popupBtn = document.getElementById('popupBtn');
			popupBtn.addEventListener('click', popup);
			      
			          }
			        };
        xhr.send();
}

function activateEach_seat() {
	let seatNumber = this.textContent;
	console.log(seatNumber)
	
	// 확인을 누르면 팝업창 띄우기
		
	/*let reserve = confirm(seatNumber+"를 예약하시겠습니까?"); 
	if(reserve){
		console.log("확인");
	}*/
}

function popup(){
	console.log("팝업 버트")
	   var url = "/reservation/roomPopUp";
		const popupDiv = document.getElementById('roomPopUp');
		console.log(popupDiv.innerHTML);
        const xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
			console.log("팝업 버트2")
        xhr.onreadystatechange = function () {
          if (xhr.readyState === 4 && xhr.status === 200) {
            popupDiv.innerHTML = xhr.responseText;
            }
           }
	
}


