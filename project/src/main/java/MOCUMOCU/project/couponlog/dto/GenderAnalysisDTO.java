package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenderAnalysisDTO {

    private String gender;
    private long count;

    public void setGenderAnalysis(String gender, long count) {
        this.gender = gender;
        this.count = count;
    }
}
