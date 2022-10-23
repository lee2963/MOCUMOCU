package MOCUMOCU.project.couponlog.controller;

import MOCUMOCU.project.couponlog.entity.CouponLog;
import MOCUMOCU.project.couponlog.service.CouponLogServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class CouponLogController {

    private final CouponLogServiceImpl couponLogService;
    //매장 분석을 위한 컨트롤러

    //customer 쿠폰 적립 사용내역 컨트롤러
    @PostMapping("/save")
    public ResponseEntity<Void> saveLog(CouponLog couponLog) {
        couponLogService.saveLog(couponLog);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
