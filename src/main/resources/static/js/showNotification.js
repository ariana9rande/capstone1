// Service Worker로부터 푸시 알림 수신
self.addEventListener('push', event => {
    if (event.data) {
        const notificationData = event.data.json();
        // 푸시 알림 출력
        event.waitUntil(
            showNotification(notificationData.title, {
                body: notificationData.body,
                icon: '/images/favicon.ico'
            })
        );
    }
});

// 푸시 알림 출력
function showNotification(title, options) {
    // 알림 권한을 요청
    return self.registration.showNotification(title, options);
}

// 푸시 알림 권한 요청
self.addEventListener('pushsubscriptionchange', event => {
    event.waitUntil(
        self.registration.pushManager.getSubscription()
            .then(subscription => {
                if (subscription) {
                    return subscription.unsubscribe();
                }
            })
            .then(() => {
                return self.registration.pushManager.subscribe();
            })
            .then(newSubscription => {
                // 푸시 구독 정보를 서버로 전송
                return sendSubscriptionToServer(newSubscription);
            })
            .then(() => {
                console.log('푸시 구독 정보가 변경되었습니다.');
            })
    );
});

// 푸시 구독 정보를 서버로 전송하는 함수
function sendSubscriptionToServer(subscription) {
    const subscriptionJson = JSON.stringify(subscription);

    return fetch('/notice', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: subscriptionJson
    });
}
