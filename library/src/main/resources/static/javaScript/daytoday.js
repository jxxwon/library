window.addEventListener("DOMContentLoaded", function () {
    const today = new Date();
    const sevenDaysLater = new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000);

    // 강의 기간 시작 날짜
    flatpickr(".lecture.start-date", {
        dateFormat: "Y-m-d",
        minDate: "today", // 오늘 이전의 날짜는 선택 불가능
        defaultDate: today,
    });

    // 강의 기간 종료 날짜
    flatpickr(".lecture.end-date", {
        dateFormat: "Y-m-d",
        minDate: "today", // 오늘 이전의 날짜는 선택 불가능
        defaultDate: sevenDaysLater,
    });

    // 접수 기간 시작 날짜
    flatpickr(".registration.start-date", {
        dateFormat: "Y-m-d",
        minDate: "today", // 오늘 이전의 날짜는 선택 불가능
        defaultDate: today,
    });

    // 접수 기간 종료 날짜
    flatpickr(".registration.end-date", {
        dateFormat: "Y-m-d",
        minDate: "today", // 오늘 이전의 날짜는 선택 불가능
        defaultDate: sevenDaysLater,
    });

    // 폼 제출
    const form = document.querySelector("form");
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        // 선택된 날짜 값 가져오기
        const lectureStartDate = document.querySelector(".lecture.start-date").value;
        const lectureEndDate = document.querySelector(".lecture.end-date").value;
        const registrationStartDate = document.querySelector(".registration.start-date").value;
        const registrationEndDate = document.querySelector(".registration.end-date").value;

        // 서버로 전송할 데이터 형식에 맞게 가공
        const data = {
            "lecture-start-date": lectureStartDate,
            "lecture-end-date": lectureEndDate,
            "registration-start-date": registrationStartDate,
            "registration-end-date": registrationEndDate
        };

        // 여기서 data를 서버로 보내는 로직을 작성하면 됩니다.
        // 예를 들어, AJAX 요청을 사용하여 서버로 데이터를 전송할 수 있습니다.
    });
});
