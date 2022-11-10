package MOCUMOCU.project.pointlog.dto;


import MOCUMOCU.project.pointlog.entity.Type;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PointLogDTO {

    private int month;
    private int day;
    private int point;
    private int hour;
    private int minute;
    private Type type;

    public PointLogDTO(int month, int day, int hour, int minute, int point, Type type) {
        this.month = month;
        this.day = day;
        this.point = point;
        this.hour = hour;
        this.minute = minute;
        this.type = type;
    }
}
