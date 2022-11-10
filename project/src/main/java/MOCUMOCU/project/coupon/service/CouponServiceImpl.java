package MOCUMOCU.project.coupon.service;

import MOCUMOCU.project.coupon.dto.SaveStampWithDayDTO;
import MOCUMOCU.project.coupon.entity.Coupon;
import MOCUMOCU.project.coupon.repository.CouponRepository;
import MOCUMOCU.project.couponlog.entity.CouponLog;
import MOCUMOCU.project.couponlog.repository.CouponLogRepository;
import MOCUMOCU.project.customer.repository.CustomerRepository;
import MOCUMOCU.project.coupon.dto.CouponInfoDTO;
import MOCUMOCU.project.customizeCustomer.entity.CustomizeCustomer;
import MOCUMOCU.project.customizeCustomer.repository.CustomizeCustomerRepository;
import MOCUMOCU.project.reward.form.RewardInfoDTO;
import MOCUMOCU.project.coupon.dto.SaveStampDTO;
import MOCUMOCU.project.coupon.dto.UseStampDTO;
import MOCUMOCU.project.market.repository.MarketRepository;
import MOCUMOCU.project.reward.entity.Reward;
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
    private final CustomizeCustomerRepository customizeCustomerRepository;

    @Override
    public boolean useStamp(UseStampDTO useStampDTO) {
        Coupon findCoupon = couponRepository.findOne(useStampDTO.getCouponId());
        CouponLog newCouponLog = new CouponLog();

        if(findCoupon.getAmountStamp() - useStampDTO.getCouponRequire() >= 0 ){
            findCoupon.setAmountStamp(findCoupon.getAmountStamp() - useStampDTO.getCouponRequire());
            newCouponLog.setLog(findCoupon, -useStampDTO.getCouponRequire());
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
            couponInfoDTO.setBoardUrl(myCoupon.getBoardUrl());
            couponInfoDTO.setStampUrl(myCoupon.getStampUrl());

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

    @Override
    public void setCustomizeImage(Long couponId, Long customizeCustomerId, String type) {

        Coupon findCoupon = couponRepository.findOne(couponId);

        if(customizeCustomerId == -1){
            if(type.equals("stamp")){
                findCoupon.setStampUrl(null);
            } else if(type.equals("board")){
                findCoupon.setBoardUrl(null);
            }
        } else {

            CustomizeCustomer findCustomizeCustomer = customizeCustomerRepository.findOne(customizeCustomerId);

            if (type.equals("stamp")) {
                findCoupon.setStampUrl(findCustomizeCustomer.getCustomize().getSmallImageUrl());
            } else if (type.equals("board")) {
                findCoupon.setBoardUrl(findCustomizeCustomer.getCustomize().getBigImageUrl());
            }
        }
    }

    @Override
    public void earnStampSetDayTogether(SaveStampWithDayDTO saveStampWithDayDTO) {
        Coupon findCoupon = couponRepository
                .findByCustomerIdAndMarketId(saveStampWithDayDTO.getCustomerId(), saveStampWithDayDTO.getMarketId());
        CouponLog newCouponLog = new CouponLog();

        if (findCoupon == null) {
            Coupon newCoupon = new Coupon();

            newCoupon.setCoupon(customerRepository.findOne(saveStampWithDayDTO.getCustomerId()),
                    marketRepository.findOne(saveStampWithDayDTO.getMarketId()), saveStampWithDayDTO.getAmount());

            couponRepository.save(newCoupon);

            newCouponLog.setLogWithTime(newCoupon, saveStampWithDayDTO.getAmount(), saveStampWithDayDTO.getYear(),
                    saveStampWithDayDTO.getMonth(), saveStampWithDayDTO.getDay(), saveStampWithDayDTO.getHour(), saveStampWithDayDTO.getMinute());
        } else{
            findCoupon.setAmountStamp(findCoupon.getAmountStamp() + saveStampWithDayDTO.getAmount());
            newCouponLog.setLogWithTime(findCoupon, saveStampWithDayDTO.getAmount(), saveStampWithDayDTO.getYear(),
                    saveStampWithDayDTO.getMonth(), saveStampWithDayDTO.getDay(), saveStampWithDayDTO.getHour(), saveStampWithDayDTO.getMinute());
        }

        couponLogRepository.save(newCouponLog);
    }


}
