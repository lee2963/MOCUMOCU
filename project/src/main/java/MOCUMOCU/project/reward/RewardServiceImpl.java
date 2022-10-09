package MOCUMOCU.project.reward;

import MOCUMOCU.project.domain.Reward;
import MOCUMOCU.project.form.RewardAddDTO;
import MOCUMOCU.project.Market.MarketRepository;
import MOCUMOCU.project.reward.RewardRepository;
import MOCUMOCU.project.reward.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final MarketRepository marketRepository;
    private final RewardRepository rewardRepository;

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
}
