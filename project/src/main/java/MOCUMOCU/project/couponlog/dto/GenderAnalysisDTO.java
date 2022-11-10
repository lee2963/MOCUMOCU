package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenderAnalysisDTO {

    private String placement;

    private int x;
    private long y;

    public void setGenderAnalysis(String placement, int x, long y) {
        this.placement = placement;
        this.x = x;
        this.y = y;
    }
}
