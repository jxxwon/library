 // 현재 URL을 가져오는 JavaScript 함수
    function getCurrentURL() {
      return window.location.href;
    }

    // URL에 따라 버튼 색상을 변경하는 JavaScript 함수
    function setButtonColorByURL() {
      var currentURL = getCurrentURL();
      console.log(document.getElementById("login"))
      
      // 원하는 URL 패턴에 따라 버튼 색상을 변경합니다.
      if (currentURL.includes("login")) {
        document.getElementById("login").classList.add("active");
        document.getElementById("register").classList.remove("active");
      } else if (currentURL.includes("register")) {
        document.getElementById("register").classList.add("active");
        document.getElementById("login").classList.remove("active");
      } else {
        // 기본적으로 어떤 패턴에도 해당하지 않을 경우 버튼 색상을 초기화합니다.
        document.getElementById("redButton").classList.remove("red-button");
        document.getElementById("blueButton").classList.remove("blue-button");
      }
    }

    // 페이지 로드 시 버튼 색상을 설정합니다.
    setButtonColorByURL();

    // 페이지 URL이 변경될 때마다 버튼 색상을 업데이트합니다.
    window.onpopstate = function () {
      setButtonColorByURL();
    };