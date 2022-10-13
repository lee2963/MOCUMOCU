package MOCUMOCU.project.coupon;

import MOCUMOCU.project.form.CouponInfoDTO;
import MOCUMOCU.project.form.RewardInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponServiceImpl couponService;

    @GetMapping("/{customerIdTest}/coupon")
    public ResponseEntity<List<CouponInfoDTO>> findMyCoupons(@PathVariable Long customerIdTest) {

        List<CouponInfoDTO> couponInfoDTOList = couponService.findAllCoupon(customerIdTest);

        return new ResponseEntity<>(couponInfoDTOList, HttpStatus.OK);
    }

    @GetMapping("/reward-list")
    public ResponseEntity<List<RewardInfoDTO>> showRewards(@RequestParam Long couponId) {

        List<RewardInfoDTO> rewardInfoDTOList = couponService.findAllReward(couponId);
        return new ResponseEntity<>(rewardInfoDTOList, HttpStatus.OK);
    }
}
