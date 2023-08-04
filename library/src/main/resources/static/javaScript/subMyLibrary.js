 // 현재 URL을 가져오는 JavaScript 함수
    function getCurrentURL() {
      return window.location.href;
    }

    // URL에 따라 버튼 색상을 변경하는 JavaScript 함수
    function setButtonColorByURL() {
      var currentURL = getCurrentURL();
      
      // 원하는 URL 패턴에 따라 버튼 색상을 변경합니다.
      if (currentURL.includes("myInfo")) {
		  console.dir(document.getElementById("subMyInfo"));
        document.getElementById("subMyInfo").classList.add("active");
      } else if(currentURL.includes("myInquiry")){
	      document.getElementById("subMyInquiry").classList.add("active");
	  } else {   // 기본적으로 어떤 패턴에도 해당하지 않을 경우 버튼 색상을 초기화합니다.
		  
	  }
		  
    }

    // 페이지 로드 시 버튼 색상을 설정합니다.
    setButtonColorByURL();

    // 페이지 URL이 변경될 때마다 버튼 색상을 업데이트합니다.
    window.onpopstate = function () {
      setButtonColorByURL();
    };
    