package MOCUMOCU.project.domain;


import MOCUMOCU.project.coupon.Coupon;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class BoardCustom {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private int boardPoint;
    private String boardImage;
    private String boardName;

    @OneToMany(mappedBy = "boardCustom")
    private List<BoardCustomer> boardCustomers = new ArrayList<>();

    @OneToOne(mappedBy = "boardCustom")
    private Coupon coupon;
}
