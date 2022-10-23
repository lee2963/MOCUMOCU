package MOCUMOCU.project.customizeCustomer.service;

import MOCUMOCU.project.customer.entity.Customer;
import MOCUMOCU.project.customer.repository.CustomerRepository;
import MOCUMOCU.project.customize.entity.Customize;
import MOCUMOCU.project.customize.repository.CustomizeRepository;
import MOCUMOCU.project.customizeCustomer.entity.CustomizeCustomer;
import MOCUMOCU.project.customizeCustomer.repository.CustomizeCustomerRepository;
import MOCUMOCU.project.pointlog.entity.PointLog;
import MOCUMOCU.project.pointlog.repository.PointLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomizeCustomerServiceImpl implements CustomizeCustomerService{

    private final CustomizeCustomerRepository customizeCustomerRepository;

    private final CustomerRepository customerRepository;

    private final CustomizeRepository customizeRepository;

    private final PointLogRepository pointLogRepository;

    @Override
    public void saveCustomizeCustomer(Long customerId, Long customizeId) {
        CustomizeCustomer newCustomizeCustomer = new CustomizeCustomer();
        Customer findCustomer = customerRepository.findOne(customerId);
        Customize findCustomize = customizeRepository.findOne(customizeId);

        PointLog newPointLog = new PointLog();
        newPointLog.setPointLog(findCustomize, findCustomer);
        pointLogRepository.save(newPointLog);

        newCustomizeCustomer.setCustomizeCustomer(findCustomize, findCustomer, newPointLog);
        customizeCustomerRepository.save(newCustomizeCustomer);
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
    public List<CustomizeCustomer> findAllMyComponent(String type) {
        return customizeCustomerRepository.findByType(type);
    }

    @Override
    public void setCouponCustomize(Long couponId) {

    }
}
