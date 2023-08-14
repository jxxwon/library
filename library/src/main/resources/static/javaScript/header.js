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
