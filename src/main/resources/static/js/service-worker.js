self.addEventListener('push', function(event) {
    const title = '푸시 알림 제목';
    const options = {
        body: '푸시 알림 내용',
        icon: '/path/to/icon.png',
        // 추가적인 옵션 설정 가능
    };

    event.waitUntil(
        self.registration.showNotification(title, options)
    );
});
