package hjh.capstone.domain.wait;

import hjh.capstone.domain.restaurant.Restaurant;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WaitRepository
{
    List<Wait> findAll();

    List<Wait> findByRestaurantIdOrderByStartTimeAsc(Long restId);

    Wait save(Wait wait);

    int calculateWaitTime(Restaurant restaurant, LocalDateTime startTime);

    Wait createWait(Long restId, Long memberId);

    Long countWait(Long restId);

    Long calcWaitTime(Long restId);

    void deleteById(Long id);

    void deleteByRestId(Long restId);

    void deleteAll();

    Wait findById(Long waitId);

    void enterWait(Long waitId);
}
