const submyLibraryItems = document.querySelectorAll('.submyLibrary_menu');
	console.log(submyLibraryItems);
	
	function activateMenuItem() {
		// 모든 메뉴 항목에서 '활성' 클래스 제거
		submyLibraryItems.forEach(item => item.classList.remove('active'));
	
		// 클릭한 메뉴 항목에 '활성' 클래스 추가
		this.classList.add('active');
	}
	
	//기본설정으로 첫번째 li는 활성화
	submyLibraryItems[0].classList.add('active');
	
	// 클릭한 메뉴 항목에 '활성' 클래스 추가
	submyLibraryItems.forEach(item => item.addEventListener('click', activateMenuItem));
	
	function showMyLibSub(menu) {
	
		  const url = "/myLibrary/"+  menu;
		  console.log(menu);
		  const myLibraryContent = document.querySelector('.myLibraryContent');
		  const xhr = new XMLHttpRequest();
		  xhr.open('GET', url, true);
		  xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		      myLibraryContent.innerHTML = xhr.responseText;
			  if(menu === "myInfo"){
			      const menuItems = document.querySelectorAll('.myInfo_menu');
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
			  }
		    }
				
			  };
		  xhr.send();
	}