package MOCUMOCU.project.customizeCustomer;

import java.util.List;

public interface CustomizeCustomerService {

    void saveCustomizeCustomer(Long customerId, Long customizeId);

    void removeCustomizeCustomer(CustomizeCustomer customizeCustomer);

    List<CustomizeCustomer> findAllMyCustomize(Long customerId);

    List<CustomizeCustomer> findAllMyComponent(String type);

    void setCouponCustomize(Long couponId);

}
