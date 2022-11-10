package MOCUMOCU.project.coupon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveStampWithDayDTO {

    private Long marketId;
    private Long customerId;
    private int amount;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
}
