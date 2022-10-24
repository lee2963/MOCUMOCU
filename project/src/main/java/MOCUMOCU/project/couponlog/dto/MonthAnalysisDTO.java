package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthAnalysisDTO {

    private String month;
    private long count;

    public void setMonthAnalysis(String month, long count) {
        this.month = month;
        this.count = count;
    }

}
