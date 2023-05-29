package hjh.capstone.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import hjh.capstone.domain.notification.FCMNotification;
import org.springframework.stereotype.Service;

@Service
public class FCMService
{
    public void sendNotification(String title, String body, String token) throws FirebaseMessagingException
    {
        Message message = FCMNotification.createNotificationMessage(title, body, token);
        String messageId = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + messageId);
    }
}
