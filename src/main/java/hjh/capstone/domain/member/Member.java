package hjh.capstone.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberName;
    private String email;
    private String password;

    public Member(String memberName, String email, String password)
    {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
    }

    public Member()
    {
    }
}
