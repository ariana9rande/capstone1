package hjh.capstone.service;

import hjh.capstone.domain.wait.Wait;
import hjh.capstone.domain.wait.WaitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Wait> waitListOrderByStartTimeAsc(Long restId)
    {
        return waitRepository.findByRestaurantIdOrderByStartTimeAsc(restId);
    }

    @Transactional
    public Wait createNewWait(Long restId, Long memberId)
    {
        return waitRepository.createWait(restId, memberId);
    }

    @Transactional
    public Wait saveWait(Wait wait)
    {
        return waitRepository.save(wait);
    }

    public Long getWaitCount(Long restId)
    {
        return waitRepository.countWait(restId);
    }

    public Long getWaitTime(Long restId)
    {
        return waitRepository.calcWaitTime(restId);
    }

    @Transactional
    public void deleteWaitById(Long waitId)
    {
        waitRepository.deleteById(waitId);
    }

    @Transactional
    public void deleteByRestId(Long restId)
    {
        waitRepository.deleteByRestId(restId);
    }

    @Transactional
    public void deleteAllWaits()
    {
        waitRepository.deleteAll();
    }

    public Wait findById(long waitId)
    {
        return waitRepository.findById(waitId);
    }

    @Transactional
    public void enterWait(Long waitId)
    {
        waitRepository.enterWait(waitId);
    }
}
