package MOCUMOCU.project.coupon;

import MOCUMOCU.project.coupon.form.CouponInfoDTO;
import MOCUMOCU.project.reward.form.RewardInfoDTO;
import MOCUMOCU.project.coupon.form.SaveStampDTO;
import MOCUMOCU.project.coupon.form.UseStampDTO;

import java.util.List;

public interface CouponService {

    boolean useStamp(UseStampDTO useStampDTO);

    void earnStamp(SaveStampDTO saveStampDTO);

    List<CouponInfoDTO> findAllCoupon(Long id);

    List<RewardInfoDTO> findAllReward(Long id);



}
