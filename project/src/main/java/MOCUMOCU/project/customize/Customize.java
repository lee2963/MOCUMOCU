package MOCUMOCU.project.customize;


import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.domain.CustomizeCustomer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Customize {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private int customizePoint;
    private String customizeImage;
    private String customizeName;
    private boolean type; // 1 쿠폰 0 스탬프

    @OneToMany(mappedBy = "cutomizeCustom", cascade = CascadeType.ALL)
    private List<CustomizeCustomer> customizeCustomers = new ArrayList<>();

    @OneToOne(mappedBy = "customize")
    private Coupon coupon;
}
