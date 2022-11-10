package MOCUMOCU.project.reward.service;

import MOCUMOCU.project.reward.entity.Reward;
import MOCUMOCU.project.reward.form.RewardAddDTO;
import MOCUMOCU.project.reward.form.RewardCustomerDTO;
import MOCUMOCU.project.reward.form.RewardOwnerDTO;

import java.util.List;

public interface RewardService {

    void addReward(RewardAddDTO rewardAddDTO);

    void removeReward(Long id);

    void updateReward(Reward reward);

    List<RewardCustomerDTO> customerReward(Long couponId);

    List<RewardOwnerDTO> findAllReward(Long id);

}
