package hjh.capstone.configuration;

import hjh.capstone.domain.notification.Notification;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Component;

@Component
public class NotificationSubscriber {

    @SubscribeMapping("/notifications")
    public void handleNotification(Notification notification) {
        // 알림 처리 로직 작성
        System.out.println("Received notification: " + notification);
        // 원하는 작업 수행
    }
}
