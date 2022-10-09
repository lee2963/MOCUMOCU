package MOCUMOCU.project.owner;

import MOCUMOCU.project.customer.Customer;
import MOCUMOCU.project.customer.CustomerService;
import MOCUMOCU.project.domain.Privacy;
import MOCUMOCU.project.form.*;
import MOCUMOCU.project.coupon.CouponService;
import MOCUMOCU.project.Market.MarketService;
import MOCUMOCU.project.reward.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final MarketService marketService;
    private final RewardService rewardService;
    private final CouponService couponService;
    private final CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody OwnerRegisterDTO ownerRegisterDTO) {
        Privacy newPrivacy = new Privacy();
        newPrivacy.setEmail(ownerRegisterDTO.getOwnerEmail());
        newPrivacy.setName(ownerRegisterDTO.getOwnerName());
        newPrivacy.setPassword(ownerRegisterDTO.getOwnerPassword());
        newPrivacy.setPhoneNum(ownerRegisterDTO.getOwnerPhoneNum());

        Owner newOwner = new Owner();
        newOwner.setPrivacy(newPrivacy);

        ownerService.join(newOwner);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<OwnerInfoDTO> login(@RequestBody OwnerLoginDTO ownerLoginDTO) {
        if (ownerService.login(ownerLoginDTO)) {
            OwnerInfoDTO ownerInfoDTO = ownerService.findOwnerByEmail(ownerLoginDTO.getOwnerEmail());

            return new ResponseEntity<>(ownerInfoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/store")
    public ResponseEntity<Void> addMarket(@RequestBody MarketAddDTO marketAddDTO) {
        marketService.addMarket(marketAddDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{ownerId}/market-list")
    public ResponseEntity<List<MarketInfoDTO>> showMarkets(@PathVariable Long ownerId) {

        List<MarketInfoDTO> findMarkets = ownerService.findAllMarket(ownerId);
        List<ActivityData> activityData = new ArrayList<>();
        ActivityData newActivityData = new ActivityData();
        activityData.add(newActivityData);


        for (MarketInfoDTO findMarket : findMarkets) {
            findMarket.setRewardList(marketService.findAllReward(findMarket.getId()));
            findMarket.setActivityData(activityData);
        }

        return new ResponseEntity<>(findMarkets, HttpStatus.OK);
    }

    @DeleteMapping("/store/{storeId}")
    public ResponseEntity<Void> removeMarket(@PathVariable Long storeId) {
        marketService.removeMarket(storeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/store/reward")
    public ResponseEntity<Void> saveReward(@RequestBody RewardAddDTO rewardAddDTO) {
        rewardService.addReward(rewardAddDTO);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{ownerId}/store/{marketId}/reward/{rewardId}")
    public ResponseEntity<Void> removeReward(@PathVariable Long rewardId) {
        rewardService.removeReward(rewardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/stamp")
    public ResponseEntity<Void> saveStamp(@RequestBody SaveStampDTO saveStampDTO) {
        couponService.earnStamp(saveStampDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/stamp")
    public ResponseEntity<Void> useStamp(@RequestBody UseStampDTO useStampDTO) {
        if(couponService.useStamp(useStampDTO)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/phoneNum")
    public ResponseEntity<CustomerSendDTO> searchCustomerByPhoneNum(@RequestBody PhoneNumDTO phoneNumDTO) {

        if (customerService.isPhoneNumExist(phoneNumDTO.getPhoneNumber())) {
            Customer findCustomer = customerService.findByPhoneNum(phoneNumDTO.getPhoneNumber());

            CustomerSendDTO customerSendDTO = new CustomerSendDTO();
            customerSendDTO.setCustomerId(findCustomer.getId());
            customerSendDTO.setName(findCustomer.getPrivacy().getName());

            return new ResponseEntity<>(customerSendDTO, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{marketId}/reward-list")
    public ResponseEntity<List<RewardOwnerDTO>> rewardList(@PathVariable Long marketId) {

        List<RewardOwnerDTO> rewardOwnerDTOList = marketService.findAllReward(marketId);

        return new ResponseEntity<>(rewardOwnerDTOList, HttpStatus.OK);
    }


}
