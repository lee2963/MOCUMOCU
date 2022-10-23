package MOCUMOCU.project.owner.service;

import MOCUMOCU.project.owner.entity.Owner;
import MOCUMOCU.project.owner.form.OwnerInfoDTO;
import MOCUMOCU.project.owner.form.OwnerLoginDTO;
import MOCUMOCU.project.Privacy;

public interface OwnerService {

    Long join(Owner owner);

    void withdrawal(Long id);

    void updatePrivacy(Long id, Privacy privacy);

    boolean login(OwnerLoginDTO ownerLoginDTO);

    OwnerInfoDTO findOwnerByEmail(String email);
}
