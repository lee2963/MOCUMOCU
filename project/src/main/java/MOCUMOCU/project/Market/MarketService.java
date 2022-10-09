package MOCUMOCU.project.Market;

import MOCUMOCU.project.Market.Market;
import MOCUMOCU.project.form.MarketAddDTO;
import MOCUMOCU.project.form.RewardOwnerDTO;

import java.util.List;

public interface MarketService {

    void addMarket(MarketAddDTO marketAddDTO);

    void removeMarket(Long id);

    void updateMarket(Market market);

    List<RewardOwnerDTO> findAllReward(Long id);
}
