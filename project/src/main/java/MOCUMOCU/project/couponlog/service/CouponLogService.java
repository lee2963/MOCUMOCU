package MOCUMOCU.project.couponlog.service;

import MOCUMOCU.project.couponlog.dto.*;
import MOCUMOCU.project.couponlog.entity.CouponLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;


public interface CouponLogService {

    void saveLog(CouponLog couponLog);

    Slice<CouponLogDTO> findAllCouponLog(Long customerId, Pageable pageable);

    Slice<MarketLogDTO> findAllMarketLog(Long marketId, Pageable pageable);

    List<CouponLog> findAllMarketLog(Long marketId);

    List<TimeAnalysisDTO> timeAnalysis(Long marketId);

    List<DayOfWeekAnalysisDTO> dayOfWeekAnalysis(Long marketId);

    List<MonthAnalysisDTO> monthAnalysis(Long marketId);

    List<GenderAnalysisDTO> genderAnalysis(Long marketId, int day);

}
