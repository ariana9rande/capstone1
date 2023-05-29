package hjh.capstone.domain.member;

import hjh.capstone.domain.wait.Wait;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberName;
    private String email;
    private String password;
    private boolean isWaiting;
    private String token;

    @OneToMany(mappedBy = "member")
    private List<Wait> waits = new ArrayList<>();

    public Member(String memberName, String email, String password)
    {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
    }

    public Member()
    {
    }

    public boolean getIsWaiting()
    {
        return isWaiting;
    }

    public void setIsWaiting(boolean isWaiting)
    {
        this.isWaiting = isWaiting;
    }
}
