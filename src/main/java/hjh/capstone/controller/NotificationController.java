package hjh.capstone.controller;

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
        Wait wait = waitService.findById(waitId);
        Member member = wait.getMember();

        String title = "입장 안내";
        String body = "대기번호 " + wait.getWaitNumber() + "번 입장 5분 전입니다.";

        fcmService.sendNotification(member.getToken(), title, body);
        return ResponseEntity.ok("알림이 전송되었습니다.");
    }
}
