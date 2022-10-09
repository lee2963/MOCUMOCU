package MOCUMOCU.project.form;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class MarketInfoDTO {

    private Long id;
    private String phoneNum;
    private String name;
    private List<RewardOwnerDTO> rewardList;
    private int today = 54;
    private int male = 42;
    private int female = 12;

    private List<ActivityData> activityData = new ArrayList<>();
}

