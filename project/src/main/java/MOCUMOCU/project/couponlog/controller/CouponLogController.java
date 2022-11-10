package MOCUMOCU.project.couponlog.controller;

import MOCUMOCU.project.couponlog.dto.*;
import MOCUMOCU.project.couponlog.service.CouponLogServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/couponlog")
@RequiredArgsConstructor
public class CouponLogController {

    private final CouponLogServiceImpl couponLogService;
    //매장 분석을 위한 컨트롤러

    //customer 쿠폰 적립 사용내역 컨트롤러
    @GetMapping("/customer/scroll")
    public ResponseEntity<Slice<CouponLogDTO>> CouponLogForCustomer(@RequestParam Long customerId, Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
        Slice<CouponLogDTO> couponLog = couponLogService.findAllCouponLog(customerId, pageRequest);
        return new ResponseEntity<>(couponLog, HttpStatus.OK);
    }

    @GetMapping("/market/scroll")
    public ResponseEntity<Slice<MarketLogDTO>> MarketLogForOwner(@RequestParam Long marketId, Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
        Slice<MarketLogDTO> marketLog = couponLogService.findAllMarketLog(marketId, pageRequest);
        return new ResponseEntity<>(marketLog, HttpStatus.OK);
    }

    @GetMapping("/market/analysis")
    public ResponseEntity<AnalysisDTO> analysisForMarket(@RequestParam Long marketId, @RequestParam int day) {
        log.info("in");
        AnalysisDTO analysisDTO = new AnalysisDTO();
        analysisDTO.setAnalysisDTO(couponLogService.dayOfWeekAnalysis(marketId),
                couponLogService.genderAnalysis(marketId, day),
                couponLogService.monthAnalysis(marketId), couponLogService.timeAnalysis(marketId));

        return new ResponseEntity<>(analysisDTO, HttpStatus.OK);
    }
}
