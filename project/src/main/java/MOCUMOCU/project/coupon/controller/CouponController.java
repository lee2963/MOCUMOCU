package MOCUMOCU.project.coupon.controller;

import MOCUMOCU.project.coupon.dto.*;
import MOCUMOCU.project.coupon.service.CouponServiceImpl;
import MOCUMOCU.project.reward.form.RewardInfoDTO;
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
            log.info("use = " + useStampDTO.getCouponRequire());
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{customerId}/coupon")
    public ResponseEntity<List<CouponInfoDTO>> findMyCoupons(@PathVariable Long customerId) {

        List<CouponInfoDTO> couponInfoDTOList = couponService.findAllCoupon(customerId);

        return new ResponseEntity<>(couponInfoDTOList, HttpStatus.OK);
    }

    @GetMapping("/reward-list")
    public ResponseEntity<List<RewardInfoDTO>> showRewards(@RequestParam Long couponId) {

        List<RewardInfoDTO> rewardInfoDTOList = couponService.findAllReward(couponId);
        return new ResponseEntity<>(rewardInfoDTOList, HttpStatus.OK);
    }

    @PostMapping("/set/customize")
    public ResponseEntity<Void> setCustomize(@RequestBody SetCustomizeDTO setCustomizeDTO) {
        couponService.setCustomizeImage(setCustomizeDTO.getCouponId(), setCustomizeDTO.getCustomizeId(), setCustomizeDTO.getType());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/set/coupon-time")
    public ResponseEntity<Void> setCouponWithDate(@RequestBody SaveStampWithDayDTO saveStampWithDayDTO) {
        couponService.earnStampSetDayTogether(saveStampWithDayDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
