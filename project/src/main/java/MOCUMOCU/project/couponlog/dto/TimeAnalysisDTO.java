package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TimeAnalysisDTO {
    private int time;
    private long count;

    public void setTimeAnalysis(int time, long count) {
        this.time = time;
        this.count = count;
    }
}
