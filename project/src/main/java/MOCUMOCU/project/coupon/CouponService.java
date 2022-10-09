package MOCUMOCU.project.coupon;

import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.form.RewardInfoDTO;
import MOCUMOCU.project.form.SaveStampDTO;
import MOCUMOCU.project.form.UseStampDTO;

import java.util.List;

public interface CouponService {

    Long addCoupon(Coupon coupon);

    boolean useStamp(UseStampDTO useStampDTO);

    void earnStamp(SaveStampDTO saveStampDTO);

    void removeCoupon(Coupon coupon);

    void changeBoard(Long id);

    void changeStamp(Long id);

    List<RewardInfoDTO> findAllReward(Long id);
}
