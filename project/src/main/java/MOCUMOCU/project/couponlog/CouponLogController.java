package MOCUMOCU.project.couponlog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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


}
