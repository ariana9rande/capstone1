// Service Worker 등록
navigator.serviceWorker.register('service-worker.js')
    .then(registration => {
        console.log('Service Worker 등록 완료:', registration);
    })
    .catch(error => {
        console.log('Service Worker 등록 실패:', error);
    });

// 알림 이벤트 수신
self.addEventListener('push', event => {
    if (event.data) {
        const notificationData = event.data.json();

        // 알림 생성
        self.registration.showNotification(notificationData.title, {
            body: notificationData.body,
            icon: '/images/favicon.ico'
        });
    }
});
