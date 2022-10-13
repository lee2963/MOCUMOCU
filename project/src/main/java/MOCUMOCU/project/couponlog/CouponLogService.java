package MOCUMOCU.project.couponlog;

import java.util.List;

public interface CouponLogService {

    List<CouponLog> findAllCouponLog(Long customerId);

    List<CouponLog> findAllMarketLog(Long marketId);
}
