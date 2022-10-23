package MOCUMOCU.project.couponlog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CouponLogService {

    void saveLog(CouponLog couponLog);

    List<CouponLog> findAllCouponLog(Long customerId);

    List<CouponLog> findAllMarketLog(Long marketId);
}
