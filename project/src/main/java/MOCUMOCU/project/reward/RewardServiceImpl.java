package MOCUMOCU.project.reward;

import MOCUMOCU.project.reward.form.RewardAddDTO;
import MOCUMOCU.project.market.repository.MarketRepository;
import MOCUMOCU.project.reward.form.RewardOwnerDTO;
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
