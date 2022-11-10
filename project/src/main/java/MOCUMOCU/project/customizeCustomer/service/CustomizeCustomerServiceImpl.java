package MOCUMOCU.project.customizeCustomer.service;

import MOCUMOCU.project.customer.entity.Customer;
import MOCUMOCU.project.customer.repository.CustomerRepository;
import MOCUMOCU.project.customize.entity.Customize;
import MOCUMOCU.project.customize.entity.Type;
import MOCUMOCU.project.customize.repository.CustomizeRepository;
import MOCUMOCU.project.customizeCustomer.dto.BuyCustomizeDTO;
import MOCUMOCU.project.customizeCustomer.dto.SendComponentDTO;
import MOCUMOCU.project.customizeCustomer.entity.CustomizeCustomer;
import MOCUMOCU.project.customizeCustomer.repository.CustomizeCustomerRepository;
import MOCUMOCU.project.pointlog.entity.PointLog;
import MOCUMOCU.project.pointlog.repository.PointLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomizeCustomerServiceImpl implements CustomizeCustomerService{

    private final CustomizeCustomerRepository customizeCustomerRepository;

    private final CustomerRepository customerRepository;

    private final CustomizeRepository customizeRepository;

    private final PointLogRepository pointLogRepository;

    @Override
    public boolean saveCustomizeCustomer(BuyCustomizeDTO buyCustomizeDTO) {

        CustomizeCustomer newCustomizeCustomer = new CustomizeCustomer();
        Customer findCustomer = customerRepository.findOne(buyCustomizeDTO.getCustomerId());
        Customize findCustomize = customizeRepository.findOne(buyCustomizeDTO.getCustomizeId());

        PointLog newPointLog = new PointLog();
        newPointLog.setPointLog(findCustomize, findCustomer);

        if (findCustomer.getUserPoint() >= findCustomize.getCustomizePoint()) {
            findCustomer.setUserPoint(findCustomer.getUserPoint() - findCustomize.getCustomizePoint());

            if(findCustomize.getType().equals(Type.BOARD)){
                newPointLog.setType(MOCUMOCU.project.pointlog.entity.Type.BOARD);
            } else{
                newPointLog.setType(MOCUMOCU.project.pointlog.entity.Type.STAMP);
            }
            pointLogRepository.save(newPointLog);

            newCustomizeCustomer.setCustomizeCustomer(findCustomize, findCustomer, newPointLog);
            customizeCustomerRepository.save(newCustomizeCustomer);

            return true;
        } else{
            return false;
        }


    }

    @Override
    public void removeCustomizeCustomer(CustomizeCustomer customizeCustomer) {
        customizeCustomerRepository.remove(customizeCustomer);
    }

    @Override
    public List<CustomizeCustomer> findAllMyCustomize(Long customerId) {
        return customizeCustomerRepository.findByCustomerId(customerId);
    }

    @Override
    public List<SendComponentDTO> findAllMyComponent(Long customerId, String type) {
        Type customizeType;
        if(type.equals("board")){
            customizeType = Type.BOARD;
        } else{
            customizeType = Type.STAMP;

        }
        List<CustomizeCustomer> customizeCustomers = customizeCustomerRepository.findByType(customerId, customizeType);
        List<SendComponentDTO> sendComponentDTOS = new ArrayList<>();
        if(!customizeCustomers.isEmpty()) {
            if (type.equals("board")) {
                for (CustomizeCustomer customizeCustomer : customizeCustomers) {
                    SendComponentDTO sendComponentDTO = new SendComponentDTO();

                    sendComponentDTO.setId(customizeCustomer.getId());
                    sendComponentDTO.setBigImageUrl(customizeCustomer.getCustomize().getBigImageUrl());
                    sendComponentDTO.setSmallImageUrl(customizeCustomer.getCustomize().getSmallImageUrl());

                    sendComponentDTOS.add(sendComponentDTO);
                }
            } else {
                for (CustomizeCustomer customizeCustomer : customizeCustomers) {
                    SendComponentDTO sendComponentDTO = new SendComponentDTO();

                    sendComponentDTO.setId(customizeCustomer.getId());
                    sendComponentDTO.setBigImageUrl(customizeCustomer.getCustomize().getBigImageUrl());
                    sendComponentDTO.setSmallImageUrl(customizeCustomer.getCustomize().getSmallImageUrl());

                    sendComponentDTOS.add(sendComponentDTO);
                }
            }
        }

        return sendComponentDTOS;
    }


}
