// WebSocket 연결
const socket = new WebSocket('ws://localhost:8080/ws');

// 채널 구독
socket.onopen = function() {
    const channelId = 'notifications'; // 구독할 채널 ID
    const subscriptionMessage = {
        command: 'subscribe',
        channel: channelId
    };
    socket.send(JSON.stringify(subscriptionMessage));
};

// 메시지 수신 처리
socket.onmessage = function(event) {
    const message = JSON.parse(event.data);

    // 실제로 수신된 메시지의 구조를 확인하고 필요한 데이터를 추출하여 처리
    const notification = message.notification; // 예시: 수신된 메시지에 'notification' 속성이 있다고 가정

    if (notification) {
        const title = notification.title;
        const body = notification.body;
        const icon = notification.icon;

        // 알림을 표시하는 코드 예시
        new Notification(title, { body, icon });
    }
};