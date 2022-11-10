package MOCUMOCU.project.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerRegisterDTO {

    private String customerName;
    private String customerPhoneNum;
    private String customerEmail;
    private String customerPassword;
    private String customerCheckPassword;
    private String customerBirth;
    private String customerGender;
}
