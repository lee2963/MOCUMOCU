package MOCUMOCU.project.market.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActivityData {

    private String label;
    private double value;
    private String color;


    public ActivityData() {
        this.label = "ACTIVITY";
        this.value = 0.5;
        this.color = "#FA6072";
    }
}