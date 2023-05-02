package hjh.capstone.controller;

import hjh.capstone.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm
{
    private Long id;

    @NotEmpty(message = "이메일을 입력해주세요")
    private String email;

    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;

    public Member toEntity()
    {
        return new Member(name, email, password);
    }
}