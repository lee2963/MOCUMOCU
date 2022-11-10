package MOCUMOCU.project.customize.controller;

import MOCUMOCU.project.customize.dto.RequestTypeDTO;
import MOCUMOCU.project.customize.dto.SendCustomizeDTO;
import MOCUMOCU.project.customize.service.CustomizeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customize")
@RequiredArgsConstructor
public class CustomizeController {

    private final CustomizeServiceImpl customizeService;

    @GetMapping("/customize-list")
    public ResponseEntity<List<SendCustomizeDTO>> sendCustomize(@RequestParam String type) {
        List<SendCustomizeDTO> sendCustomizeDTOS = customizeService.findAllComponent(type);
        return new ResponseEntity<>(sendCustomizeDTOS, HttpStatus.OK);
    }
}
