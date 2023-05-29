package hjh.capstone.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import hjh.capstone.domain.member.Member;
import hjh.capstone.domain.wait.Wait;
import hjh.capstone.service.FCMService;
import hjh.capstone.service.WaitService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController
{

    private final FCMService fcmService;
    private final WaitService waitService;

    public NotificationController(FCMService fcmService, WaitService waitService)
    {
        this.fcmService = fcmService;
        this.waitService = waitService;
    }

    @PostMapping("/{restId}/notice")
    public ResponseEntity<String> sendNotification(@PathVariable Long restId,
                                                   @RequestParam Long waitId)
    {
        // 알림을 전송할 대기 중인 손님 정보 가져오기
        Wait wait = waitService.findById(waitId);
        Member member = wait.getMember();

        // 알림 내용 설정
        String title = "대기번호 안내";
        String message = "대기번호 " + wait.getWaitNumber() + "번으로 이동하실 차례입니다.";

        // 알림 전송
        try
        {
            fcmService.sendNotification(member.getToken(), title, message);
            return ResponseEntity.ok("알림이 전송되었습니다.");
        }
        catch (FirebaseMessagingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
