window.addEventListener("DOMContentLoaded", function () {
    // 폼 제출
    const form = document.querySelector("form");
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        // 선택된 대상 항목 가져오기
        const targetOptions = document.querySelectorAll(".select2 option:checked");
        const selectedTargets = Array.from(targetOptions).map((option) => option.value);

        // 서버로 전송할 데이터 형식에 맞게 가공
        const data = {
            target: selectedTargets,
            // 다른 필드들도 추가
        };

        // 여기서 data를 서버로 보내는 로직을 작성하면 됩니다.
        // 예를 들어, AJAX 요청을 사용하여 서버로 데이터를 전송할 수 있습니다.
    });
});
