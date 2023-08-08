const header_nav = document.querySelector(".header_nav");
const toggle_box = document.querySelectorAll(".toggleBox");
console.log("header_nav", header_nav);
	
header_nav.addEventListener("mouseover", () => {
    //item.classList.add("toggled"); // 추가적인 스타일링을 위한 클래스 추가
    toggle_box.forEach(item => item.style.display = 'block');
    
});

header_nav.addEventListener("mouseout", () => {
   // item.classList.remove("toggled"); // 호버 상태를 벗어날 때 클래스 제거
     toggle_box.forEach(item => item.style.display = 'none');
});
