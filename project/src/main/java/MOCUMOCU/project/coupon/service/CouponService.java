package MOCUMOCU.project.coupon.service;

import MOCUMOCU.project.coupon.dto.CouponInfoDTO;
import MOCUMOCU.project.coupon.dto.SaveStampWithDayDTO;
import MOCUMOCU.project.reward.form.RewardInfoDTO;
import MOCUMOCU.project.coupon.dto.SaveStampDTO;
import MOCUMOCU.project.coupon.dto.UseStampDTO;

import java.util.List;

public interface CouponService {

    boolean useStamp(UseStampDTO useStampDTO);

    void earnStamp(SaveStampDTO saveStampDTO);

    List<CouponInfoDTO> findAllCoupon(Long id);

    List<RewardInfoDTO> findAllReward(Long id);

    void setCustomizeImage(Long couponId, Long customizeCustomerId, String type);

    void earnStampSetDayTogether(SaveStampWithDayDTO saveStampWithDayDTO);




}
