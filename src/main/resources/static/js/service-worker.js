self.addEventListener('push', function(event) {
    event.waitUntil(
        fetch('/notifications')
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                const title = data.title;
                const body = data.body;
                const icon = data.icon;

                const uniqueTag = generateUniqueTag();

                const options = {
                    body: body,
                    icon: icon,
                    tag: uniqueTag
                };

                return self.registration.showNotification(title, options);
            })
            .catch(function(error) {
                console.error('Error sending notification:', error);
            })
    );
});

// 10초마다 알림 확인
setInterval(function() {
    self.dispatchEvent(new Event('push')); // push 이벤트 디스패치
}, 10000);

function generateUniqueTag() {
    const currentDate = new Date();
    const timestamp = currentDate.getTime();
    const uniqueTag = 'notification-' + timestamp;
    return uniqueTag;
}