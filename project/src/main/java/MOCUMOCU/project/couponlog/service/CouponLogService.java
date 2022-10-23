package MOCUMOCU.project.couponlog.service;

import MOCUMOCU.project.couponlog.entity.CouponLog;

import java.util.List;


public interface CouponLogService {

    void saveLog(CouponLog couponLog);

    List<CouponLog> findAllCouponLog(Long customerId);

    List<CouponLog> findAllMarketLog(Long marketId);
}
