package MOCUMOCU.project.couponlog.service;

import MOCUMOCU.project.couponlog.dto.*;
import MOCUMOCU.project.couponlog.entity.CouponLog;
import MOCUMOCU.project.couponlog.repository.CouponLogRepository;
import MOCUMOCU.project.couponlog.repository.CouponLogRepositoryInter;
import MOCUMOCU.project.customer.entity.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponLogServiceImpl implements CouponLogService{
    private final CouponLogRepository couponLogRepository;

    private final CouponLogRepositoryInter couponLogRepositoryInter;


    @Override
    public void saveLog(CouponLog couponLog) {
        couponLogRepository.save(couponLog);
    }

    @Override
    public Slice<CouponLogDTO> findAllCouponLog(Long customerId, Pageable pageable) {

        return couponLogRepositoryInter.findByCustomerId(customerId, pageable);
    }

    @Override
    public Slice<MarketLogDTO> findAllMarketLog(Long marketId, Pageable pageable) {
        return couponLogRepositoryInter.findByMarketId(marketId, pageable);
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

    @Override
    public List<TimeAnalysisDTO> timeAnalysis(Long marketId) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<CouponLog> yesterdayInfo = new ArrayList<>();

        yesterdayInfo = couponLogRepository.findByDay(marketId, yesterday.getYear(), yesterday.getMonthValue(), yesterday.getDayOfMonth());

        List<TimeAnalysisDTO> timeAnalysisDTOS = new ArrayList<>();


        if (yesterdayInfo.isEmpty()) {
            for (int i = 0; i < 24; i++) {
                TimeAnalysisDTO timeAnalysisDTO = new TimeAnalysisDTO();
                timeAnalysisDTO.setTimeAnalysis(i, 0);
                timeAnalysisDTOS.add(timeAnalysisDTO);
            }
        } else{
            for (int i = 0; i < 24; i++) {
                int targetHour = i;
                TimeAnalysisDTO timeAnalysisDTO = new TimeAnalysisDTO();
                long visitor = yesterdayInfo.stream().filter(c -> c.getHour() == targetHour).count();

                timeAnalysisDTO.setTimeAnalysis(i, visitor);
                timeAnalysisDTOS.add(timeAnalysisDTO);
            }
        }
        return timeAnalysisDTOS;
    }

    @Override
    public List<DayOfWeekAnalysisDTO> dayOfWeekAnalysis(Long marketId) {

        LocalDate today = LocalDate.now();
        List<DayOfWeekAnalysisDTO> dayOfWeekAnalysisDTOS = new ArrayList<>();

        switch (today.getDayOfWeek()) {

            case MONDAY:
                dayOfWeekAnalysisDTOS = countVisitor(7, 0, today, marketId);
                break;
            case TUESDAY:
                dayOfWeekAnalysisDTOS = countVisitor(8, 1, today, marketId);
                break;
            case WEDNESDAY:
                dayOfWeekAnalysisDTOS = countVisitor(9, 2, today, marketId);
                break;
            case THURSDAY:
                dayOfWeekAnalysisDTOS = countVisitor(10, 3, today, marketId);
                break;
            case FRIDAY:
                dayOfWeekAnalysisDTOS = countVisitor(11, 4, today, marketId);
                break;
            case SATURDAY:
                dayOfWeekAnalysisDTOS = countVisitor(12, 5, today, marketId);
                break;
            case SUNDAY:
                dayOfWeekAnalysisDTOS = countVisitor(13, 6, today, marketId);
                break;
            default:
                break;
        }


        return dayOfWeekAnalysisDTOS;
    }

    @Override
    public List<MonthAnalysisDTO> monthAnalysis(Long marketId) {
        LocalDate lastYear = LocalDate.now().minusYears(1);
        String[] monthInfo = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};

        List<CouponLog> lastYearInfo = couponLogRepository.findByYear(marketId, lastYear.getYear());
        List<MonthAnalysisDTO> monthAnalysisDTOS = new ArrayList<>();


        if (lastYearInfo.isEmpty()) {

            for (int i = 1; i <= 12; i++) {
                MonthAnalysisDTO monthAnalysisDTO = new MonthAnalysisDTO();
                monthAnalysisDTO.setMonthAnalysis(monthInfo[i - 1], 0);
                monthAnalysisDTOS.add(monthAnalysisDTO);
            }
        } else{
            for (int i = 1; i <= 12; i++) {
                int month = i;
                MonthAnalysisDTO monthAnalysisDTO = new MonthAnalysisDTO();
                long visitor = lastYearInfo.stream().filter(c -> c.getMonth() == month).count();

                monthAnalysisDTO.setMonthAnalysis(monthInfo[i - 1], visitor);
                monthAnalysisDTOS.add(monthAnalysisDTO);
            }
        }


        return monthAnalysisDTOS;
    }

    @Override
    public List<GenderAnalysisDTO> genderAnalysis(Long marketId, int day) {

        String[] genderInfo = {"남자", "여자"};


        LocalDate date = LocalDate.now().minusDays(day);
        List<CouponLog> marketLog = couponLogRepository.findByMarketIdAndDay(marketId, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        List<GenderAnalysisDTO> genderAnalysisDTOS = new ArrayList<>();
        GenderAnalysisDTO male = new GenderAnalysisDTO();
        GenderAnalysisDTO female = new GenderAnalysisDTO();
        Gender gender = Gender.MALE;

        if (marketLog.isEmpty()) {
            male.setGenderAnalysis(genderInfo[0],1, 0);
            genderAnalysisDTOS.add(male);
            female.setGenderAnalysis(genderInfo[1], 2, 0);
            genderAnalysisDTOS.add(female);
        } else{
            long maleVisitor = marketLog.stream().filter(c -> c.getCustomer().getGender().equals(Gender.MALE)).count();
            male.setGenderAnalysis(genderInfo[0], 1, maleVisitor);
            female.setGenderAnalysis(genderInfo[1], 2, marketLog.size() - maleVisitor);
            genderAnalysisDTOS.add(male);
            genderAnalysisDTOS.add(female);
        }

        return genderAnalysisDTOS;
    }


    public List<DayOfWeekAnalysisDTO> countVisitor(int startIndex, int endIndex, LocalDate today, Long marketId) {
        String[] dayOfWeek = {"월", "화", "수", "목", "금", "토", "일"};
        List<DayOfWeekAnalysisDTO> dayOfWeekAnalysisDTOS = new ArrayList<>();

        int index = 0;
        for (int i = startIndex; i > endIndex; i--) {
            DayOfWeekAnalysisDTO dayOfWeekAnalysisDTO = new DayOfWeekAnalysisDTO();
            LocalDate lastWeek = today.minusDays(i);
            Long visitor = couponLogRepository.countByDay(marketId, lastWeek.getYear(), lastWeek.getMonthValue(), lastWeek.getDayOfMonth());

            dayOfWeekAnalysisDTO.setDayOfWeekDTO(dayOfWeek[index], visitor);
            dayOfWeekAnalysisDTOS.add(dayOfWeekAnalysisDTO);
            index++;
        }
        return dayOfWeekAnalysisDTOS;
    }

}
