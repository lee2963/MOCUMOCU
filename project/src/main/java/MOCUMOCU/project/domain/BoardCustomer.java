package MOCUMOCU.project.domain;

import MOCUMOCU.project.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class BoardCustomer {

    @Id @GeneratedValue
    @Column(name = "board_customer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardCustom boardCustom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardCustom")
    private Customer customer;
}
