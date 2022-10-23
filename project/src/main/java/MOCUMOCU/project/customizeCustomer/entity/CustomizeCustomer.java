package MOCUMOCU.project.customizeCustomer.entity;

import MOCUMOCU.project.coupon.entity.Coupon;
import MOCUMOCU.project.customer.entity.Customer;
import MOCUMOCU.project.customize.entity.Customize;
import MOCUMOCU.project.pointlog.entity.PointLog;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CustomizeCustomer {

    @Id @GeneratedValue
    @Column(name = "customizecustomer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customize_id")
    private Customize customize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pointlog_id")
    private PointLog pointLog;


    public void setCustomizeCustomer(Customize customize, Customer customer, PointLog pointLog) {
        this.customize = customize;
        this.customer = customer;
        this.pointLog = pointLog;
    }
}
