package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class CouponLogDTO {

    private int month;
    private int day;
    private int stamp;
    private int hour;
    private int minute;
    private String marketName;

    public CouponLogDTO(int month, int day, int hour, int minute, int stamp, String marketName) {
        this.month = month;
        this.day = day;
        this.stamp = stamp;
        this.hour = hour;
        this.minute = minute;
        this.marketName = marketName;
    }
}
