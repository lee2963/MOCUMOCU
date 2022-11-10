package MOCUMOCU.project.owner.service;

import MOCUMOCU.project.owner.entity.Owner;
import MOCUMOCU.project.owner.form.OwnerInfoDTO;
import MOCUMOCU.project.owner.form.OwnerLoginDTO;
import MOCUMOCU.project.Privacy;

public interface OwnerService {

    Long join(Owner owner);

    void withdrawal(Long id);

    void updatePhoneNum(Long id, String phoneNum);
    void updatePassword(Long id, String password);

    boolean authPassword(String password, Long id);

    boolean login(OwnerLoginDTO ownerLoginDTO);

    OwnerInfoDTO findOwnerByEmail(String email);

    String findEmail(String customerName, String phoneNum);

    String findPassword(String email);

}
