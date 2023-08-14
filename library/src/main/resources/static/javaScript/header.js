const header_nav = document.querySelector(".header_nav");
const toggle_box = document.querySelectorAll(".toggleBox");
console.log("header_nav", header_nav);
	
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


function toggleMenu(){
	var notification = document.getElementById('notification');
	var expanded = notification.getAttribute('aria-hidden') === 'false';
	
	if (expanded) {
        notification.setAttribute('aria-hidden', 'true');
        notification.classList.remove('show'); // 알림 메뉴를 감추기 위해 클래스 제거
    } else {
        notification.setAttribute('aria-hidden', 'false');
        notification.classList.add('show'); // 알림 메뉴를 표시하기 위해 클래스 추가
    }
	
	console.log('안됨???')
}
