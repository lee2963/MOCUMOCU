package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AnalysisDTO {

    private List<DayOfWeekAnalysisDTO> dayOfWeekAnalysisDTOS;
    private List<GenderAnalysisDTO> genderAnalysisDTOS;
    private List<MonthAnalysisDTO> monthAnalysisDTOS;
    private List<TimeAnalysisDTO> timeAnalysisDTOS;

    public void setAnalysisDTO(List<DayOfWeekAnalysisDTO> dayOfWeekAnalysisDTOS, List<GenderAnalysisDTO> genderAnalysisDTOS, List<MonthAnalysisDTO> monthAnalysisDTOS, List<TimeAnalysisDTO> timeAnalysisDTOS) {
        this.dayOfWeekAnalysisDTOS = dayOfWeekAnalysisDTOS;
        this.genderAnalysisDTOS = genderAnalysisDTOS;
        this.monthAnalysisDTOS = monthAnalysisDTOS;
        this.timeAnalysisDTOS = timeAnalysisDTOS;

    }
}
