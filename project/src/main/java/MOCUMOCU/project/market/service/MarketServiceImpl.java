package MOCUMOCU.project.market.service;

import MOCUMOCU.project.market.dto.MarketInfoDTO;
import MOCUMOCU.project.market.dto.MarketAddDTO;
import MOCUMOCU.project.market.entity.Market;
import MOCUMOCU.project.market.repository.MarketRepository;
import MOCUMOCU.project.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final OwnerRepository ownerRepository;
    private final MarketRepository marketRepository;

    @Override
    public void addMarket(MarketAddDTO marketAddDTO) {

        Market newMarket = new Market();
        newMarket.setMarketName(marketAddDTO.getMarketName());
        newMarket.setMarketPhoneNum(marketAddDTO.getMarketPhoneNum());
        newMarket.setOwner(ownerRepository.findOne(marketAddDTO.getOwnerId()));
        marketRepository.save(newMarket);
    }

    @Override
    public void removeMarket(Long id) {
        Market findMarket = marketRepository.findOne(id);
        marketRepository.remove(findMarket);
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

    /**
     *
     * 수정 데이터만 수정 할거 정해야함
     */

    @Override
    public void updateMarket(Market market) {
        marketRepository.findOne(market.getId());

    }



}
