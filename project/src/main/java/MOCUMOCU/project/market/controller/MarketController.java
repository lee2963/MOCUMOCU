package MOCUMOCU.project.market.controller;

import MOCUMOCU.project.market.dto.*;
import MOCUMOCU.project.market.service.MarketServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{ownerId}/market-list")
    public ResponseEntity<List<MarketInfoDTO>> showMarkets(@PathVariable Long ownerId) {

        List<MarketInfoDTO> findMarkets = marketService.findAllMarket(ownerId);

        for (MarketInfoDTO findMarket : findMarkets) {
            findMarket.setGenderDTO(marketService.genderAnalysis(findMarket.getId()));
        }

        return new ResponseEntity<>(findMarkets, HttpStatus.OK);
    }

    @PutMapping("/update/market-num")
    public ResponseEntity<Void> updateMarketNum(@RequestBody UpdateMarketNumDTO updateMarketNumDTO) {
        marketService.updateMarketNum(updateMarketNumDTO.getId(), updateMarketNumDTO.getMarketNum());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/event/add")
    public ResponseEntity<Void> addEvent(@RequestBody AddEventImageDTO addEventImageDTO) {
        marketService.addEventImage(addEventImageDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{marketId}/event/remove")
    public ResponseEntity<Void> removeEvent(@PathVariable Long marketId) {
        marketService.removeEventImage(marketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{marketId}/event/show")
    public ResponseEntity<SendEventImageDTO> showEvent(@PathVariable Long marketId) {
        SendEventImageDTO sendEventImageDTO = marketService.sendEventImage(marketId);
        return new ResponseEntity<>(sendEventImageDTO, HttpStatus.OK);
    }
}
