package MOCUMOCU.project.domain;

import MOCUMOCU.project.Market.Market;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Reward {

    @Id @GeneratedValue
    @Column(name = "reward_id")
    private Long id;

    private int needAmount;
    private String rewardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "market_id")
    private Market market;
}
