package hjh.capstone.domain.wait;

import hjh.capstone.domain.member.Member;
import hjh.capstone.domain.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Wait
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int waitNumber;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Wait(int waitNumber, LocalDateTime startTime, Restaurant restaurant, Member member)
    {
        this.waitNumber = waitNumber;
        this.startTime = startTime;
        this.restaurant = restaurant;
        this.member = member;
    }

    public Wait()
    {

    }

    // getter, setter, constructor
}
