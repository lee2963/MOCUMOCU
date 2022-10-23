package MOCUMOCU.project.coupon;

import MOCUMOCU.project.couponlog.CouponLog;
import MOCUMOCU.project.couponlog.CouponLogRepository;
import MOCUMOCU.project.customer.CustomerRepository;
import MOCUMOCU.project.coupon.form.CouponInfoDTO;
import MOCUMOCU.project.reward.form.RewardInfoDTO;
import MOCUMOCU.project.coupon.form.SaveStampDTO;
import MOCUMOCU.project.coupon.form.UseStampDTO;
import MOCUMOCU.project.market.MarketRepository;
import MOCUMOCU.project.reward.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final MarketRepository marketRepository;
    private final CustomerRepository customerRepository;
    private final CouponLogRepository couponLogRepository;

    @Override
    public boolean useStamp(UseStampDTO useStampDTO) {
        Coupon findCoupon = couponRepository.findOne(useStampDTO.getCouponId());
        CouponLog newCouponLog = new CouponLog();

        if(findCoupon.getAmountStamp() - useStampDTO.getCouponRequire() > 0 ){
            findCoupon.setAmountStamp(findCoupon.getAmountStamp() - useStampDTO.getCouponRequire());
            newCouponLog.setLog(findCoupon, useStampDTO.getCouponRequire());
            couponLogRepository.save(newCouponLog);
            return true;
        }
        return false;
    }

    @Override
    public void earnStamp(SaveStampDTO saveStampDTO) {

        Coupon findCoupon = couponRepository
                .findByCustomerIdAndMarketId(saveStampDTO.getCustomerId(), saveStampDTO.getMarketId());
        CouponLog newCouponLog = new CouponLog();

        if (findCoupon == null) {
            Coupon newCoupon = new Coupon();

            newCoupon.setCoupon(customerRepository.findOne(saveStampDTO.getCustomerId()),
                    marketRepository.findOne(saveStampDTO.getMarketId()), saveStampDTO.getAmount());

             couponRepository.save(newCoupon);

             newCouponLog.setLog(newCoupon, saveStampDTO.getAmount());
        } else{
            findCoupon.setAmountStamp(findCoupon.getAmountStamp() + saveStampDTO.getAmount());
            newCouponLog.setLog(findCoupon, saveStampDTO.getAmount());
        }

        couponLogRepository.save(newCouponLog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouponInfoDTO> findAllCoupon(Long customerId) {
        List<Coupon> myCoupons = couponRepository.findByCustomerId(customerId);
        List<CouponInfoDTO> couponInfoDTOList = new ArrayList<>();

        for (Coupon myCoupon : myCoupons) {
            CouponInfoDTO couponInfoDTO = new CouponInfoDTO();

            couponInfoDTO.setCouponId(myCoupon.getId());
            couponInfoDTO.setMarketName(myCoupon.getMarket().getMarketName());
            couponInfoDTO.setStampAmount(myCoupon.getAmountStamp());

            couponInfoDTOList.add(couponInfoDTO);
        }

        return  couponInfoDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RewardInfoDTO> findAllReward(Long id) {
        Coupon coupon = couponRepository.findOne(id);
        List<Reward> rewards = coupon.getRewards();
        List<RewardInfoDTO> rewardInfoDTOList = new ArrayList<>();

        for (Reward reward : rewards) {
            rewardInfoDTOList.add(new RewardInfoDTO(reward.getNeedAmount(), reward.getRewardContent()));
        }

        return rewardInfoDTOList;
    }


}
