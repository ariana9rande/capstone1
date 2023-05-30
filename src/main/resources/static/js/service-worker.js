self.addEventListener('push', function(event) {
    event.waitUntil(
        fetch('/queue/notifications') // 서버 API 엔드포인트로 요청을 보냅니다.
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                const title = data.title; // 서버에서 받아온 제목
                const body = data.body; // 서버에서 받아온 내용
                const icon = data.icon;

                const options = {
                    body: body,
                    icon: icon,
                    // 추가적인 옵션 설정 가능
                };

                return self.registration.showNotification(title, options);
            })
    );
});
