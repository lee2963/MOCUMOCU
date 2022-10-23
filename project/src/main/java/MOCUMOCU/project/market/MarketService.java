package MOCUMOCU.project.market;

import MOCUMOCU.project.market.form.MarketAddDTO;
import MOCUMOCU.project.market.form.MarketInfoDTO;

import java.util.List;

public interface MarketService {

    void addMarket(MarketAddDTO marketAddDTO);

    void removeMarket(Long id);

    void updateMarket(Market market);

    List<MarketInfoDTO> findAllMarket(Long id);
}
