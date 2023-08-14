const header_nav = document.querySelector(".header_nav");
const toggle_box = document.querySelectorAll(".toggleBox");
//console.log("header_nav", header_nav);
	
header_nav.addEventListener("mouseover", () => {
	header_nav.classList.add("show");
    toggle_box.forEach(item => {
        item.classList.add("show"); // 전환을 위한 클래스 추가
    });
});

header_nav.addEventListener("mouseout", () => 
{	header_nav.classList.remove("show");
    toggle_box.forEach(item => {
        item.classList.remove("show"); // 전환을 위한 클래스 제거
    });
});

// 헤더 스크롤에 따라 네비게이션 맨 위로 이동시키기
const mainController = document.querySelector('.mainController');

window.addEventListener('scroll', function () {
	//console.log(window.scrollY);
    if (window.scrollY > 40) {
    //console.log(window.scrollY);
        header_nav.classList.add('fix');
    } else {
        header_nav.classList.remove('fix');
    }
});

