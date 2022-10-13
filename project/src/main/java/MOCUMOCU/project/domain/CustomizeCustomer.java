package MOCUMOCU.project.domain;

import MOCUMOCU.project.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CustomizeCustomer {

    @Id @GeneratedValue
    @Column(name = "customize_customer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customize_id")
    private CustomizeCustomer customizeCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customizeCustom")
    private Customer customer;
}
