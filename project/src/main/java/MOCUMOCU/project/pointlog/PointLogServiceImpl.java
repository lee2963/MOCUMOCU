package MOCUMOCU.project.pointlog;

import MOCUMOCU.project.couponlog.CouponLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PointLogServiceImpl implements PointLogService {

    private final PointLogRepository pointLogRepository;

    @Override
    public void saveLog(PointLog pointLog) {
        pointLogRepository.save(pointLog);
    }

    @Override
    public List<PointLog> findAllPointLog(Long customerId) {
        return pointLogRepository.findByCustomerId(customerId);
    }
}
