package MOCUMOCU.project.market.service;

import MOCUMOCU.project.market.dto.MarketAddDTO;
import MOCUMOCU.project.market.dto.MarketInfoDTO;
import MOCUMOCU.project.market.entity.Market;

import java.util.List;

public interface MarketService {

    void addMarket(MarketAddDTO marketAddDTO);

    void removeMarket(Long id);

    void updateMarket(Market market);

    List<MarketInfoDTO> findAllMarket(Long id);
}
