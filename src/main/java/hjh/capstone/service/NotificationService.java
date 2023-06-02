package hjh.capstone.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService
{

    @Value("${webpush.vapid.public-key}")
    private String vapidPublicKey;

    @Value("${webpush.vapid.private-key}")
    private String vapidPrivateKey;

}
