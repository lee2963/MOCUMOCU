package MOCUMOCU.project.pointlog.service;

import MOCUMOCU.project.pointlog.entity.PointLog;
import MOCUMOCU.project.pointlog.repository.PointLogRepository;
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
