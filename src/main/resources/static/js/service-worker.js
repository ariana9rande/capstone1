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

                const options = {
                    body: body,
                    icon: icon
                };
                return self.registration.showNotification(title, options);
            })
            .catch(function(error) {
                console.error('Error sending notification:', error);
            })
    );
});

//1초마다 알림 확인
setInterval(function() {
    self.dispatchEvent(new Event('push')); // push 이벤트 디스패치
}, 1000);