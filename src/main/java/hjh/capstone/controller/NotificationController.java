package hjh.capstone.controller;

import hjh.capstone.domain.member.Member;
import hjh.capstone.domain.notification.Notification;
import hjh.capstone.domain.wait.Wait;
import hjh.capstone.service.WaitService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController
{
    private final WaitService waitService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationController(WaitService waitService, SimpMessagingTemplate simpMessagingTemplate)
    {
        this.waitService = waitService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/{restId}/notice")
    public String sendNotification(@PathVariable Long restId, @RequestParam Long waitId, @RequestBody Notification notification)
    {
        Wait wait = waitService.findById(waitId);
        Member member = wait.getMember();

        String title = "입장 안내";
        String body = "대기번호 " + wait.getWaitNumber() + "번 입장 5분 전입니다.";

        notification.setTitle(title);
        notification.setBody(body);

        simpMessagingTemplate.convertAndSendToUser(member.getMemberId().toString(), "/queue/notifications", notification);
//        fcmService.sendNotification(member.getToken(), title, body);
//        System.out.println("NotificationController : fcmService.sendNotification 호출 완료");

        return "redirect:/" + restId + "/manage";
    }

}
