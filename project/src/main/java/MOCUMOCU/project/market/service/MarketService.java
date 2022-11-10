package MOCUMOCU.project.market.service;

import MOCUMOCU.project.couponlog.dto.GenderAnalysisDTO;
import MOCUMOCU.project.market.dto.*;
import MOCUMOCU.project.market.entity.Market;
import MOCUMOCU.project.reward.form.RewardOwnerDTO;

import java.util.List;

public interface MarketService {

    void addMarket(MarketAddDTO marketAddDTO);

    void removeMarket(Long id);

    void addEventImage(AddEventImageDTO addEventImageDTO);

    void updateMarketNum(Long id, String newNum);

    SendEventImageDTO sendEventImage(Long marketId);

    void removeEventImage(Long marketId);

    List<MarketInfoDTO> findAllMarket(Long id);

    GenderDTO genderAnalysis(Long marketId);
}
