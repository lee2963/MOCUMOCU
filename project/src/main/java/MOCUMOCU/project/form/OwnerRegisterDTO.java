package MOCUMOCU.project.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerRegisterDTO {
    private String ownerName;
    private String ownerPhoneNum;
    private String ownerEmail;
    private String ownerPassword;
    private String ownerCheckPassword;
}
