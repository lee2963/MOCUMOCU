package MOCUMOCU.project.reward.controller;

import MOCUMOCU.project.reward.service.RewardServiceImpl;
import MOCUMOCU.project.reward.form.RewardAddDTO;
import MOCUMOCU.project.reward.form.RewardCustomerDTO;
import MOCUMOCU.project.reward.form.RewardOwnerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reward")
@RequiredArgsConstructor
public class RewardController {

    private final RewardServiceImpl rewardService;

    @PostMapping("/store")
    public ResponseEntity<Void> saveReward(@RequestBody RewardAddDTO rewardAddDTO) {
        rewardService.addReward(rewardAddDTO);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/remove/{rewardId}")
    public ResponseEntity<Void> removeReward(@PathVariable Long rewardId) {
        rewardService.removeReward(rewardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{marketId}/reward-list")
    public ResponseEntity<List<RewardOwnerDTO>> rewardList(@PathVariable Long marketId) {
        log.info("marketId={}", marketId);
        List<RewardOwnerDTO> rewardOwnerDTOList = rewardService.findAllReward(marketId);

        return new ResponseEntity<>(rewardOwnerDTOList, HttpStatus.OK);
    }

    @GetMapping("/customer/{couponId}/reward-list")
    public ResponseEntity<List<RewardCustomerDTO>> customerRewardList(@PathVariable Long couponId) {

        List<RewardCustomerDTO> rewardCustomerDTOS = rewardService.customerReward(couponId);
        return new ResponseEntity<>(rewardCustomerDTOS, HttpStatus.OK);
    }



}
