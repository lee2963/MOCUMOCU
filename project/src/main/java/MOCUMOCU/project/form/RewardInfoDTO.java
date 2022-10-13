package MOCUMOCU.project.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RewardInfoDTO {

    private int needAmount;
    private String rewardContent;

    public RewardInfoDTO(int needAmount, String rewardContent) {
        this.needAmount = needAmount;
        this.rewardContent = rewardContent;
    }
}
