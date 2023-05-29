package hjh.capstone.domain.notification;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class FCMNotification
{

    public static Message createNotificationMessage(String token, String title, String body)
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