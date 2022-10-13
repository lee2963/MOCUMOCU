package MOCUMOCU.project.Market;

import MOCUMOCU.project.reward.Reward;
import MOCUMOCU.project.form.MarketAddDTO;
import MOCUMOCU.project.form.RewardOwnerDTO;
import MOCUMOCU.project.owner.OwnerRepository;
import MOCUMOCU.project.reward.RewardRepository;
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
    private final RewardRepository rewardRepository;

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

    /**
     *
     * 수정 데이터만 수정 할거 정해야함
     */

    @Override
    public void updateMarket(Market market) {
        marketRepository.findOne(market.getId());

    }

    @Override
    @Transactional(readOnly = true)
    public List<RewardOwnerDTO> findAllReward(Long id) {

        List<Reward> findRewards = rewardRepository.findByMarketId(id);
        List<RewardOwnerDTO> rewardOwnerDTOList = new ArrayList<>();

        for (Reward findReward : findRewards) {
            RewardOwnerDTO rewardOwnerDTO = new RewardOwnerDTO();

            rewardOwnerDTO.setId(findReward.getId());
            rewardOwnerDTO.setReward(findReward.getRewardContent());
            rewardOwnerDTO.setCouponRequire(findReward.getNeedAmount());

            rewardOwnerDTOList.add(rewardOwnerDTO);
        }

        return rewardOwnerDTOList;
    }

}
