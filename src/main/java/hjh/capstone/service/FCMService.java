package hjh.capstone.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import hjh.capstone.domain.notification.FCMNotification;
import org.springframework.stereotype.Service;

@Service
public class FCMService
{
    public void sendNotification(String token, String title, String body)
    {
        try
        {
            Message message = FCMNotification.createNotificationMessage(token, title, body);
            String messageId = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + messageId);
        }
        catch (FirebaseMessagingException e)
        {
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }
}
