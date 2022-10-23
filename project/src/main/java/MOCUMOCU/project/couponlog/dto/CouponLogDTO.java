package MOCUMOCU.project.couponlog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class CouponLogDTO {

    private LocalDate usedDay;

    private List<CouponLogInfo> logInfos;
}
