package MOCUMOCU.project.domain;

import MOCUMOCU.project.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class StampCustomer {

    @Id
    @GeneratedValue
    @Column(name = "stamp_customer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stamp_id")
    private StampCustom stampCustom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
