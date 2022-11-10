package MOCUMOCU.project.customer.controller;

import MOCUMOCU.project.Privacy;
import MOCUMOCU.project.coupon.dto.FindCustomerPasswordDTO;
import MOCUMOCU.project.coupon.dto.FindEventDTO;
import MOCUMOCU.project.customer.dto.*;
import MOCUMOCU.project.customer.service.CustomerService;
import MOCUMOCU.project.customer.entity.Gender;
import MOCUMOCU.project.customer.entity.Customer;
import MOCUMOCU.project.customer.service.CustomerServiceImpl;
import MOCUMOCU.project.owner.form.AuthPasswordDTO;
import MOCUMOCU.project.owner.form.UpdatePasswordDTO;
import MOCUMOCU.project.owner.form.UpdatePhoneNumDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

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

    @PostMapping("/updateInfo")
    public ResponseEntity<Void> changePhoneNum(@RequestBody ChangePhoneNumDTO changePhoneNumDTO) {
        customerService.updatePhoneNum(changePhoneNumDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{customerId}/attendance")
    public ResponseEntity<Boolean> checkAttendance(@PathVariable Long customerId) {
        boolean attendance = customerService.attendance(customerId);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @GetMapping("/{customerId}/attendance-check")
    public ResponseEntity<Boolean> updateAttendance(@PathVariable Long customerId) {
        boolean attendance = customerService.updateAttendance(customerId);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @GetMapping("/{customerId}/point")
    public ResponseEntity<Integer> sendPoint(@PathVariable Long customerId) {
        Integer point = customerService.sendPoint(customerId);
        return new ResponseEntity<>(point, HttpStatus.OK);
    }

    //출석체크 이전으로 돌리기 테스트용임
    @GetMapping("/{customerId}/attendance-return")
    public ResponseEntity<Void> returnAttendance(@PathVariable Long customerId) {
        customerService.returnAttendance(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<Void> authOwnerPassword(@RequestBody AuthPasswordDTO authPasswordDTO) {

        if (customerService.authPassword(authPasswordDTO.getPassword(), authPasswordDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        customerService.updatePassword(updatePasswordDTO.getId(), updatePasswordDTO.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/phoneNum")
    public ResponseEntity<Void> updatePhoneNum(@RequestBody UpdatePhoneNumDTO updatePhoneNumDTO) {
        customerService.updatePhoneNum(updatePhoneNumDTO.getId(), updatePhoneNumDTO.getPhoneNum());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find/email")
    public ResponseEntity<String> findEmail(@RequestBody FindCustomerEmailDTO findCustomerEmailDTO) {
        String findEmail = customerService.findEmail(findCustomerEmailDTO.getCustomerName(), findCustomerEmailDTO.getPhoneNum());

        if (findEmail.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(findEmail, HttpStatus.OK);
        }
    }

    @PostMapping("/find/password")
    public ResponseEntity<String> findPassword(@RequestBody FindCustomerPasswordDTO findCustomerPasswordDTO) {
        String findPassword = customerService.findPassword(findCustomerPasswordDTO.getEmail());

        if (findPassword.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(findPassword, HttpStatus.OK);
        }
    }


    @GetMapping("/find/all-event")
    public ResponseEntity<List<FindEventDTO>> findEventList(){
        List<FindEventDTO> findEventDTOS = customerService.findEvent();

        return new ResponseEntity<>(findEventDTOS, HttpStatus.OK);
    }

    @GetMapping("/find/big-event/{marketId}")
    public ResponseEntity<String> findBigEventUrl(@PathVariable Long marketId) {
        String bigImageUrl = customerService.findBigImageUrl(marketId);

        return new ResponseEntity<>(bigImageUrl, HttpStatus.OK);
    }


}
