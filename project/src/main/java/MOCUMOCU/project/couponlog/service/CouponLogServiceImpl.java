package MOCUMOCU.project.couponlog.service;

import MOCUMOCU.project.couponlog.entity.CouponLog;
import MOCUMOCU.project.couponlog.repository.CouponLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponLogServiceImpl implements CouponLogService{
    private final CouponLogRepository couponLogRepository;


    @Override
    public void saveLog(CouponLog couponLog) {
        couponLogRepository.save(couponLog);
    }

    @Override
    public List<CouponLog> findAllCouponLog(Long customerId) {

        List<CouponLog> couponLogs = couponLogRepository.findByCustomerId(customerId);


        if (couponLogs.isEmpty()) {
            return null;
        } else{
            return couponLogs;
        }

    }

    @Override
    public List<CouponLog> findAllMarketLog(Long marketId) {

        List<CouponLog> couponLogs = couponLogRepository.findByMarketId(marketId);

        if (couponLogs.isEmpty()) {
            return null;
        } else{
            return couponLogs;
        }
    }
}
