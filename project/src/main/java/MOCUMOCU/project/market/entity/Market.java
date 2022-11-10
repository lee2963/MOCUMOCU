package MOCUMOCU.project.market.entity;

import MOCUMOCU.project.coupon.entity.Coupon;
import MOCUMOCU.project.reward.entity.Reward;
import MOCUMOCU.project.owner.entity.Owner;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Market {

    @Id @GeneratedValue
    @Column(name = "market_id")
    private Long id;

    private String marketPhoneNum;
    private String marketName;
    private String marketAddress;
    private String eventBigImage;
    private String eventSmallImage;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private List<Reward> rewards = new ArrayList<>();

    @OneToOne(mappedBy = "market")
    private Coupon coupon;
}
