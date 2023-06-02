package hjh.capstone.controller;

import hjh.capstone.domain.member.Member;
import hjh.capstone.domain.notification.Notification;
import hjh.capstone.domain.wait.Wait;
import hjh.capstone.service.WaitService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController
{
    private final WaitService waitService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private Notification notification;

    public NotificationController(WaitService waitService, SimpMessagingTemplate simpMessagingTemplate)
    {
        this.waitService = waitService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/{restId}/notice")
    public String sendNotification(@PathVariable Long restId, @RequestParam Long waitId)
    {
        Wait wait = waitService.findById(waitId);
        Member member = wait.getMember();

        System.out.println("/" + restId + "/notice" + " postMapping 완료");

        String title = "입장 안내";
        String body = "대기번호 " + wait.getWaitNumber() + "번 입장 5분 전입니다.";
        String icon = "/images/favicon.ico";

        notification = new Notification(title, body, icon);

        simpMessagingTemplate.convertAndSend("/notifications", notification);
        System.out.println("notification.title = " + notification.getTitle());
        System.out.println("notification.body = " + notification.getBody());
        System.out.println("notification.icon = " + notification.getIcon());

        return "redirect:/" + restId + "/manage";
    }

    @GetMapping("/notifications")
    public ResponseEntity<Notification> getNotification()
    {
        if (notification != null)
        {
            return ResponseEntity.ok(notification); // 전역 변수 반환
        }
        else
        {
            return ResponseEntity.notFound().build(); // 알림이 없을 경우 404 에러 반환
        }
    }
}
