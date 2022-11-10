package MOCUMOCU.project.pointlog.repository;

import MOCUMOCU.project.pointlog.dto.PointLogDTO;
import MOCUMOCU.project.pointlog.entity.PointLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PointLogRepositoryInter extends JpaRepository<PointLog, Long> {

    @Query("select new MOCUMOCU.project.pointlog.dto.PointLogDTO(p.month, p.day, p.hour, p.minute, p.point, p.type) from PointLog p")
    Slice<PointLogDTO> findByCustomerId(long customerId, Pageable pageable);
}
