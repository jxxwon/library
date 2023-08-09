/*
<!-- 캘린더 추가 -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">
<script src="/javaScript/daytoday.js"></script>
*/
window.addEventListener("DOMContentLoaded", function () {
    const today = new Date();
    const sevenDaysLater = new Date(today.getTime() + 7 * 24 * 60 * 60 * 1000);

    // 강의 기간 시작 날짜
    const lectureStartDateInput = document.querySelector(".lecture.start-date");
    const lectureEndDateInput = document.querySelector(".lecture.end-date");

    const lectureEndDatePicker = flatpickr(lectureEndDateInput, {
        dateFormat: "Y-m-d",
        minDate: today, // 시작 날짜를 오늘 날짜 이후로 선택
        defaultDate: sevenDaysLater, // 보여지는 날짜를 오늘날짜 +7일 후로 보여줌
    });

    flatpickr(lectureStartDateInput, {
        dateFormat: "Y-m-d",
        minDate: "today", // 오늘 이전의 날짜는 선택 불가능
        defaultDate: today,
        onChange: function(selectedDates, dateStr) {
            // 강의 기간 종료 날짜의 최소 선택 가능한 날짜를 시작 날짜 이후로 설정
            lectureEndDatePicker.set("minDate", dateStr);
            // 종료 날짜의 보여지는 날짜를 시작 날짜+7일 로 설정
            lectureEndDatePicker.set("defaultDate", new Date(selectedDates[0].getTime() + 7 * 24 * 60 * 60 * 1000));
        }
    });

    // 접수 기간 시작 날짜
    const registrationStartDateInput = document.querySelector(".registration.start-date");
    const registrationEndDateInput = document.querySelector(".registration.end-date");

    const registrationEndDatePicker = flatpickr(registrationEndDateInput, {
        dateFormat: "Y-m-d",
        minDate: today, // 시작 날짜를 오늘 날짜 이후로 선택
        defaultDate: sevenDaysLater, // 보여지는 날짜를 오늘날짜 +7일 후로 보여줌
    });

    flatpickr(registrationStartDateInput, {
        dateFormat: "Y-m-d",
        minDate: "today", // 오늘 이전의 날짜는 선택 불가능
        defaultDate: today,
        onChange: function(selectedDates, dateStr) {
            // 접수 기간 종료 날짜의 최소 선택 가능한 날짜를 시작 날짜 이후로 설정
            registrationEndDatePicker.set("minDate", dateStr);
            // 종료 날짜의 보여지는 날짜를 시작 날짜+7일 로 설정
            registrationEndDatePicker.set("defaultDate", new Date(selectedDates[0].getTime() + 7 * 24 * 60 * 60 * 1000));
        }
    });

    // 폼 제출
/*    
	const form = document.querySelector("form");
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        // 선택된 날짜 값 가져오기
        const lectureStartDate = lectureStartDateInput.value;
        const lectureEndDate = lectureEndDateInput.value;
        const registrationStartDate = registrationStartDateInput.value;
        const registrationEndDate = registrationEndDateInput.value;

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
*/
});
