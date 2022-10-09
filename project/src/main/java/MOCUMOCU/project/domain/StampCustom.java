package MOCUMOCU.project.domain;

import MOCUMOCU.project.coupon.Coupon;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class StampCustom {

    @Id @GeneratedValue
    @Column(name ="stamp_id")
    private Long id;

    private int stampPoint;
    private String stampImage;
    private String stampName;

    @OneToMany(mappedBy = "stampCustom")
    private List<StampCustomer> stampCustomers = new ArrayList<>();

    @OneToOne(mappedBy = "stampCustom")
    private Coupon coupon;
}
