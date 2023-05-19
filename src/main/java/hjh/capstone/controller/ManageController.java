package hjh.capstone.controller;

import hjh.capstone.domain.member.Member;
import hjh.capstone.domain.wait.Wait;
import hjh.capstone.service.MemberService;
import hjh.capstone.service.WaitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ManageController
{
    private final WaitService waitService;
    private final MemberService memberService;

    public ManageController(WaitService waitService, MemberService memberService)
    {
        this.waitService = waitService;
        this.memberService = memberService;
    }

    @GetMapping("/{restId}/manage")
    public String manage(@PathVariable Long restId, Model model)
    {
        List<Wait> waits =  waitService.waitListOrderByStartTimeAsc(restId);
        model.addAttribute("waits", waits);
        return "manage";
    }

    //Delete 요청이 안 보내져서 임시로 GETMapping
    @GetMapping("/{restId}/manage/deleteWaits")
    public String manage_deleteWaitsByRestId(@PathVariable Long restId)
    {
        List<Wait> waits = waitService.waitListOrderByStartTimeAsc(restId);
        if(waits.size() > 0)
        {
            waitService.deleteByRestId(restId);
        }
        return "redirect:/{restId}/manage?manage";
    }

    //Delete 요청이 안 보내져서 임시로 GETMapping
    @GetMapping("/{restId}/manage/{waitId}/delete")
    public String manage_deleteWait(@PathVariable Long restId, @PathVariable Long waitId)
    {
        waitService.deleteWaitById(waitId);
        return "redirect:/{restId}/manage";
    }

    @GetMapping("/{restId}/notice")
    public String notice(@PathVariable Long restId, @RequestParam Long waitId, RedirectAttributes redirectAttributes) {
        Wait wait = waitService.findById(waitId);
        Member member = memberService.findMemberById(wait.getMember().getMemberId());

        // 알림 전송 로직 추가 필요

        redirectAttributes.addAttribute("check", true);
        redirectAttributes.addAttribute("notificationSent", true);
        redirectAttributes.addAttribute("notificationSentTime", LocalDateTime.now());

        return "redirect:/" + restId + "/manage?check={check}&notificationSent={notificationSent}&notificationSentTime={notificationSentTime}";
    }

    @GetMapping("/{restId}/waitlist")
    public String waitList(@PathVariable Long restId, Model model)
    {
        List<Wait> waits =  waitService.waitListOrderByStartTimeAsc(restId);
        model.addAttribute("waits", waits);
        return "waitlist";
    }

    //Delete 요청이 안 보내져서 임시로 GETMapping
    @GetMapping("/{restId}/waitlist/deleteWaits")
    public String deleteWaitsByRestId(@PathVariable Long restId)
    {
        List<Wait> waits = waitService.waitListOrderByStartTimeAsc(restId);
        if(waits.size() > 0)
        {
            waitService.deleteByRestId(restId);
        }
        return "redirect:/{restId}/waitlist?waitlist";
    }

    //Delete 요청이 안 보내져서 임시로 GETMapping
    @GetMapping("/{restId}/waitlist/{waitId}/delete")
    public String deleteWait(@PathVariable Long restId, @PathVariable Long waitId)
    {
        waitService.deleteWaitById(waitId);
        return "redirect:/{restId}/waitlist";
    }

    @GetMapping("/waitlist")
    public String EntireWaitList(Model model)
    {
        List<Wait> waits = waitService.findAllWaits();
        model.addAttribute("waits", waits);
        return "waitlist";
    }

    //Delete 요청이 안 보내져서 임시로 GETMapping
    @GetMapping("/waitlist/deleteWaits")
    public String deleteWaits()
    {
        waitService.deleteAllWaits();
        return "redirect:/waitlist";
    }

    //Delete 요청이 안 보내져서 임시로 GETMapping
    @GetMapping("/waitlist/{waitId}/delete")
    public String deleteWait(@PathVariable Long waitId)
    {
        waitService.deleteWaitById(waitId);
        return "redirect:/waitlist";
    }
}
