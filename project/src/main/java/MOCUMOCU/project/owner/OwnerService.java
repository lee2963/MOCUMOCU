package MOCUMOCU.project.owner;

import MOCUMOCU.project.form.MarketInfoDTO;
import MOCUMOCU.project.form.OwnerInfoDTO;
import MOCUMOCU.project.form.OwnerLoginDTO;
import MOCUMOCU.project.domain.Privacy;

import java.util.List;

public interface OwnerService {

    Long join(Owner owner);

    void withdrawal(Long id);

    void updatePrivacy(Long id, Privacy privacy);

    List<MarketInfoDTO> findAllMarket(Long id);

    boolean login(OwnerLoginDTO ownerLoginDTO);

    OwnerInfoDTO findOwnerByEmail(String email);
}
