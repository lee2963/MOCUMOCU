package MOCUMOCU.project.couponlog.repository;

import MOCUMOCU.project.couponlog.dto.CouponLogDTO;
import MOCUMOCU.project.couponlog.dto.MarketLogDTO;
import MOCUMOCU.project.couponlog.entity.CouponLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponLogRepositoryInter extends JpaRepository<CouponLog, Long> {

    @Query("select new MOCUMOCU.project.couponlog.dto.CouponLogDTO(c.month, c.day, c.hour, c.minute, c.usedStamp, c.market.marketName) from CouponLog c")
    Slice<CouponLogDTO> findByCustomerId(long customerId, Pageable pageable);

    @Query("select new MOCUMOCU.project.couponlog.dto.MarketLogDTO(c.month, c.day, c.usedStamp, c.hour, c.minute, c.customer.privacy.name) from CouponLog c")
    Slice<MarketLogDTO> findByMarketId(long marketId, Pageable pageable);


}
