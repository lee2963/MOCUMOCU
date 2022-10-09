package MOCUMOCU.project.customer;

import MOCUMOCU.project.domain.BoardCustomer;
import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.domain.Privacy;
import MOCUMOCU.project.domain.StampCustomer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private int attendanceDate; //연속 출석 일수
    private int userPoint; //보유 포인트
    private String birthDate;
    private boolean enable;
    private LocalDateTime lastDate; //마지막 접속 날짜 저장

    @OneToMany(mappedBy = "customer")
    private List<Coupon> coupons = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<BoardCustomer> boardCustomers = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<StampCustomer> stampCustomers = new ArrayList<>();
}
