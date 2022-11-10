package MOCUMOCU.project.pointlog.service;

import MOCUMOCU.project.pointlog.dto.PointLogDTO;
import MOCUMOCU.project.pointlog.entity.PointLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PointLogService {

    void saveLog(PointLog pointLog);

    Slice<PointLogDTO> findAllPointLog(Long customerId, Pageable pageable);

}
