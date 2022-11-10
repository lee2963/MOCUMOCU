package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MarketLogDTO {
    private int month;
    private int day;
    private int stamp;
    private int hour;
    private int minute;
    private String customerName;

    public MarketLogDTO(int month, int day, int stamp, int hour, int minute, String customerName) {
        this.month = month;
        this.day = day;
        this.stamp = stamp;
        this.hour = hour;
        this.minute = minute;
        this.customerName = customerName;
    }
}
