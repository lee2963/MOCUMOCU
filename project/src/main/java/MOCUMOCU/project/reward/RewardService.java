package MOCUMOCU.project.reward;

import MOCUMOCU.project.reward.form.RewardAddDTO;
import MOCUMOCU.project.reward.form.RewardOwnerDTO;

import java.util.List;

public interface RewardService {

    void addReward(RewardAddDTO rewardAddDTO);

    void removeReward(Long id);

    void updateReward(Reward reward);

    List<RewardOwnerDTO> findAllReward(Long id);
}
