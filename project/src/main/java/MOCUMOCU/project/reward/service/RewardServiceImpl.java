package MOCUMOCU.project.reward.service;

import MOCUMOCU.project.coupon.repository.CouponRepository;
import MOCUMOCU.project.reward.entity.Reward;
import MOCUMOCU.project.reward.form.RewardAddDTO;
import MOCUMOCU.project.market.repository.MarketRepository;
import MOCUMOCU.project.reward.form.RewardCustomerDTO;
import MOCUMOCU.project.reward.form.RewardOwnerDTO;
import MOCUMOCU.project.reward.repository.RewardRepository;
import MOCUMOCU.project.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final MarketRepository marketRepository;
    private final RewardRepository rewardRepository;
    private final CouponRepository couponRepository;

    @Override
    public void addReward(RewardAddDTO rewardAddDTO) {
        Reward newReward = new Reward();
        newReward.setMarket(marketRepository.findOne(rewardAddDTO.getMarketId()));
        newReward.setRewardContent(rewardAddDTO.getRewardName());
        newReward.setNeedAmount(rewardAddDTO.getCouponRequire());

        rewardRepository.save(newReward);
    }

    @Override
    public void removeReward(Long id) {
        Reward findReward = rewardRepository.findOne(id);
        rewardRepository.remove(findReward);
    }

    @Override
    public void updateReward(Reward reward) {
        Reward updateReward = rewardRepository.findOne(reward.getId());
        updateReward.setRewardContent(reward.getRewardContent());
        updateReward.setNeedAmount(reward.getNeedAmount());
    }

    @Override
    public List<RewardCustomerDTO> customerReward(Long couponId) {
        Long marketId = couponRepository.findOne(couponId).getMarket().getId();

        List<Reward> findRewards = rewardRepository.findByMarketId(marketId);
        List<RewardCustomerDTO> rewardCustomerDTOS = new ArrayList<>();

        for (Reward findReward : findRewards) {
            RewardCustomerDTO rewardCustomerDTO = new RewardCustomerDTO();

            rewardCustomerDTO.setReward(findReward.getRewardContent());
            rewardCustomerDTO.setCouponRequire(findReward.getNeedAmount());

            rewardCustomerDTOS.add(rewardCustomerDTO);
        }

        return rewardCustomerDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RewardOwnerDTO> findAllReward(Long id) {

        List<Reward> findRewards = rewardRepository.findByMarketId(id);
        List<RewardOwnerDTO> rewardOwnerDTOList = new ArrayList<>();

        for (Reward findReward : findRewards) {
            RewardOwnerDTO rewardOwnerDTO = new RewardOwnerDTO();

            rewardOwnerDTO.setId(findReward.getId());
            rewardOwnerDTO.setReward(findReward.getRewardContent());
            rewardOwnerDTO.setCouponRequire(findReward.getNeedAmount());

            rewardOwnerDTOList.add(rewardOwnerDTO);
        }

        return rewardOwnerDTOList;
    }
}
