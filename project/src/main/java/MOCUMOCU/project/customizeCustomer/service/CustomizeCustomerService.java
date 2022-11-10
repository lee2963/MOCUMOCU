package MOCUMOCU.project.customizeCustomer.service;

import MOCUMOCU.project.customizeCustomer.dto.BuyCustomizeDTO;
import MOCUMOCU.project.customizeCustomer.dto.SendComponentDTO;
import MOCUMOCU.project.customizeCustomer.entity.CustomizeCustomer;

import java.util.List;

public interface CustomizeCustomerService {

    boolean saveCustomizeCustomer(BuyCustomizeDTO buyCustomizeDTO);

    void removeCustomizeCustomer(CustomizeCustomer customizeCustomer);

    List<CustomizeCustomer> findAllMyCustomize(Long customerId);

    List<SendComponentDTO> findAllMyComponent(Long customerId, String type);



}
