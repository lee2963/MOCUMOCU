package MOCUMOCU.project.customer.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerInfoDTO {

    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String userType;
    private boolean isLogIn;
}
