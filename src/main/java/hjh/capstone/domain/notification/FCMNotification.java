package hjh.capstone.domain.notification;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class FCMNotification
{

    public static Message createNotificationMessage(String title, String body, String token)
    {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        return Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build();
    }
}