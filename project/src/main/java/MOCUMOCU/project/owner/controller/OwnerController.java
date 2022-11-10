package MOCUMOCU.project.owner.controller;

import MOCUMOCU.project.customer.entity.Customer;
import MOCUMOCU.project.customer.service.CustomerService;
import MOCUMOCU.project.Privacy;
import MOCUMOCU.project.customer.dto.CustomerSendDTO;
import MOCUMOCU.project.customer.dto.PhoneNumDTO;
import MOCUMOCU.project.market.service.MarketService;
import MOCUMOCU.project.owner.form.*;
import MOCUMOCU.project.owner.service.OwnerService;
import MOCUMOCU.project.owner.entity.Owner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/owner")
@Slf4j
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final MarketService marketService;

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
            log.info("suc");
            return new ResponseEntity<>(ownerInfoDTO, HttpStatus.OK);
        } else {
            log.info("fail");
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/store/{storeId}")
    public ResponseEntity<Void> removeMarket(@PathVariable Long storeId) {
        marketService.removeMarket(storeId);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @PostMapping("/auth")
    public ResponseEntity<Void> authOwnerPassword(@RequestBody AuthPasswordDTO authPasswordDTO) {

        if (ownerService.authPassword(authPasswordDTO.getPassword(), authPasswordDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/phoneNum")
    public ResponseEntity<Void> updatePhoneNum(@RequestBody UpdatePhoneNumDTO updatePhoneNumDTO) {
        ownerService.updatePhoneNum(updatePhoneNumDTO.getId(), updatePhoneNumDTO.getPhoneNum());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        ownerService.updatePassword(updatePasswordDTO.getId(), updatePasswordDTO.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find/email")
    public ResponseEntity<String> findEmail(@RequestBody FindOwnerEmailDTO findOwnerEmailDTO) {
        String findEmail = ownerService.findEmail(findOwnerEmailDTO.getOwnerName(), findOwnerEmailDTO.getPhoneNum());

        if (findEmail.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(findEmail, HttpStatus.OK);
        }
    }

    @PostMapping("/find/password")
    public ResponseEntity<String> findPassword(@RequestBody FindOwnerPasswordDTO findOwnerPasswordDTO) {
        String findPassword = ownerService.findPassword(findOwnerPasswordDTO.getEmail());

        if (findPassword.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(findPassword, HttpStatus.OK);
        }
    }

}
