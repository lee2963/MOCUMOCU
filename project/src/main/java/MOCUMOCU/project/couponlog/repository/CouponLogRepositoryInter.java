package MOCUMOCU.project.couponlog.repository;

import MOCUMOCU.project.couponlog.dto.CouponLogDTO;
import MOCUMOCU.project.couponlog.entity.CouponLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponLogRepositoryInter extends JpaRepository<CouponLog, Long> {

    @Query("select new MOCUMOCU.project.couponlog.dto.CouponLogDTO(c.month, c.day, c.hour, c.minute, c.coupon.amountStamp, c.market.marketName) from CouponLog c")
    List<CouponLogDTO> findByCustomerId(long customerId, Pageable pageable);
}
