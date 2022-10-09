package MOCUMOCU.project.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardAddDTO {

    private Long marketId;
    private String rewardName;
    private int couponRequire;
}
