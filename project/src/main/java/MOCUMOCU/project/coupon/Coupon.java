package MOCUMOCU.project.coupon;

import MOCUMOCU.project.customizeCustomer.CustomizeCustomer;
import MOCUMOCU.project.market.Market;
import MOCUMOCU.project.couponlog.CouponLog;
import MOCUMOCU.project.customer.Customer;
import MOCUMOCU.project.reward.Reward;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Coupon {

    @Id @GeneratedValue
    @Column(name = "coupon_id")
    private Long id;

    private int amountStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponlog_id")
    private CouponLog couponLog;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<Reward> rewards = new ArrayList<>();

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<CustomizeCustomer> customizeCustomers = new ArrayList<>();

    public void setCoupon(Customer customer, Market market, int amountStamp) {
        this.customer = customer;
        this.market = market;
        this.amountStamp = amountStamp;
    }
}
