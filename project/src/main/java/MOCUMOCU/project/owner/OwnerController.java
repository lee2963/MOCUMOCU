package MOCUMOCU.project.owner;

import MOCUMOCU.project.customer.Customer;
import MOCUMOCU.project.customer.CustomerService;
import MOCUMOCU.project.Privacy;
import MOCUMOCU.project.customer.form.CustomerSendDTO;
import MOCUMOCU.project.customer.form.PhoneNumDTO;
import MOCUMOCU.project.market.MarketService;
import MOCUMOCU.project.owner.form.OwnerInfoDTO;
import MOCUMOCU.project.owner.form.OwnerLoginDTO;
import MOCUMOCU.project.owner.form.OwnerRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/owner")
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

            return new ResponseEntity<>(ownerInfoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
}
