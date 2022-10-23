package MOCUMOCU.project.owner.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerInfoDTO {

    private Long ownerId;
    private String ownerName;
    private String ownerEmail;
    private String userType;
    private boolean isLogIn;
}
