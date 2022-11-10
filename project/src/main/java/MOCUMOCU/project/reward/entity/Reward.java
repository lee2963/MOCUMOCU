package MOCUMOCU.project.reward.entity;

import MOCUMOCU.project.market.entity.Market;
import MOCUMOCU.project.coupon.entity.Coupon;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "coupon_id")
    private Coupon coupon;
}
