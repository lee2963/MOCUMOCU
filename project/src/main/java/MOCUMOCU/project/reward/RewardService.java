package MOCUMOCU.project.reward;

import MOCUMOCU.project.form.RewardAddDTO;

import java.util.List;

public interface RewardService {

    void addReward(RewardAddDTO rewardAddDTO);

    void removeReward(Long id);

    void updateReward(Reward reward);
}
