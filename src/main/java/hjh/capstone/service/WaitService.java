package hjh.capstone.service;

import hjh.capstone.domain.restaurant.Restaurant;
import hjh.capstone.domain.wait.Wait;
import hjh.capstone.domain.wait.WaitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WaitService
{
    private final WaitRepository waitRepository;

    public List<Wait> findAllWaits()
    {
        return waitRepository.findAll();
    }

    public List<Object[]> countAllByRestaurantAndMemberIsNull()
    {
        return waitRepository.countAllByRestaurantAndMemberIsNull();
    }

    public int countByRestaurantAndMemberIsNull(Restaurant restaurant)
    {
        return waitRepository.countByRestaurantAndMemberIsNull(restaurant);
    }

    public int getWaitTime(Restaurant restaurant, LocalDateTime startTime)
    {
        return waitRepository.calculateWaitTime(restaurant, startTime);
    }

    public List<Wait> WaitListOrderByStartTimeAsc(Long restId)
    {
        return waitRepository.findByRestaurantIdOrderByStartTimeAsc(restId);
    }

    public Wait createNewWait(Long restId, Long memberId)
    {
        return waitRepository.createWait(restId, memberId);
    }

    @Transactional
    public Wait saveWait(Wait wait)
    {
        return waitRepository.save(wait);
    }

    public long getWaitCount(Long restId)
    {
        return waitRepository.countWait(restId);
    }

    public long getWaitTime(Long restId)
    {
        return waitRepository.calcWaitTime(restId);
    }
}
