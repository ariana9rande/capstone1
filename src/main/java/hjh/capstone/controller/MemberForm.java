package hjh.capstone.controller;

import hjh.capstone.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm
{
    private Long id;
    private String name;
    private String email;
    private String password;

    // getter, setter 생략

    public Member toEntity()
    {
        return new Member(name, email, password);
    }
}
