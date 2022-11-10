package MOCUMOCU.project.pointlog.service;

import MOCUMOCU.project.pointlog.dto.PointLogDTO;
import MOCUMOCU.project.pointlog.entity.PointLog;
import MOCUMOCU.project.pointlog.repository.PointLogRepository;
import MOCUMOCU.project.pointlog.repository.PointLogRepositoryInter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PointLogServiceImpl implements PointLogService {

    private final PointLogRepository pointLogRepository;

    private final PointLogRepositoryInter pointLogRepositoryInter;

    @Override
    public void saveLog(PointLog pointLog) {
        pointLogRepository.save(pointLog);
    }

    @Override
    public Slice<PointLogDTO> findAllPointLog(Long customerId, Pageable pageable) {
        return pointLogRepositoryInter.findByCustomerId(customerId, pageable);
    }
}
