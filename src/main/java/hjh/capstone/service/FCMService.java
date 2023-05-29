package hjh.capstone.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import hjh.capstone.domain.notification.FCMNotification;
import org.springframework.stereotype.Service;

@Service
public class FCMService
{
    private final MemberService memberService;

    public FCMService(MemberService memberService)
    {
        this.memberService = memberService;
    }

    public void sendNotification(String token, String title, String body)
    {
        if(!memberService.verifyFCMToken(token))
        {
            System.out.println("유효하지 않은 FCM token");
            return;
        }

        try
        {
            Message message = FCMNotification.createNotificationMessage(token, title, body);
            String messageId = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + messageId);
        }
        catch (FirebaseMessagingException e)
        {
            System.out.println("Failed to send message: " + e.getMessage());
        }
    }

}
