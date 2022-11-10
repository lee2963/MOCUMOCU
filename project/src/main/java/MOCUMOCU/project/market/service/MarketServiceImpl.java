package MOCUMOCU.project.market.service;

import MOCUMOCU.project.couponlog.entity.CouponLog;
import MOCUMOCU.project.couponlog.repository.CouponLogRepository;
import MOCUMOCU.project.customer.entity.Gender;
import MOCUMOCU.project.market.dto.*;
import MOCUMOCU.project.market.entity.Market;
import MOCUMOCU.project.market.repository.MarketRepository;
import MOCUMOCU.project.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final OwnerRepository ownerRepository;
    private final MarketRepository marketRepository;

    private final CouponLogRepository couponLogRepository;

    @Override
    public void addMarket(MarketAddDTO marketAddDTO) {

        Market newMarket = new Market();
        newMarket.setMarketName(marketAddDTO.getMarketName());
        newMarket.setMarketPhoneNum(marketAddDTO.getMarketPhoneNum());
        newMarket.setEventSmallImage(null);
        newMarket.setEventBigImage(null);
        newMarket.setOwner(ownerRepository.findOne(marketAddDTO.getOwnerId()));
        marketRepository.save(newMarket);
    }

    @Override
    public void removeMarket(Long id) {
        Market findMarket = marketRepository.findOne(id);
        marketRepository.remove(findMarket);
    }

    @Override
    public void addEventImage(AddEventImageDTO addEventImageDTO) {
        Market findMarket = marketRepository.findOne(addEventImageDTO.getId());
        findMarket.setEventBigImage(addEventImageDTO.getBigImage());
        findMarket.setEventSmallImage(addEventImageDTO.getSmallImage());
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
    public void updateMarketNum(Long id, String newNum) {
        Market findMarket = marketRepository.findOne(id);
        findMarket.setMarketPhoneNum(newNum);

    }

    @Override
    public SendEventImageDTO sendEventImage(Long id) {
        Market findMarket = marketRepository.findOne(id);
        SendEventImageDTO sendEventImageDTO = new SendEventImageDTO();
        sendEventImageDTO.setBigImage(findMarket.getEventBigImage());
        sendEventImageDTO.setSmallImage(findMarket.getEventSmallImage());

        return sendEventImageDTO;
    }

    @Override
    public void removeEventImage(Long marketId) {
        Market findMarket = marketRepository.findOne(marketId);
        findMarket.setEventBigImage(null);
        findMarket.setEventSmallImage(null);
    }

    @Override
    public GenderDTO genderAnalysis(Long marketId) {

        String[] genderInfo = {"남자", "여자"};


        LocalDate date = LocalDate.now();
        List<CouponLog> marketLog = couponLogRepository.findByMarketIdAndDay(marketId, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        GenderDTO genderDTO = new GenderDTO();

        Gender gender = Gender.MALE;

        if (marketLog.isEmpty()) {
            genderDTO.setMale(0);
            genderDTO.setFemale(0);

        } else{
            long maleVisitor = marketLog.stream().filter(c -> c.getCustomer().getGender().equals(Gender.MALE)).count();
            genderDTO.setMale((int)maleVisitor);
            genderDTO.setFemale(marketLog.size() - genderDTO.getMale());
        }

        return genderDTO;
    }


}
