package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayOfWeekAnalysisDTO {

    private String day;

    private long count;

    public void setDayOfWeekDTO(String day, long count) {
        this.day = day;
        this.count = count;
    }
}
