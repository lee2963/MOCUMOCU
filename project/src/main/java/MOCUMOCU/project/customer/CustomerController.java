package MOCUMOCU.project.customer;

import MOCUMOCU.project.domain.Privacy;
import MOCUMOCU.project.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody CustomerRegisterDTO customerRegisterDTO) {

        Privacy newPrivacy = new Privacy();
        newPrivacy.setEmail(customerRegisterDTO.getCustomerEmail());
        newPrivacy.setName(customerRegisterDTO.getCustomerName());
        newPrivacy.setPassword(customerRegisterDTO.getCustomerPassword());
        newPrivacy.setPhoneNum(customerRegisterDTO.getCustomerPhoneNum());

        Customer newCustomer = new Customer();
        newCustomer.setPrivacy(newPrivacy);

        if (customerRegisterDTO.getCustomerGender().equals("MALE")) {
            newCustomer.setGender(Gender.MALE);
        } else {
            newCustomer.setGender(Gender.FEMALE);
        }
        newCustomer.setBirthDate(customerRegisterDTO.getCustomerBirth());

        customerService.join(newCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerInfoDTO> login(@RequestBody CustomerLoginDTO customerLoginDTO) {
        if (customerService.login(customerLoginDTO)) {
            CustomerInfoDTO customerInfoDTO = customerService.findCustomerByEmail(customerLoginDTO.getCustomerEmail());

            return new ResponseEntity<>(customerInfoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
