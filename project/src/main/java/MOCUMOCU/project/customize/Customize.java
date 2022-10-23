package MOCUMOCU.project.customize;


import MOCUMOCU.project.coupon.Coupon;
import MOCUMOCU.project.customizeCustomer.CustomizeCustomer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Customize {

    @Id @GeneratedValue
    @Column(name = "customize_id")
    private Long id;

    private int customizePoint;

    private String customizeImage;

    private String customizeName;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "customize", cascade = CascadeType.ALL)
    private List<CustomizeCustomer> customizeCustomers = new ArrayList<>();

    @OneToOne(mappedBy = "customize")
    private Coupon coupon;
}
