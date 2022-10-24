package MOCUMOCU.project.couponlog.service;

import MOCUMOCU.project.couponlog.dto.DayOfWeekAnalysisDTO;
import MOCUMOCU.project.couponlog.dto.GenderAnalysisDTO;
import MOCUMOCU.project.couponlog.dto.MonthAnalysisDTO;
import MOCUMOCU.project.couponlog.dto.TimeAnalysisDTO;
import MOCUMOCU.project.couponlog.entity.CouponLog;

import java.util.List;


public interface CouponLogService {

    void saveLog(CouponLog couponLog);

    List<CouponLog> findAllCouponLog(Long customerId);

    List<CouponLog> findAllMarketLog(Long marketId);

    List<TimeAnalysisDTO> timeAnalysis(Long marketId);

    List<DayOfWeekAnalysisDTO> dayOfWeekAnalysis(Long marketId);

    List<MonthAnalysisDTO> monthAnalysis(Long marketId);

    List<GenderAnalysisDTO> genderAnalysis(Long marketId);
}
