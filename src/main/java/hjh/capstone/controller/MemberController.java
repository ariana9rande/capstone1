package hjh.capstone.controller;

import hjh.capstone.domain.member.Member;
import hjh.capstone.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController
{

    private final MemberService memberService;

    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String joinForm()
    {
        return "members/join";
    }

    @PostMapping("members/new")
    public String register(MemberForm form)
    {
        Member member = new Member();
        member.setMemberName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model)
    {
        if(session.getAttribute("loginMember") != null)
        {
            model.addAttribute("message", "이미 로그인 중입니다.");
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, HttpSession session)
    {
        Member member = memberService.login(name, password);
        if (member == null)
        {
            return "login";
        }

        session.setAttribute("loginMember", member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model)
    {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberlist";
    }

}
