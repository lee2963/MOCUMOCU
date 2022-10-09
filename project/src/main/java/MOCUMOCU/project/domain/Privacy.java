package MOCUMOCU.project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Privacy {

    private String name;
    private String email;
    private String password;
    private String phoneNum;
}
