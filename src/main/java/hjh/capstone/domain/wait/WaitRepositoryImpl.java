package hjh.capstone.domain.wait;

import hjh.capstone.domain.member.Member;
import hjh.capstone.domain.restaurant.Restaurant;
import hjh.capstone.service.MemberService;
import hjh.capstone.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class WaitRepositoryImpl implements WaitRepository
{
    private final EntityManager em;
    private final RestaurantService restaurantService;
    private final MemberService memberService;

    @Autowired
    public WaitRepositoryImpl(EntityManager em, RestaurantService restaurantService, MemberService memberService)
    {
        this.em = em;
        this.restaurantService = restaurantService;
        this.memberService = memberService;
    }

    @Override
    public List<Object[]> countAllByRestaurantAndMemberIsNull()
    {
        return em.createQuery(
                        "select w.restaurant.id, count(w) from Wait w where w.member is null group by w.restaurant.id",
                        Object[].class)
                .getResultList();
    }

    @Override
    public List<Wait> findAll()
    {
        return em.createQuery("SELECT w FROM Wait w", Wait.class)
                .getResultList();
    }

    @Override
    public List<Wait> findByRestaurantIdOrderByStartTimeAsc(Long restId)
    {
        return em.createQuery("SELECT w FROM Wait w WHERE w.restaurant.restId = :restId ORDER BY w.startTime ASC",
                        Wait.class)
                .setParameter("restId", restId)
                .getResultList();
    }

    public Wait createWait(Long restId, Long memberId)
    {
        List<Wait> waits = findByRestaurantIdOrderByStartTimeAsc(restId);
        Restaurant restaurant = restaurantService.findRestaurant(restId);
        Optional<Member> member = memberService.findMember(memberId);
        int waitNumber = waits.isEmpty() ? 1 : waits.get(waits.size() - 1).getWaitNumber() + 1;
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime startTime = LocalDateTime.parse(formattedDateTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Wait wait = new Wait(waitNumber, startTime, restaurant, member.orElse(null));
        save(wait);
        return wait;
    }

    @Override
    public Wait save(Wait wait)
    {
        em.persist(wait);
        return wait;
    }

    @Override
    public int countByRestaurantAndMemberIsNull(Restaurant restaurant)
    {
        return em.createQuery("select count(w) from Wait w where w.restaurant = :restaurant and w.member is null",
                        Long.class)
                .setParameter("restaurant", restaurant)
                .getSingleResult()
                .intValue();
    }

    @Override
    public int calculateWaitTime(Restaurant restaurant, LocalDateTime startTime)
    {
        List<LocalDateTime> startTimes = em.createQuery(
                        "select w.startTime from Wait w where w.restaurant = :restaurant order by w.startTime",
                        LocalDateTime.class)
                .setParameter("restaurant", restaurant)
                .getResultList();

        if (startTimes.isEmpty())
        {
            return 0;
        }

        return startTimes.size() * 10;
    }

    @Override
    public Long countWait(Long restId)
    {
        Restaurant restaurant = em.find(Restaurant.class, restId);
        List<Wait> waits = restaurant.getWaits();
        return waits != null ? (long) waits.size() : 0;
    }

    @Override
    public Long calcWaitTime(Long restId)
    {
        return countWait(restId) * 10;
    }

    @Override
    public void deleteById(Long id)
    {
        Wait wait = em.find(Wait.class, id);
        if (wait != null)
        {
            em.remove(wait);
        }
    }

    @Override
    public void deleteByRestId(Long restId)
    {
        List<Wait> waits = findByRestaurantIdOrderByStartTimeAsc(restId);
        if (waits != null)
        {
            for (Wait wait : waits)
            {
                deleteById(wait.getId());
            }
        }
    }

    @Override
    public void deleteAll()
    {
        em.createQuery("DELETE FROM Wait ").executeUpdate();
    }

    @Override
    public Wait findById(Long waitId) {
        return em.find(Wait.class, waitId);
    }
}
