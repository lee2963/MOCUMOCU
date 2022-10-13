package MOCUMOCU.project.coupon;

import MOCUMOCU.project.form.CouponInfoDTO;
import MOCUMOCU.project.form.RewardInfoDTO;
import MOCUMOCU.project.form.SaveStampDTO;
import MOCUMOCU.project.form.UseStampDTO;

import java.util.List;

public interface CouponService {

    Long addCoupon(Coupon coupon);

    boolean useStamp(UseStampDTO useStampDTO);

    void earnStamp(SaveStampDTO saveStampDTO);

    List<CouponInfoDTO> findAllCoupon(Long id);

    List<RewardInfoDTO> findAllReward(Long id);

}
