package MOCUMOCU.project.coupon;

import MOCUMOCU.project.customer.CustomerRepository;
import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.domain.Reward;
import MOCUMOCU.project.form.RewardInfoDTO;
import MOCUMOCU.project.form.SaveStampDTO;
import MOCUMOCU.project.form.UseStampDTO;
import MOCUMOCU.project.coupon.CouponRepository;
import MOCUMOCU.project.Market.MarketRepository;
import MOCUMOCU.project.reward.RewardRepository;
import MOCUMOCU.project.coupon.CouponService;
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
    private final RewardRepository rewardRepository;
    private final MarketRepository marketRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Long addCoupon(Coupon coupon) {
        couponRepository.save(coupon);

        return coupon.getId();
    }

    @Override
    public boolean useStamp(UseStampDTO useStampDTO) {
        Coupon findCoupon = couponRepository.findOne(useStampDTO.getCouponId());

        if(findCoupon.getAmount() - useStampDTO.getCouponRequire() > 0 ){
            findCoupon.setAmount(findCoupon.getAmount() - useStampDTO.getCouponRequire());
            return true;
        }
        return false;
    }

    @Override
    public void earnStamp(SaveStampDTO saveStampDTO) {

        List<Coupon> findCoupons = couponRepository.findByCustomerIdAndMarketId(saveStampDTO.getCustomerId(), saveStampDTO.getMarketId());

        if (findCoupons.isEmpty()) {
            Coupon newCoupon = new Coupon();
            newCoupon.setMarket(marketRepository.findOne(saveStampDTO.getMarketId()));
            newCoupon.setCustomer(customerRepository.findOne(saveStampDTO.getCustomerId()));
            newCoupon.setAmount(saveStampDTO.getAmount());
            couponRepository.save(newCoupon);
        } else{
            findCoupons.get(0).setAmount(findCoupons.get(0).getAmount() + saveStampDTO.getAmount());
        }

    }


    @Override
    public void removeCoupon(Coupon coupon) {
        couponRepository.remove(coupon);
    }

    @Override
    public void changeBoard(Long id) {

    }

    @Override
    public void changeStamp(Long id) {

    }

    @Override
    public List<RewardInfoDTO> findAllReward(Long id) {
        Coupon findCoupon = couponRepository.findOne(id);
        List<Reward> rewards = rewardRepository.findByMarketId(findCoupon.getMarket().getId());
        List<RewardInfoDTO> rewardInfoDTOList = new ArrayList<>();

        for (Reward reward : rewards) {
            RewardInfoDTO rewardInfoDTO = new RewardInfoDTO();

            rewardInfoDTO.setRewardContent(reward.getRewardContent());
            rewardInfoDTO.setNeedAmount(reward.getNeedAmount());

            rewardInfoDTOList.add(rewardInfoDTO);
        }

        return rewardInfoDTOList;
    }
}
