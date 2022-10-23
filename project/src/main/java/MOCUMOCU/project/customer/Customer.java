package MOCUMOCU.project.customer;

import MOCUMOCU.project.couponlog.CouponLog;
import MOCUMOCU.project.customizeCustomer.CustomizeCustomer;
import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.Privacy;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @Embedded
    private Privacy privacy;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int userPoint; //보유 포인트

    private String birthDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate lastDate; //마지막 접속 날짜 저장

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Coupon> coupons = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomizeCustomer> CustomizeCustomers = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CouponLog> couponLogs = new ArrayList<>();

}
