console.log('subTop.js is loaded.');
  function getCurrentURL() {
      return window.location.href;
   }

const submyLibraryItems = document.querySelectorAll('.submyLibrary_menu');

function activateLibraryItems() {
	var currentURL = getCurrentURL();
	console.dir(currentURL);
	submyLibraryItems.forEach(item => {
		if(item.getAttribute('id').includes(currentURL)){
			item.classList.add('active');
		}
		item.classList.remove('active')
	});
}

// 클릭한 메뉴 항목에 '활성' 클래스 추가
submyLibraryItems.forEach(item => item.addEventListener('click', activateLibraryItems));