package MOCUMOCU.project.owner;

import MOCUMOCU.project.Market.Market;
import MOCUMOCU.project.form.MarketInfoDTO;
import MOCUMOCU.project.form.OwnerInfoDTO;
import MOCUMOCU.project.form.OwnerLoginDTO;
import MOCUMOCU.project.domain.Privacy;
import MOCUMOCU.project.Market.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final MarketRepository marketRepository;

    @Override
    public Long join(Owner owner) {
        ownerRepository.save(owner);
        return owner.getId();
    }

    @Override
    public void withdrawal(Long id) {
        Owner findOwner = ownerRepository.findOne(id);
        ownerRepository.remove(findOwner);
    }

    @Override
    public void updatePrivacy(Long id, Privacy privacy) {
        Owner findOwner = ownerRepository.findOne(id);
        findOwner.setPrivacy(privacy);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MarketInfoDTO> findAllMarket(Long id) {

        List<Market> findMarkets = marketRepository.findByOwnerId(id);
        List<MarketInfoDTO> marketInfoDTOList = new ArrayList<>();

        for (Market findMarket : findMarkets) {
            MarketInfoDTO marketInfoDTO = new MarketInfoDTO();

            marketInfoDTO.setName(findMarket.getMarketName());
            marketInfoDTO.setPhoneNum(findMarket.getMarketPhoneNum());
            marketInfoDTO.setId(findMarket.getId());

            marketInfoDTOList.add(marketInfoDTO);
        }

        return marketInfoDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean login(OwnerLoginDTO ownerLoginDTO) {
        List<Owner> findOwner = ownerRepository.findByEmail(ownerLoginDTO.getOwnerEmail());


        if (findOwner.isEmpty()) {
            return false;
        } else {
            return findOwner.get(0).getPrivacy().getPassword().equals(ownerLoginDTO.getOwnerPassword());
        }
    }

    @Override
    public OwnerInfoDTO findOwnerByEmail(String email) {
        List<Owner> findOwners = ownerRepository.findByEmail(email);

        Owner findOwner = findOwners.get(0);
        OwnerInfoDTO ownerInfoDTO = new OwnerInfoDTO();
        ownerInfoDTO.setOwnerId(findOwner.getId());
        ownerInfoDTO.setOwnerName(findOwner.getPrivacy().getName());
        ownerInfoDTO.setOwnerEmail(findOwner.getPrivacy().getEmail());
        ownerInfoDTO.setLogIn(true);
        ownerInfoDTO.setUserType("Owner");
        return ownerInfoDTO;
    }
}
