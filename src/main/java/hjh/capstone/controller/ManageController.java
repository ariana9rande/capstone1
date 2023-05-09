package hjh.capstone.controller;

import hjh.capstone.domain.wait.Wait;
import hjh.capstone.service.WaitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ManageController
{
    private final WaitService waitService;

    public ManageController(WaitService waitService)
    {
        this.waitService = waitService;
    }

    @GetMapping("/{restId}/manage")
    public String manage(@PathVariable Long restId, Model model)
    {
        List<Wait> waits =  waitService.WaitListOrderByStartTimeAsc(restId);
        model.addAttribute("waits", waits);
        return "manage";
    }

    @GetMapping("/{restId}/waitlist")
    public String waitList(@PathVariable Long restId, Model model)
    {
        List<Wait> waits =  waitService.WaitListOrderByStartTimeAsc(restId);
        model.addAttribute("waits", waits);
        return "waitlist";
    }

    @GetMapping("/waitlist")
    public String EntireWaitList(Model model)
    {
        List<Wait> waits = waitService.findAllWaits();
        model.addAttribute("waits", waits);
        return "waitlist";
    }
}
