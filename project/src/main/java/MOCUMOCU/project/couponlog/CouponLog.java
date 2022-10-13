package MOCUMOCU.project.couponlog;

import MOCUMOCU.project.Market.Market;
import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter @Setter
public class CouponLog {

    @Id @GeneratedValue
    @Column(name = "couponlog_id")
    private Long id;

    private LocalDateTime nowTime;
    private int curStamp;
    private String rewardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    public void setLog(Coupon coupon, int stamp) {
        CouponLog newCouponLog = new CouponLog();

        newCouponLog.setNowTime(LocalDateTime.now());
        newCouponLog.setCoupon(coupon);
        newCouponLog.setMarket(coupon.getMarket());
        newCouponLog.setCustomer(coupon.getCustomer());
        newCouponLog.setCurStamp(stamp);
    }
}
