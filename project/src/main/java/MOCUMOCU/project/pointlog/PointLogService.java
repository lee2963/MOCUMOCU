package MOCUMOCU.project.pointlog;

import MOCUMOCU.project.couponlog.CouponLog;

import java.util.List;

public interface PointLogService {

    void saveLog(PointLog pointLog);

    List<PointLog> findAllPointLog(Long customerId);
}
