package hjh.capstone.domain.wait;

import hjh.capstone.domain.restaurant.Restaurant;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WaitRepository
{
    List<Object[]> countAllByRestaurantAndMemberIsNull();

    List<Wait> findAll();

    List<Wait> findByRestaurantIdOrderByStartTimeAsc(Long restId);

    Wait save(Wait wait);

    int countByRestaurantAndMemberIsNull(@Param("restaurant") Restaurant restaurant);

    int calculateWaitTime(Restaurant restaurant, LocalDateTime startTime);

    Wait createWait(Long restId, Long memberId);

    Long countWait(Long restId);

    Long calcWaitTime(Long restId);

    void deleteById(Long id);

    void deleteByRestId(Long restId);

    void deleteAll();

    Wait findById(Long waitId);
}
