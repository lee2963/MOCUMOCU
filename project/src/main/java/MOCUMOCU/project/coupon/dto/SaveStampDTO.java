package MOCUMOCU.project.coupon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaveStampDTO {

    private Long marketId;
    private Long customerId;
    private int amount;
}
