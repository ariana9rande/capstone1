self.addEventListener('push', function(event) {
    alert("service-worker.js 호출");
    event.waitUntil(
        fetch('/queue/notifications')
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
                alert("data : " + data);
                return self.registration.showNotification(title, options);
            })
    );
});