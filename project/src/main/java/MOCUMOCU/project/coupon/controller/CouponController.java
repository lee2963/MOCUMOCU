package MOCUMOCU.project.coupon.controller;

import MOCUMOCU.project.coupon.service.CouponServiceImpl;
import MOCUMOCU.project.coupon.dto.CouponInfoDTO;
import MOCUMOCU.project.reward.form.RewardInfoDTO;
import MOCUMOCU.project.coupon.dto.SaveStampDTO;
import MOCUMOCU.project.coupon.dto.UseStampDTO;
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

    @PostMapping("/stamp")
    public ResponseEntity<Void> saveStamp(@RequestBody SaveStampDTO saveStampDTO) {
        couponService.earnStamp(saveStampDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/stamp")
    public ResponseEntity<Void> useStamp(@RequestBody UseStampDTO useStampDTO) {
        if(couponService.useStamp(useStampDTO)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


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
