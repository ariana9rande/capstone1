self.addEventListener('push', function(event) {
    console.log("service-worker.js 호출");
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
                console.log("data : " + data);
                return self.registration.showNotification(title, options);
            })
    );
});

// 1초마다 알림 확인
setInterval(function() {
    self.dispatchEvent(new Event('push')); // push 이벤트 디스패치
}, 1000);

// self.addEventListener("push", (event) => {
//     const payload = JSON.parse(event.data.text());
//     event.waitUntil(
//         self.registration.showNotification(payload.title, {
//             body: payload.body,
//             icon: payload.icon,
//         })
//     );
// });
//
// self.addEventListener("install", () => {
//     self.skipWaiting();
// })