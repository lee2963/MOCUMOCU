package MOCUMOCU.project.pointlog.controller;

import MOCUMOCU.project.pointlog.dto.PointLogDTO;
import MOCUMOCU.project.pointlog.service.PointLogServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/pointlog")
@RequiredArgsConstructor
public class PointLogController {

    private final PointLogServiceImpl pointLogService;

    @GetMapping("/customer/scroll")
    public ResponseEntity<Slice<PointLogDTO>> CouponLogForCustomer(@RequestParam Long customerId, Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().descending());
        Slice<PointLogDTO> logInfo = pointLogService.findAllPointLog(customerId, pageRequest);
        return new ResponseEntity<>(logInfo, HttpStatus.OK);
    }
}
