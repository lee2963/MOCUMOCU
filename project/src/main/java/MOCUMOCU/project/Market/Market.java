package MOCUMOCU.project.Market;

import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.domain.Reward;
import MOCUMOCU.project.owner.Owner;
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

    private String businessNum;
    private String marketPhoneNum;
    private String marketName;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "market")
    private List<Reward> rewards = new ArrayList<>();

    @OneToOne(mappedBy = "market")
    private Coupon coupon;
}
