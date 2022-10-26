package MOCUMOCU.project.couponlog.controller;

import MOCUMOCU.project.couponlog.dto.CouponLogDTO;
import MOCUMOCU.project.couponlog.service.CouponLogServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<CouponLogDTO>> CouponLogForCustomer(@RequestParam Long customerId, Pageable pageable) {
        List<CouponLogDTO> logInfo = couponLogService.findAllCouponLog(customerId, pageable);
        return new ResponseEntity<>(logInfo, HttpStatus.OK);
    }

}
