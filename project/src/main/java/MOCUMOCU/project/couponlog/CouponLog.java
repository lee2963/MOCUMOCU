package MOCUMOCU.project.couponlog;

import MOCUMOCU.project.market.Market;
import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class CouponLog {

    @Id @GeneratedValue
    @Column(name = "couponlog_id")
    private Long id;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String dayOfWeek;

    private int usedStamp;

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
        LocalDateTime now = LocalDateTime.now();

        this.year = now.getYear();
        this.month = now.getMonthValue();
        this.day = now.getDayOfMonth();
        this.hour = now.getHour();
        this.minute = now.getMinute();
        this.dayOfWeek = now.getDayOfWeek().name();
        this.coupon = coupon;
        this.market = coupon.getMarket();
        this.customer = coupon.getCustomer();
        this.usedStamp = stamp;
    }
}
