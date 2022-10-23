package MOCUMOCU.project.market;

import MOCUMOCU.project.market.form.MarketAddDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/market")
@RequiredArgsConstructor
public class MarketController {

    private final MarketServiceImpl marketService;

    @PostMapping("/store")
    public ResponseEntity<Void> addMarket(@RequestBody MarketAddDTO marketAddDTO) {
        marketService.addMarket(marketAddDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/{ownerId}/market-list")
//    public ResponseEntity<List<MarketInfoDTO>> showMarkets(@PathVariable Long ownerId) {
//
//        List<MarketInfoDTO> findMarkets = marketService.findAllMarket(ownerId);
//        List<ActivityData> activityData = new ArrayList<>();
//        ActivityData newActivityData = new ActivityData();
//        activityData.add(newActivityData);
//
//
//        for (MarketInfoDTO findMarket : findMarkets) {
//            findMarket.setRewardList(marketService.findAllReward(findMarket.getId()));
//            findMarket.setActivityData(activityData);
//        }
//
//        return new ResponseEntity<>(findMarkets, HttpStatus.OK);
//    }
}
