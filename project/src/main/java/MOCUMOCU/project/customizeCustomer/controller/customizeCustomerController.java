package MOCUMOCU.project.customizeCustomer.controller;

import MOCUMOCU.project.customizeCustomer.dto.BuyCustomizeDTO;
import MOCUMOCU.project.customizeCustomer.dto.RequestComponentDTO;
import MOCUMOCU.project.customizeCustomer.dto.SendComponentDTO;
import MOCUMOCU.project.customizeCustomer.service.CustomizeCustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/customize-customer")
@RestController
@RequiredArgsConstructor
public class customizeCustomerController {

    private final CustomizeCustomerServiceImpl customizeCustomerService;

    @PostMapping("/buy")
    public ResponseEntity<Void> buyCustomize(@RequestBody BuyCustomizeDTO buyCustomizeDTO) {
        if(customizeCustomerService.saveCustomizeCustomer(buyCustomizeDTO)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/component-list")
    public ResponseEntity<List<SendComponentDTO>> showComponentList(@RequestBody RequestComponentDTO requestComponentDTO) {
        List<SendComponentDTO> sendComponentDTOS = customizeCustomerService.findAllMyComponent(requestComponentDTO.getCustomerId(), requestComponentDTO.getType());
        return new ResponseEntity<>(sendComponentDTOS, HttpStatus.OK);
    }

}
