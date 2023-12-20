package hjh.capstone.controller;

import hjh.capstone.domain.member.Member;
import hjh.capstone.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    public String register(MemberForm form, Model model) throws Exception
    {
        Member member = new Member();
        System.out.println("member.id = " + member.getMemberId());
        member.setMemberName(form.getName());
        System.out.println("member.name = " + member.getMemberName());
        member.setEmail(form.getEmail());
        System.out.println("member.email = " + member.getEmail());
        member.setPassword(form.getPassword());
        System.out.println("member.password = " + member.getPassword());

        try
        {
            memberService.join(member);

            return "redirect:/";
        }
        catch(Exception e)
        {
            model.addAttribute("errorMessage", e.getMessage());
            return "members/join";
        }
    }

    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model)
    {
        if (session.getAttribute("loginMember") != null)
        {
            model.addAttribute("loggedInMessage", "이미 로그인 중입니다.");
        }
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, HttpSession session, Model model,
                        HttpServletRequest request)
    {
        Member member = memberService.login(name, password);
        if (member == null)
        {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "memebers/login";
        }

        session.setAttribute("loginMember", member);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate(); // 세션 무효화
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
